package ServeurPoolThreads;

import java.io.*;
import java.net.*;
import java.sql.*;
import newBean.*;
import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class RunnableTraitement implements Runnable
{
    private Boolean terminer = false;
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracleCompta;
    private BeanBDAccess beanOracleTrafic;
    //private BeanBDAccess beanOracleDesicions;

    
    public RunnableTraitement(Socket s)
    {
        CSocket = s;
        
        try
        {
            dis = new DataInputStream(new BufferedInputStream(CSocket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(CSocket.getOutputStream()));
        }
        catch(IOException e)
        {
            System.err.println("RunnableTraitement : Host non trouvé : " + e);
        }
        
        
        /* PROPERTIES */
        Properties prop = new Properties();
        String Emplacement = "localhost";
        int port = 1521;
        String DBCompta = "COMPTA";
        String DBTrafic = "TRAFIC";
        //String DBDecisions = "DECISIONS";
        String DB = "XE";
        
        try
        {
            FileInputStream FIS = new FileInputStream("DataAnalysis.properties");
            prop.load(FIS);
        }
        catch(FileNotFoundException ex)
        {
            try 
            {
                FileOutputStream FOS = new FileOutputStream("ServeurDataAnalysis.properties");
                
                prop.setProperty("Emplacement", "localhost");
                prop.setProperty("Port", "1521");
                prop.setProperty("DBCompta", "COMPTA");
                prop.setProperty("DBTrafic", "TRAFIC");
                //prop.setProperty("DBDecisions", "DECISIONS");
                prop.setProperty("DB", "XE");
                              
                try
                {
                    prop.store(FOS, null);
                }
                catch (IOException ex1)
                {
                    System.err.println("RunnableTraitement : IOException (Ecriture properties) : " + ex1.getMessage());
                    System.exit(0);
                }
            } 
            catch (FileNotFoundException ex1) 
            {
                System.err.println("RunnableTraitement : FileNotFoundException (Properties) : " + ex1.getMessage());
                System.exit(0);
            }
            
        }
        catch(IOException ex)
        {
            System.err.println("RunnableTraitement : IOException (Lecture properties) : " + ex.getMessage());
            System.exit(0);
        }
        
        Emplacement = prop.getProperty("Emplacement");
        port = Integer.parseInt(prop.getProperty("Port"));
        DBCompta = prop.getProperty("DBCompta");
        DBTrafic = prop.getProperty("DBTrafic");
        //DBDecisions = prop.getProperty("DBDecisions");
        DB = prop.getProperty("DB");
        
        
        /* BEANS */
        beanOracleCompta = new BeanBDAccess();
        beanOracleTrafic = new BeanBDAccess();
        //beanOracleDecisions = new BeanBDAccess();
        try
        {
            beanOracleCompta.connexionOracle(Emplacement, port, DBCompta, DBCompta, DB);
            beanOracleTrafic.connexionOracle(Emplacement, port, DBTrafic, DBTrafic, DB);
            //beanOracleDecisions.connexionOracle(Emplacement, port, DBDecisions, DBDecisions, DB);
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Class not found " + ex.getMessage());
        }
        catch (connexionException ex)
        {
            System.err.println(ex.getNumException() + " -- " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException : " + ex.getMessage());
        }
    }

    
    /* RUNNABLE : BOUCLE DU SERVEUR + APPEL DES METHODES SUIVANT LE SERVICE DEMANDE */
    @Override
    public void run()
    {
        System.out.println("RunnableTraitement : Execution du run");
        
        while (!terminer)
        {   
            String reponse = ReceiveMsg(); 
            
            if (terminer)
                break;
            
            String[] parts = reponse.split("#");
         
            switch (Integer.parseInt(parts[0]))
            {
                case ProtocolePIDEP.LOGIN :
                    Login();
                    break;
                    
                case ProtocolePIDEP.GET_STAT_DESCR_CONT :
                    GetStatDescrCont(parts);
                    break;
                    
                case ProtocolePIDEP.GET_GR_COULEUR_REP :
                    GetGrCouleurRep(parts);
                    break;
                    
                case ProtocolePIDEP.GET_GR_COULEUR_COMP :
                    GetGrCouleurComp(parts);
                    break;
                    
                default :
                    terminer = true;
                    break;
            }
        }
        
        System.out.println("RunnableTraitement : Fin du while et du client");
    }
    
    
    /* ENVOI D'UN MESSAGE AU CLIENT */
    public void SendMsg(String msg)
    {
        String chargeUtile = msg;
        int taille = chargeUtile.length();
        StringBuffer message = new StringBuffer(String.valueOf(taille) + "#" + chargeUtile);
            
        try
        {               
            dos.write(message.toString().getBytes());
            dos.flush();
        }
        catch(IOException e)
        {
            System.err.println("RunnableTraitement : Erreur d'envoi de msg (IO) : " + e);
        }
    }
    
    
    /* RECEPTION D'UN MESSAGE DU CLIENT */
    public String ReceiveMsg()
    {
        byte b;
        StringBuffer taille = new StringBuffer();
        StringBuffer message = new StringBuffer();
        
        try
        {
            while ((b = dis.readByte()) != (byte)'#')
            {      
                if (b != (byte)'#')
                    taille.append((char)b);
            }
            for (int i = 0; i < Integer.parseInt(taille.toString()); i++)
            {
                b = dis.readByte();
                message.append((char)b);
            }  
        }
        catch(IOException e)
        {
            terminer = true;
            System.err.println("RunnableTraitement : ReceiveMsg : Erreur de reception de msg (IO) : " + e);
        }
            
        return message.toString();
    }
    
    
    /* LOGIN (à partir de BD_COMPTA */
    public void Login()
    {      
        System.out.println("RunnableTraitement : DEBUT LOGIN");
        
        try
        {
            // Lecture des données
            String user = dis.readUTF();
            long temps = dis.readLong();
            double aleatoire = dis.readDouble();
            int longueur = dis.readInt();
            byte[] pwdClient = new byte[longueur];
            dis.readFully(pwdClient);
                       
            // Récupération du mot de passe dans la base de données
            String passwordDB = null;
            
            ResultSet ResultatDB = beanOracleCompta.selection("PASSWORD", "PERSONNEL", "LOGIN = '" + user + "'");
            while (ResultatDB.next())
                passwordDB = ResultatDB.getString(1);
            
            // confection d'un digest local
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(passwordDB.getBytes());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream bdos = new DataOutputStream(baos);
            bdos.writeLong(temps);
            bdos.writeDouble(aleatoire);
            md.update(baos.toByteArray());
            byte[] pwdLocal = md.digest();

            // comparaison            
            if (MessageDigest.isEqual(pwdClient, pwdLocal))
            {
                SendMsg("OUI");
                System.out.println("RunnableTraitement : Login : Le client " + user + " est connecté au serveur");
            }
            else
            {
                SendMsg("NON");
                System.out.println("RunnableTraitement : Login : Le client " + user + " est refusé");
            }
        }
        catch (SQLException e)
        {
            SendMsg("NON");
            System.err.println("RunnableTraitement : SQLexception Login : " + e.getMessage());
        }
        catch (NoSuchAlgorithmException e)
        {
            SendMsg("NON");
            System.err.println("RunnableTraitement : Login : NoSuchAlgorithmException : " + e.getMessage());
        }
        catch (IOException e)
        {
            SendMsg("NON");
            System.err.println("RunnableTraitement : Login : IOException : " + e.getMessage());
        }
        
        System.out.println("RunnableTraitement : Fin LOGIN");
    }
    
    
    /* STATITIQUE DESCRIPTIVE CONTINUE (moyenne, mode, médiane, écart-type */
    /* In : Nb de containers sur lequels faire les stats + containers chargés ou déchargés */
    /* OUT : moyenne, mode, médiane, écart-type OU NON#msgErreur */
    public void GetStatDescrCont(String[] parts)
    {
        System.out.println("RunnableTraitement : DEBUT GETSTATDESCRCONT");
        
        try
        {   
            // Base de données
            int nbCont = Integer.parseInt(parts[1]);
            String condition;
            double[] arrayPoids = new double[nbCont];
            
            if (parts[2].equals("OUT")) // Chargement
                condition = "DATE_DEPART IS NOT NULL AND EXTRACT(YEAR FROM(TO_DATE(DATE_DEPART, 'DD/MM/YYYY'))) = EXTRACT(YEAR FROM SYSDATE) ORDER BY DBMS_RANDOM.VALUE";
            else // Déchargement
                condition = "EXTRACT(YEAR FROM(TO_DATE(DATE_ARRIVEE, 'DD/MM/YYYY'))) = EXTRACT(YEAR FROM SYSDATE) ORDER BY DBMS_RANDOM.VALUE";

            ResultSet ResultatDB = beanOracleTrafic.selection("POIDS", "MOUVEMENTS", condition);
            
                       
            // Vérification de la taille de l'échantillon
            if (!ResultatDB.next())
            {
                SendMsg("NON#Aucune donnee correspondante aux parametres demandes");
                return;
            }
                    
            ResultatDB.last();
            int size = ResultatDB.getRow();
            System.out.println("size : " + size);

            if (size < nbCont)
            {
                SendMsg("NON#L'echantillon ne peut actuellement pas depasser " + size + " pour cette condition");
                return;
            }
            
            // Remplissage du tableau de poids
            ResultatDB.beforeFirst();
            for(int i = 0; i < nbCont && ResultatDB.next(); i++)
            {
                System.out.println(ResultatDB.getDouble("POIDS"));
                arrayPoids[i] = ResultatDB.getDouble("POIDS");
            }
           
            
            // Calculs
            DescriptiveStatistics ds = new DescriptiveStatistics(arrayPoids);
            double moyenne = ds.getMean();
            double ecartType = ds.getStandardDeviation();
            double[] modetab = StatUtils.mode(arrayPoids);
            double mediane = ds.getPercentile(50);
            
            
            // Envoi des données
            String mode = "[";
            int i;
            for(i = 0; i < (modetab.length - 1); i++)
                mode += modetab[i] + ", ";
            mode += modetab[i] + "]";
                
            String ChargeUtile = moyenne + "#" + mode + "#" + mediane + "#" + ecartType;
            SendMsg(ChargeUtile);
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Probleme de recherche des donnees");
            System.err.println("RunnableTraitement : SQLexception GetStatDescrCont : " + ex.getMessage());
        }
        
        System.out.println("RunnableTraitement : FIN GETSTATDESCRCONT");
    }
    
    
    /* DIAGRAMME SECTORIEL DE REPARTITION DU NOMBRE DE CONTAINERS PAR DESTINATION POUR UNE ANNEE OU UN MOIS DONNE */
    /* In : Année ou mois */
    public void GetGrCouleurRep(String[] parts)
    {
        System.out.println("RunnableTraitement : DEBUT GETGRCOULEURREP");
        
        try
        {   
            // Base de données
            String condition;
            
            if (parts[1].length() == 4) // Année
                condition = "EXTRACT(YEAR FROM(TO_DATE(DATE_ARRIVEE, 'DD/MM/YYYY'))) = " + parts[1] + " GROUP BY DESTINATION";
            else // Mois
                condition = "EXTRACT(MONTH FROM(TO_DATE(DATE_ARRIVEE, 'DD/MM/YYYY'))) = " + parts[1] + " GROUP BY DESTINATION";
            
            ResultSet ResultatDB = beanOracleTrafic.selection("DESTINATION, COUNT(ID_CONTAINER)", "MOUVEMENTS", condition);
            
            
            // HashMap
            HashMap<String, Object> map = new HashMap();
            ArrayList<String> listDestinations = new ArrayList<>();
            ArrayList<Integer> listCount = new ArrayList<>();
            
            if(!ResultatDB.first())
            {
                SendMsg("NON");
                return;
            }
            
            do
            {
                listDestinations.add(ResultatDB.getString("DESTINATION"));
                listCount.add(ResultatDB.getInt("COUNT(ID_CONTAINER)"));
            }while(ResultatDB.next());
            
            map.put("DESTINATIONS", listDestinations);
            map.put("COUNT", listCount);
            
            
            // Envoi
            SendMsg("OUI");
            ObjectOutputStream oos = new ObjectOutputStream(CSocket.getOutputStream());
            oos.writeObject(map);
            oos.flush();
        }
        catch (SQLException ex)
        {
            System.err.println("RunnableTraitement : SQLexception GetGrCouleurRep : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("RunnableTraitement : IOException GetGrCouleurRep : " + ex.getMessage());
        }
        
        System.out.println("RunnableTraitement : FIN GETGRCOULEURREP");
    }
    
    
    /* HISTOGRAMME DE REPARTITION DU NOMBRE DE CONTAINERS PAR DESTINATION PAR TRIMESTRE POUR UNE ANNEE DONNEE */
    /* In : Année */
    public void GetGrCouleurComp(String[] parts)
    {
        System.out.println("RunnableTraitement : DEBUT GETGRCOULEURCOMP");
        
        try
        {   
            // Base de données
            String condition = "EXTRACT(YEAR FROM(TO_DATE(DATE_ARRIVEE, 'DD/MM/YYYY'))) = " + parts[1] + " GROUP BY DESTINATION";
            
            ResultSet ResultatDB = beanOracleTrafic.selection("DESTINATION, COUNT(ID_CONTAINER)", "MOUVEMENTS", condition);
            
            
            // HashMap
            HashMap<String, Object> map = new HashMap();
            ArrayList<String> listDestinations = new ArrayList<>();
            ArrayList<Integer> listCount = new ArrayList<>();
            
            if(!ResultatDB.first())
            {
                SendMsg("NON");
                return;
            }
            
            do
            {
                listDestinations.add(ResultatDB.getString("DESTINATION"));
                listCount.add(ResultatDB.getInt("COUNT(ID_CONTAINER)"));
            }while(ResultatDB.next());
            
            map.put("DESTINATIONS", listDestinations);
            map.put("COUNT", listCount);
            
            
            // Envoi
            SendMsg("OUI");
            ObjectOutputStream oos = new ObjectOutputStream(CSocket.getOutputStream());
            oos.writeObject(map);
            oos.flush();
        }
        catch (SQLException ex)
        {
            System.err.println("RunnableTraitement : SQLexception GetGrCouleurComp : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("RunnableTraitement : IOException GetGrCouleurComp : " + ex.getMessage());
        }
        
        System.out.println("RunnableTraitement : FIN GETGRCOULEURCOMP");
    }
}

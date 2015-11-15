package ServeurPoolThreads;

import java.io.*;
import java.net.*;
import java.sql.*;
import newBean.*;
import java.security.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.jfree.data.statistics.Statistics;


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
        try
        {   
            // Base de données
            int nbCont = Integer.parseInt(parts[1]);
            String condition;
            double[] arrayPoids = new double[nbCont];
            Random r = new Random();
            
            int alea = r.nextInt();
            
            if (parts[2].equals("OUT")) // Chargement
                condition = "DATE_DEPART IS NOT NULL AND EXTRACT(YEAR FROM DATE_DEPART) = EXTRACT(YEAR FROM SYSDATE) ORDER BY " + alea;
            else // Déchargement
                condition = "EXTRACT(YEAR FROM DATE_ARRIVEE) = EXTRACT(YEAR FROM SYSDATE) ORDER BY " + alea;
            
            ResultSet ResultatDB = beanOracleTrafic.selection("POIDS", "MOUVEMENTS", condition);
            
            
            // Vérification de la taille de l'échantillon
            int size = 0;
            if (ResultatDB != null)
            {
                ResultatDB.last();
                size = ResultatDB.getRow();
            }
            
            if (size < nbCont)
                SendMsg("NON#L'échantillon ne peut actuellement pas dépasser " + size + " pour cette condition");
            
            
            // Remplissage du tableau de poids
            ResultatDB.beforeFirst();
            for(int i = 0; i < size; i++)
            {
                arrayPoids[i] = ResultatDB.getDouble("POIDS");
                ResultatDB.next();
            }
           
            
            // Calculs
            DescriptiveStatistics ds = new DescriptiveStatistics(arrayPoids);
            double moyenne = ds.getMean();
            double ecartType = ds.getStandardDeviation();
            double[] mode = StatUtils.mode(arrayPoids);
            double mediane = Statistics.calculateMedian(Arrays.asList(arrayPoids));
            
            
            // Envoi des données
            String ChargeUtile = moyenne + "#" + /*mode + "#" +*/ mediane + "#" + ecartType;
            SendMsg(ChargeUtile);
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Problème de recherche des données");
            System.err.println("RunnableTraitement : SQLexception GetStatDescrCont : " + ex.getMessage());
        }
    }
    

    
    
    


    
    
    
    
    
    
    
    
    /* On met dans un fichier les bateaux entrant */
    /*public void BoatArrived(String[] parts)
    {
        Bateau b = new Bateau(parts[1], parts[2]);
        
        ListeBateauAmarre.add(b);
        
        String FichierPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "bateaux.dat";
        
        try
        {
            FileOutputStream fos = new FileOutputStream(FichierPath);
            ObjectOutputStream ecriture = new ObjectOutputStream(fos);
            ecriture.writeObject(ListeBateauAmarre);
        }
        catch(IOException e)
        {
            System.err.println("RunnableTraitement BoatArrived : " + e);
        }
        
        SendMsg("OUI");
        
        System.out.println("RunnableTraitement : Fin BOAT_ARRIVED");
        
        System.out.println("Nbr bateaux amarrés : " + ListeBateauAmarre.size());
    }*/
    
    /* On stocke dans une liste les emplacements du container à insérer dans le parc */
    /*public void HandleContainerIn(String[] parts)
    {
        Boolean trouve = false;
        
        if(ListCurrentContainer == null)
            ListCurrentContainer = new ArrayList<>();
        MaJListeParc();
        for(Parc p : ListeParc)
        {
            if (p.getFlag().equals("0"))
            {
                ListCurrentContainer.add(new Parc(parts[1], parts[2].toUpperCase()));
                ListCurrentContainer.get(ListCurrentContainer.size()-1).setDateAjout();
                p.setFlag("1");
                SendMsg("OUI");
                HashMap<String, String> donnees = new HashMap<>();
                donnees.put("flag", "1");
                
                String condition = "X = " + p.getX() + " AND Y = " + p.getY();
                try {
                    beanCSV.miseAJour("\"parc.csv\"", donnees, condition);
                } catch (requeteException ex) {
                    System.err.println("ligne 290 " + ex.getNumErreur() + "---" + ex.getMessage() );
                }
                trouve = true;
                break;
            }  
        }
        
        if (trouve == false)
            SendMsg("NON");
        
        System.out.println("RunnableTraitement : Fin HANDLE_CONTAINER_IN");
    }*/
    
    /* On insère les containers de la liste dans le fichier .csv du parc */
    /*public void EndContainerIn()
    {   
        if(ListCurrentContainer == null)
        {
            SendMsg("OUI");
            return;
        }
        
        MaJListeParc();
        
        boolean fichierMaJ = true;
        boolean curContAdd = false;
        
        for(Parc curCont : ListCurrentContainer)
        {
            curContAdd = false;
            for(Parc p : ListeParc)
            {
                if (p.getFlag().equals("1"))
                {
                    HashMap<String, String> donnees = new HashMap<>();
                    donnees.put("IdContainer", curCont.getId());
                    donnees.put("Destination", curCont.getDestination());
                    donnees.put("DateAjout", curCont.getDateAjout());
                    donnees.put("flag", "2");

                    p.setId(curCont.getId());
                    p.setDestination(curCont.getDestination());
                    p.setDateAjout();
                    p.setFlag("2");

                    String condition = "X = " + p.getX() + " AND Y = " + p.getY();

                    try {
                        beanCSV.miseAJour("\"parc.csv\"", donnees, condition);
                    } catch (requeteException ex) {
                        System.err.println("line 331 : " + ex.getNumErreur() + "---" + ex.getMessage());
                    }

                    curContAdd = true;
                    break;                  
                }
            }
            if(curContAdd == false)
            {
                fichierMaJ = false;
                break;        
            }
        }
        
        ListCurrentContainer = null;
        if(fichierMaJ)
            SendMsg("OUI");
        else
        {
            
            SendMsg("NON");
        }
        
        System.out.println("RunnableTraitement : Fin END_CONTAINER_IN");
    }
    
    public void GetContainers(String[] parts)
    {
        String requeteCond =  "Destination = '" + parts[1].toUpperCase() + "'";
        first = false;
        if(parts[2].equals("FIRST"))
        {
           first =  true; 
           requeteCond  =  requeteCond + " ORDER BY DateAjout";
        }

        ResultSet ResultatDB = null;
        try {
            ResultatDB = beanCSV.selection("*", "\"parc.csv\"", requeteCond);
        } catch (SQLException ex) {
            System.err.println("line 368 SQLException " + ex.getMessage());
        }
        
        
        
        String Message ="";
        ListCurrentContainer =  new ArrayList<>();
        try
        {
            while(ResultatDB.next())
            {
                Message = Message + ResultatDB.getString("X") +"$"+ResultatDB.getString("Y") +"$"+ResultatDB.getString("IdContainer") + "$";
                Message = Message + ResultatDB.getString("Destination")+"$"+ResultatDB.getString("DateAjout")+"#";
                ListCurrentContainer.add(new Parc(ResultatDB.getString("X"), ResultatDB.getString("Y"), ResultatDB.getString("IdContainer")));           
            }
        }
        catch (SQLException ex)
        {
            System.err.println("RunnableTraitement : Erreur lecture ResultSet : " + ex);
        }
        
        containerToRemove = new ArrayList<>();
        SendMsg(Message);
    }
    
    
    public void HandleContainerOut(String[] parts)
    {
        
        if(ListCurrentContainer == null || ListCurrentContainer.size() == 0)
        {
            SendMsg("NON");
            return;
        }
        
        Parc toRemove = new Parc(parts[2], parts[3], parts[1]);
        
        if(first) //On vérifie qu'on retire bien le 1er
        {
            if(!ListCurrentContainer.get(0).getX().equals(toRemove.getX()) || !ListCurrentContainer.get(0).getY().equals(toRemove.getY()))
            {
                SendMsg("NON");
                return;
            }
            else
            {
                ListCurrentContainer.remove(0);
            }          
        }
        else
        {
            boolean containerRemoved = false;
            for(Parc elem : ListCurrentContainer)
            {      
               if(toRemove.getX().equals(elem.getX()) && toRemove.getY().equals(elem.getY()) && toRemove.getId().equals(elem.getId()))
               {
                   containerRemoved = true;
                   ListCurrentContainer.remove(elem);
                   break;
               }
            }
            if(!containerRemoved)
            {
                SendMsg("NON");
                return;
            }
        }
        
        containerToRemove.add(toRemove);
        SendMsg("OUI");
    }
    
    public void EndContainerOut()
    {
        if(containerToRemove == null)
        {
            SendMsg("OUI");
            return;
        }
        
        MaJListeParc();
        
        boolean fichierMaJ = true;
        boolean curContAdd = false;
        
        for(Parc curCont : containerToRemove)
        {
            curContAdd = false;
            for(Parc p : ListeParc)
            {
                if (p.getId().equals(curCont.getId()) && p.getX().equals(curCont.getX()) && p.getY().equals(curCont.getY()))
                {
                    HashMap<String, String> donnees = new HashMap<>();
                    donnees.put("IdContainer", "0");
                    donnees.put("Destination", "0");
                    donnees.put("DateAjout", "0");
                    donnees.put("flage", "0");

                    p.setId("0");
                    p.setDestination("0");

                    String condition = "X = " + p.getX() + " AND Y = " + p.getY();

                    try {
                        beanCSV.miseAJour("\"parc.csv\"", donnees, condition);
                    } catch (requeteException ex) {
                        System.err.println("line 473 " + ex.getNumErreur() + "---" + ex.getMessage());
                    }

                    curContAdd = true;
                    break;                  
                }
            }
            if(curContAdd == false)
            {
                fichierMaJ = false;
                break;        
            }
        }
        
        ListCurrentContainer = null;
        if(fichierMaJ)
            SendMsg("OUI");
        else
            SendMsg("NON");
        
        System.out.println("RunnableTraitement : Fin END_CONTAINER_OUT");
    }
    
    
    public void MaJListeParc()
    {
        ResultSet ResultatDB = null;
        try {
            ResultatDB = beanCSV.selection("*", "\"parc.csv\"", null);
        } catch (SQLException ex) {
            System.err.println("SQLException line 503 " + ex.getMessage());
        }

        try
        {
            ListeParc = new ArrayList<>(); 
            while(ResultatDB.next())
            {
                Parc p = new Parc(ResultatDB.getString("X"), ResultatDB.getString("Y"), ResultatDB.getString("IdContainer"), ResultatDB.getString("Destination"), ResultatDB.getString("DateAjout"), ResultatDB.getString("flag"));
                ListeParc.add(p);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("RunnableTraitement : Erreur lecture ResultSet : " + ex);
        }
    }*/
}

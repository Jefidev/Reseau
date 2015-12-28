package serveur_compta;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import newBean.BeanBDAccess;
import newBean.connexionException;


public class Runnable_BISAMAP implements Runnable
{
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracle;
    private String fonction;
    
    
    public Runnable_BISAMAP(Socket s)
    {
        CSocket = s;

        try
        {
            dis = new DataInputStream(new BufferedInputStream(CSocket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(CSocket.getOutputStream()));
        }
        catch(IOException e)
        {
            System.err.println("Runnable_BISAMAP : Host non trouvé : " + e);
        }
        
        beanOracle = new BeanBDAccess();
        try
        {
            beanOracle.connexionOracle("localhost", 1521, "COMPTA", "COMPTA", "XE");
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Runnable_BISAMAP : Class not found " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.err.println("Runnable_BISAMAP : SQL Exception (Oracle)" + ex.getMessage()); 
        }
        catch (connexionException ex)
        {
            System.err.println("Runnable_BISAMAP : " + ex.getNumException() + " -- " + ex.getMessage());
        }
    }

    
    /* RUNNABLE : BOUCLE DU SERVEUR + APPEL DES METHODES SUIVANT LE SERVICE DEMANDE */
    @Override
    public void run()
    {
        String[] parts = (ReceiveMsg()).split("#");
        
        if(parts[0].equals("LOGIN"))
        {
            if(!login())
                return;
        }
        else
        {
            SendMsg("ERR#Requete invalide");
            return;
        }
        
        boolean terminer = false;
        
        while(!terminer)
        {
            parts = ReceiveMsg().split("#");
            switch (parts[0])
            {                          
                case "GET_NEXT_BILL" :
                    getNextBill(parts);
                    break;
                    
                case "VALIDATE_BILL" :
                    validateBill(parts);
                    break;
                
                case "LIST_BILLS" :
                    listBills(parts);
                    break;
                    
                case "SEND_BILLS" :
                    sendBills(parts);
                    break;
                    
                case "REC_PAY" :
                    recPay(parts);
                    break;
                                        
                case "LIST_WAITING" :
                    listWaiting(parts);
                    break;
                                                            
                case "COMPUTE_SAL" :
                    computeSal(parts);
                    break;
                                                                                
                case "VALIDATE_SAL" :
                    validateSal(parts);
                    break;
                    
                case "LOGOUT" :
                    terminer = true;
                    break;
                    
                default :
                    terminer = true;
                    break;
            }
        }
        
        System.err.println("Runnable_BISAMAP : Run : Fin du runnable");
        
        try
        {
            CSocket.close();
        }
        catch (IOException ex)
        {
            System.err.println("Runnable_BISAMAP : Run : Erreur de close : " + ex.getMessage());
        }
    }
    
    
    /* LOGIN (à partir de BD_COMPTA */
    /* OUT : OUI/NON */
    private boolean login()
    {
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

            ResultSet rs = beanOracle.selection("PASSWORD, FONCTION", "PERSONNEL", "LOGIN = '" + user + "'");
            while (rs.next())
            {
                passwordDB = rs.getString(1);
                fonction = rs.getString(2);
            }
            
            // L'employé doit être comptable ou chef-comptable
            if(fonction.equalsIgnoreCase("comptable") || fonction.equalsIgnoreCase("chef-comptable"))
            {
                SendMsg("NON#Mauvaise fonction de l'employe");
                System.out.println("Runnable_BISAMAP : Login : Le client " + user + " est refusé");
                return false;
            }
            
            // Comparaison
            if (MessageDigest.isEqual(pwdClient, Crypto.Digest(passwordDB, temps, aleatoire)))
            {
                SendMsg("OUI");
                System.out.println("Runnable_BISAMAP : Login : Le client " + user + " est connecté au serveur");
                return true;
            }
            else
            {
                SendMsg("NON#Mauvais mot de passe");
                System.out.println("Runnable_BISAMAP : Login : Le client " + user + " est refusé");
            }
        }
        catch (IOException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : Login : IOException : " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : Login : SQLexception : " + ex.getMessage());
        }
        
        return false;
    }

            
    private void getNextBill(String[] request)
    {
    }
                
      
    private void validateBill(String[] request)
    {
    }
    
    
    private void listBills(String[] request)
    {
    }
    

    private void sendBills(String[] request)
    {
    }
       
    
    private void recPay(String[] request)
    {
    }
         
    
    private void listWaiting(String[] request)
    {
    }
            
    
    private void computeSal(String[] request)
    {
    }
    
    
    private void validateSal(String[] request)
    {
    }
    
    
    /* Envoi d'un message au client */
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
            System.err.println("Runnable_BISAMAP : SendMsg : Erreur d'envoi de msg (IO) : " + e);
        }
    }
    
    
    /* Réception d'un message du client */
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
            System.err.println("Runnable_BISAMAP : ReceiveMsg : Erreur de reception de msg (IO) : " + e);
        }
            
        return message.toString();
    }
}

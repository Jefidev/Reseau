package serveur_compta;

import java.io.*;
import java.net.*;
import java.sql.*;
import newBean.*;


public class Runnable_CHAMAP implements Runnable
{
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracle;
    
    
    public Runnable_CHAMAP(Socket s)
    {
        CSocket = s;

        try
        {
            dis = new DataInputStream(new BufferedInputStream(CSocket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(CSocket.getOutputStream()));
        }
        catch(IOException e)
        {
            System.err.println("Runnable_CHAMAP : Host non trouvé : " + e);
        }
        
        beanOracle = new BeanBDAccess();
        try
        {
            beanOracle.connexionOracle("localhost", 1521, "COMPTA", "COMPTA", "XE");
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Runnable_CHAMAP : Class not found " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.err.println("Runnable_CHAMAP : SQL Exception (Oracle)" + ex.getMessage()); 
        }
        catch (connexionException ex)
        {
            System.err.println("Runnable_CHAMAP : " + ex.getNumException() + " -- " + ex.getMessage());
        }
    }

    
    @Override
    public void run()
    {
        String[] parts = (ReceiveMsg()).split("#");
        
        if(parts[0].equals("LOGIN_TRAF"))
        {
            if(!loginTraf(parts))
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
                case "MAKE_BILL" :
                        makeBill(parts);
                        break;
                case "LOGOUT" :
                    terminer = true;
                    break;
                    
                default :
                    terminer = true;
                    break;
            }
        }
        
        System.err.println("Runnable_CHAMAP : Fin du runnable");
        
        try
        {
            CSocket.close();
        }
        catch (IOException ex)
        {
            System.err.println("Runnable_CHAMAP : Erreur de close : " + ex.getMessage());
        }
    }
    
    
    private boolean loginTraf(String[] part)
    {
        ResultSet rs = null;
        
        try
        {
            rs = beanOracle.selection("PASSWORD", "PERSONNEL", "LOGIN = '" + part[1]+"'");
        }
        catch(SQLException e)
        {
            System.err.println("Runnable_CHAMAP : " + e.getMessage());
        }
        
        String pwd = null;
        
        try
        {
            if(!rs.next())
            {
                SendMsg("ERR#Login invalide");
            }
            else
                pwd = rs.getString("PASSWORD");
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }

        if(pwd.equals(part[2]))
        {
            SendMsg("ACK");
            return true;
        }
        else
            SendMsg("ERR#Mot de passe incorrect");
        
        return false;
    }
    
    
    private void makeBill(String[] request)
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
            System.err.println("Runnable_CHAMAP : Erreur d'envoi de msg (IO) : " + e);
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
            System.err.println("Runnable_CHAMAP : Erreur de reception de msg (IO) : " + e);
        }
            
        return message.toString();
    }
}

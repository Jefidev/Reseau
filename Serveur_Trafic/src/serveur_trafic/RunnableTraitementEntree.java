package serveur_trafic;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import newBean.*;

public class RunnableTraitementEntree implements Runnable
{
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracle;

    boolean first = true;
    
    public RunnableTraitementEntree(Socket s)
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
        
        beanOracle = new BeanBDAccess();
        try {
            beanOracle.connexionOracle("localhost", 1521, "TRAFIC", "TRAFIC", "XE");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("SQL Exception (oracle)" + ex.getMessage()); 
        } catch (connexionException ex) {
            System.err.println(ex.getNumException() + " -- " + ex.getMessage());
        }
    }

    @Override
    public void run()
    {
        String[] parts = (ReceiveMsg()).split("#");
        
        if(parts[0].equals("LOGIN"))
        {
            if(!login(parts))
                return;
        }
        else
        {
            SendMsg("ERR#Requète invalide");
        }
        
        System.err.println("login ok");
        
        //TO DO
    }
    
    private boolean login(String[] part)
    {
        ResultSet rs = null;
        
        try
        {
            rs = beanOracle.selection("PASSWORD", "UTILISATEURS", "LOGIN = '" + part[1]+"'");
        }
        catch(SQLException e){
            System.err.println(e.getStackTrace());
        }
        
        String pwd = null;
        
        try {
            if(!rs.next())
            {
                SendMsg("ERR#Login invalide");
            }
            else
                pwd = rs.getString("PASSWORD");
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        System.err.println(part[2]);
        if(pwd.equals(part[2]))
        {
            SendMsg("ACK");
            return true;
        }
        else
            SendMsg("ERR#Mot de passe incorrecte");
        
        return false;
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
            System.err.println("RunnableTraitement : Erreur d'envoi de msg (IO) : " + e);
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
            System.err.println("RunnableTraitement : Erreur de reception de msg (IO) : " + e);
        }
            
        return message.toString();
    }
}

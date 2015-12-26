package serveur_compta;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import newBean.BeanBDAccess;
import newBean.connexionException;


public class Runnable_BISAMAP implements Runnable
{
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracle;
    
    
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
            SendMsg("ERR#Requete invalide");
            return;
        }
        
        boolean terminer = false;
        
        while(!terminer)
        {
            parts = ReceiveMsg().split("#");
            switch (parts[0])
            {       
                case "LOGOUT" :
                    terminer = true;
                    break;
                    
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
                    
                default :
                    terminer = true;
                    break;
            }
        }
        
        System.err.println("Runnable_BISAMAP : Fin du runnable");
        
        try
        {
            CSocket.close();
        }
        catch (IOException ex)
        {
            System.err.println("Runnable_BISAMAP : Erreur de close : " + ex.getStackTrace());
        }
    }
    
    
    private boolean login(String[] part)
    {
        ResultSet rs = null;
        
        try
        {
            rs = beanOracle.selection("PASSWORD", "PERSONNEL", "LOGIN = '" + part[1]+"'");
        }
        catch(SQLException e)
        {
            System.err.println("Runnable_BISAMAP : " + e.getStackTrace());
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
            System.err.println(ex.getStackTrace());
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
            System.err.println("Runnable_BISAMAP : Erreur d'envoi de msg (IO) : " + e);
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
            System.err.println("Runnable_BISAMAP : Erreur de reception de msg (IO) : " + e);
        }
            
        return message.toString();
    }
}

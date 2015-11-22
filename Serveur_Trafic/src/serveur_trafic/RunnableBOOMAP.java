/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_trafic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import newBean.BeanBDAccess;
import newBean.connexionException;

/**
 *
 * @author Jerome
 */
public class RunnableBOOMAP implements Runnable{
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracle;

    boolean first = true;
    
    public RunnableBOOMAP(Socket s)
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
                    
                case "GET_XY" :
                    get_xy(parts);
                    break;
                    
                case "SEND_WEIGHT" :
                    send_weight(parts);
                    break;
                
                case "GET_LIST" :
                    get_list(parts);
                    break;
                    
                case "SIGNAL_DEP" :
                    signal_dep(parts);
                    break;
                    
                default :
                    terminer = true;
                    break;
            }
        }
        
        try {
            CSocket.close();
        } catch (IOException ex) {
            System.err.println("Erreur de close : " + ex.getStackTrace());
        }
        
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

        if(pwd.equals(part[2]))
        {
            SendMsg("ACK");
            return true;
        }
        else
            SendMsg("ERR#Mot de passe incorrecte");
        
        return false;
    }
    
    private void get_xy(String[] request)
    {
        String message = "ACK#";
        
        ResultSet rs = null;
        
        try {
            rs = beanOracle.selection("X, Y", "PARC", "ETAT = 1");
        } catch (SQLException ex) {
            SendMsg("ERR#Probleme SQL");
            System.err.println(ex.getStackTrace());
            return;
        }
        System.err.println(request[3]);
        String[] listContainer = request[3].split("@");
        for(String s : listContainer)
        {
            try {
                if(rs.next())
                {
                    if(!message.equals("ACK#"))
                        message = message + "@";
                    //TO DO etat 2 + set destination + societe du transporteur et transporteur + mouvement 
                    message = message + rs.getString("X")+";"+rs.getString("Y");
                }
                else
                {
                    
                }
            } catch (SQLException ex) {
                SendMsg("ERR#Probleme SQL");
                System.err.println(ex.getStackTrace());
                return;
            }
        }
        
        SendMsg(message);   
    }
    
    private void get_list(String[] request)
    {
        ResultSet rs = null;
        String transport = null;
        
        if(request[2].equals("1"))
            transport = "TRAIN";
        else
            transport = "BATEAU";
              
        try {
            rs = beanOracle.selection("ID_CONTAINER, X, Y", "PARC", "TRANSPORT = '"+transport+"' AND UPPER(DESTINATION) = UPPER('"+request[3]+"')");
        } catch (SQLException ex) {
            SendMsg("ERR#Acces a la BD impossible");
            System.err.println("erreur: " + ex.getStackTrace().toString());
            return;
        }
        
        String message = "";
        
        try {
            while(rs.next())
            {
                if(!message.isEmpty())
                    message = message + "#";
                
                message = message + rs.getString("ID_CONTAINER") + "@" + rs.getString("X") + ";" + rs.getString("Y");
            }
        } catch (SQLException ex) {
            SendMsg("ERR#Acces a la BD impossible");
            System.err.println("test" + ex.getStackTrace().toString());
            return;
        }
        
        if(message.isEmpty())
        {
            SendMsg("ERR#Aucun container pour la destination");
            return;
        }
        
        SendMsg("ACK#"+message);
    }
    
    private void signal_dep(String[] requete)
    {
        System.err.println(requete[1]);
        
        SendMsg("ACK#");
    }
    
    private void send_weight(String[] requete)
    {
        //TO DO MAJ des infos sur le container
        System.err.println(requete[1]);
        SendMsg("ACK#");
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
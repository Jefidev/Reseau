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
            return;
        }
        
        boolean terminer = false;
               
        while(!terminer)
        {
            parts = ReceiveMsg().split("#");
            switch (parts[0])
            {       
                case "INPUT_LORRY" :
                        inputLorry(parts);
                        break;
                case "INPUT_LORRY_WITHOUT_RESERVATION" :
                        inputLorryWithoutReserv(parts);
                        break;
                case "LIST_OPERATIONS" :
                        listOperation(parts);
                        break;
                case "LOGOUT" :
                    terminer = true;
                    break;
                    
                default :
                    terminer = true;
                    break;
            }
        }
        
        System.err.println("fin du runnable");
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
    
    private void inputLorry(String[] request)
    {
        
        ResultSet rs = null;
        
        try {
            rs = beanOracle.selection("ID_CONTAINER", "CONTAINERS", "RESERVATION = '"+request[1]+"'");
        } catch (SQLException ex) {
            SendMsg("ERR#Base de donnée inaccessible");
            System.err.println("Erreur SQL exception input lorry");
            return;
        }
        
        boolean isResultEmpty = true;
        int nbrElemParc = 0;
        
        String[] idList =  request[2].split("@");
        System.out.println(request[2]);
        try {
            while(rs.next())
            {
                nbrElemParc++;
                isResultEmpty = false;
                String curId = null;
                String id = rs.getString("ID_CONTAINER");
                boolean invalidContainerID = true;
                for(String s : idList)
                {
                    System.out.println(s +"---"+id);
                    curId = s;
                    if(s.equals(id))
                    {
                        invalidContainerID = false;
                        break;
                    }
                }
                if(invalidContainerID)
                {
                    SendMsg("ERR#Le container " + curId +" ne fait pas partie de la reservation" );
                    return;
                }
                
                if(nbrElemParc >= idList.length)
                    break;
            }
        } catch (SQLException ex) {
            SendMsg("ERR#Base de donnée inaccessible");
            System.err.println("Erreur SQL exception input lorry resultat");
            return;
        }
        
        if(isResultEmpty)
        {
            SendMsg("ERR#Le numero de reservation demande n'existe pas");
            return;
        }
        
        try {
            rs = beanOracle.selection("X, Y", "PARC", "ETAT=1");
        } catch (SQLException ex) {
            SendMsg("ERR#Base de donnée inaccessible");
            System.err.println("Erreur SQL exception input lorry" + ex.getStackTrace());
            return;
        }
        
        String reponse = "ACK#";
        
        try
        {
            for(int i = 0; i < idList.length ; i++)
            {
                if(rs.next())
                {
                    reponse = reponse + idList[i] + "==>("+rs.getString("X")+";"+rs.getString("Y")+")@";
                }
                else
                {
                    SendMsg("ERR#Erreur pas assez de places reservees");
                    return;
                }
            }
        }
        catch(SQLException ex){
            SendMsg("ERR#Base de donnée inaccessible");
            System.err.println("Erreur SQL exception input lorry" + ex.getStackTrace());
            return;
        }
        
        SendMsg(reponse);
    }
    
    
    private void listOperation(String[] request)
    {
        if(request[1].equals("societe"))
        {
            
        }
        
        SendMsg("ERR#Recherche impossible sur ce critère");
    }
    
    
    private void inputLorryWithoutReserv(String[] request)
    {    
        ResultSet rs = null;
        
        String[] idList =  request[2].split("@");
        
        try {
            rs = beanOracle.selection("X, Y", "PARC", "ETAT=0");
        } catch (SQLException ex) {
            SendMsg("ERR#Base de donnée inaccessible");
            System.err.println("Erreur SQL exception input lorry" + ex.getStackTrace());
            return;
        }
        
        String reponse = "ACK#";
        
        try
        {
            for(int i = 0; i < idList.length ; i++)
            {
                if(rs.next())
                {
                    reponse = reponse + idList[i] + "==>("+rs.getString("X")+";"+rs.getString("Y")+")@";
                }
                else
                {
                    SendMsg("ERR#Erreur pas assez de places");
                    return;
                }
            }
        }
        catch(SQLException ex){
            SendMsg("ERR#Base de donnée inaccessible");
            System.err.println("Erreur SQL exception input lorry" + ex.getStackTrace());
            return;
        }
        
        SendMsg(reponse);
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

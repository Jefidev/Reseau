/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_compta;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import newBean.BeanBDAccess;
import newBean.connexionException;
import newBean.requeteException;

/**
 *
 * @author John
 */
public class Runnable_SAMOP implements Runnable{
    
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracle;
    
    
    public Runnable_SAMOP(Socket s)
    {
        CSocket = s;

        try
        {
            dis = new DataInputStream(new BufferedInputStream(CSocket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(CSocket.getOutputStream()));
        }
        catch(IOException e)
        {
            System.err.println("Runnable_SAMOP : Host non trouvé : " + e);
        }
        
        beanOracle = new BeanBDAccess();
        try
        {
            beanOracle.connexionOracle("localhost", 1521, "COMPTA", "COMPTA", "XE");
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Runnable_SAMOP : Class not found " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.err.println("Runnable_SAMOP : SQL Exception (Oracle)" + ex.getMessage()); 
        }
        catch (connexionException ex)
        {
            System.err.println("Runnable_SAMOP : " + ex.getNumException() + " -- " + ex.getMessage());
        }
    }
    
    @Override
    public void run() {
        
        String[] parts = (ReceiveMsg()).split("#");
        
        if(parts[0].equals("LOGIN_SSL"))
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
                case "LAUNCH_PAYEMENT" :
                        launchPayement(parts);
                        break;
                        
                case "LAUNCH_PAYEMENTS" :
                        launchPayements();
                        break;
                
                case "ASK_PAYEMENTS" :
                        askPayements(parts);
                        break;

                default :
                        terminer = true;
                        break;
            }
        }
        
        System.err.println("Runnable_SAMOP : Fin du runnable");
        
        try
        {
            CSocket.close();
        }
        catch (IOException ex)
        {
            System.err.println("Runnable_SAMOP : Erreur de close : " + ex.getMessage());
        }
    }
    
    
    private boolean login(String[] requete)
    {
        String login = requete[1];
        String password = requete[2];
        
        try {
            //Recuperation des infos dans la BD
            ResultSet rs = beanOracle.selection("PASSWORD, FONCTION", "PERSONNEL", "LOGIN = '" + login + "'");
            
            if(!rs.next())
            {
                SendMsg("ERR#Login incorrecte");
                return false;
            }
            
            String pwdBD = rs.getString("PASSWORD");
            String fonction = rs.getString("FONCTION");
            
            if(!fonction.equalsIgnoreCase("chef-comptable"))
            {
                SendMsg("ERR#Seul les chefs comptable ont acces à cette application");
                return false;
            }
            
            if(!password.equals(pwdBD))
            {
                SendMsg("ERR#Mot de pass invalide");
                return false;
            }
            
            SendMsg("OK");
            return true;
            
        } catch (SQLException ex) {
            SendMsg("ERR#Base de donnee inaccessible. Reessaye plus tard");
            return false;
        }
    }
    
    
    
    
    private void launchPayement(String[] requete)
    {
        String login = requete[1];
        
        ResultSet rs;
        try {
            rs = beanOracle.selection("*", "PERSONNEL", "LOGIN = '"+login+"'");
            
            if(!rs.next())
            {
                SendMsg("ERR#Aucun employe pour ce login");
                return;
            }
            
            String where = "LOGIN = '"+login+"' AND FLAG_FICHE_ENVOYEE = 0 AND FLAG_VALIDE = 1";
            rs = beanOracle.selection("*", "SALAIRES", where);
            String resultat = "";
            
            while(rs.next())
            {
                resultat += rs.getString("LOGIN") + "  /  ";
                resultat += rs.getString("MONTANT_BRUT")+ "  /  ";
                resultat += rs.getString("MOIS_ANNEE")+ "#";
            }
            
            if(resultat.isEmpty())
            {
                SendMsg("ERR#Aucun payements a effectuer pour ce login");
                return;
            }
            
            //MAJ des salaires
            
            HashMap update = new HashMap();
            
            update.put("FLAG_FICHE_ENVOYEE", 1);
            update.put("FLAG_SALAIRE_VERSE", 1);
            
            beanOracle.miseAJour("SALAIRES", update, where);
            
            SendMsg("OK#"+resultat);
            
        } catch (SQLException | requeteException ex) {
            ex.printStackTrace();
            SendMsg("ERR#Erreur BD");
        } 
    }
    
    
    private void launchPayements()
    {
        
    }
    
    
    private void askPayements(String[] requete)
    {
        //On va recuperer toutes les fiches de salaire pour une date 
        String where = "MOIS_ANNEE = '"+requete[1]+"' AND FLAG_FICHE_ENVOYEE = 1";
        
        try {
            ResultSet rs = beanOracle.selection("*", "SALAIRES", where);
            
            String listResultat = "";
            while(rs.next())
            {
                listResultat += rs.getString("LOGIN") + "   /   ";
                listResultat += rs.getString("MONTANT_BRUT")+"#";
            }
            
            if(listResultat.isEmpty())
            {
                SendMsg("ERR#Aucun salaire verse ce mois");
                return;
            }
            
            SendMsg("OK#"+listResultat);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            SendMsg("ERR#Base de donnees hors ligne");
        }
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
            System.err.println("Runnable_SAMOP : Erreur d'envoi de msg (IO) : " + e);
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
            System.err.println("Runnable_SAMOP : Erreur de reception de msg (IO) : " + e);
        }
            
        return message.toString();
    }
    
}

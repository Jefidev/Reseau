/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_trafic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class client_CHAMAP extends Thread{
    
    private Socket cliSock;
    private String ip_serveur;
    private int port_serveur;
    private String login;
    private String pwd;
    
    private DataInputStream dis;
    private DataOutputStream dos;
    
    public client_CHAMAP(String ip, int p, String l, String pw)
    {
        ip_serveur = ip;
        port_serveur = p;
        
        login =l;
        pwd =pw;
    }
    
    @Override
    public void run()
    {
        //Connexion au serveur compta
        System.err.println("lancement client chamap");
        
        try
        {
            cliSock = new Socket(ip_serveur, port_serveur);
            System.out.println(cliSock.getInetAddress().toString());
            dis = new DataInputStream(new BufferedInputStream(cliSock.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(cliSock.getOutputStream()));
        }
        catch(UnknownHostException e)
        {
            System.err.println("CHAMAP : Host non trouvé : " + e);
            return;
        }
        catch(IOException e)
        {
            System.err.println("CHAMAP : Pas de connexion ? : " + e);
            return;
        }
        
        //Envoie du digest
        // sels
        long temps = (new Date()).getTime();
        double aleatoire = Math.random();
        
        byte[] pwdDigest = SaltDigest(pwd, temps, aleatoire);
        SendMsg("LOGIN");
        try {
            dos.writeUTF(login);
            dos.writeLong(temps);
            dos.writeDouble(aleatoire);
            dos.writeInt(pwdDigest.length);                    
            dos.write(pwdDigest);
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(client_CHAMAP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
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
    
    
    public static byte[] SaltDigest(String msg, long temps, double aleatoire)
    {       
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(msg.getBytes());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream bdos = new DataOutputStream(baos);
            bdos.writeLong(temps);
            bdos.writeDouble(aleatoire);
            md.update(baos.toByteArray());
            byte[] digest = md.digest();
            return digest;
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : SaltDigest : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : SaltDigest : NoSuchAlgorithmException : " + ex.getMessage());
        }
        
        return null;
    }
    
}

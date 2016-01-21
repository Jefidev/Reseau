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
import java.net.UnknownHostException;

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
        
        
    }
    
}

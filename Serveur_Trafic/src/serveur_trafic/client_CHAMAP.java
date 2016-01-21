/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_trafic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author John
 */
public class client_CHAMAP extends Thread{
    
    private Socket cliSock;
    private String ip_serveur;
    private int port_serveur;
    
    private DataInputStream dis;
    private DataOutputStream dos;
    
    public client_CHAMAP(String ip, int p)
    {
        ip_serveur = ip;
        port_serveur = p;
    }
    
    @Override
    public void run()
    {
        //Connexion au serveur compta
    }
    
}

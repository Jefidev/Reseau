/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_chat_pfm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class Serveur_Chat_PFM extends Thread{

    private int port;
    private SourceTaches tachesAExecuter;
    private ServerSocket SSocket = null;
    private int nbrThreads;
    
    private int port_upd;
    private String ip_udp;
    
    public Serveur_Chat_PFM(int p, SourceTaches st, int nt,String ipu,int pu)
    {
        port = p;
        tachesAExecuter = st;
        nbrThreads = nt;
        
        port_upd = pu;
        ip_udp = ipu;
        
        MulticastSocket udp_sock = null;
        try {
            udp_sock = new MulticastSocket(pu);
            udp_sock.joinGroup(InetAddress.getByName(ipu));
        } catch (IOException ex) {
            System.err.println("Serveur chat : Impossible de se connecter au chat");
        }
        ThreadReception backgroundReceptionTask = new ThreadReception(udp_sock);
        backgroundReceptionTask.start();
    }
    
    public void run()
    {
        try
        {
            SSocket = new ServerSocket(port);
        }
        catch(IOException e)
        {
            System.err.println("ServeurBateau : Erreur de la creation de socket  : " + e);
        }
        
        for(int i = 0; i < nbrThreads; i++)
        {
            ThreadTraitement tt = new ThreadTraitement(tachesAExecuter);
            tt.start();
        }
        
        Socket CSocket = null;
        
        while(!isInterrupted())
        {
            try
            {
                System.out.println("Serveur chat : attend un client");
                CSocket = SSocket.accept();
                System.out.println("Serveur chat : Client dispo");
            }
            catch(IOException e)
            {
                System.err.println("Serveur chat : Erreur d'accept : " + e);
            }

            tachesAExecuter.recordTache(new RunnableTraitement(CSocket, port_upd,ip_udp));
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*Fichier properties*/
        String pathProperties = "serveurChat.properties";
        
        Properties paramCo = new Properties();
        
        try
        {
            FileInputStream Oread = new FileInputStream(pathProperties);
            paramCo.load(Oread);
        }
        catch(FileNotFoundException ex)
        {
            try 
            {
                FileOutputStream Oflux = new FileOutputStream(pathProperties);
                
                paramCo.setProperty("PORT_SERVEUR", "31047");
                paramCo.setProperty("PORT_UDP", "31049");
                paramCo.setProperty("IP_UDP", "224.0.0.1");
                try {
                    paramCo.store(Oflux, null);
                }
                catch (IOException ex1) {
                    System.err.println(ex1.getStackTrace());
                    System.exit(0);
                }
            } 
            catch (FileNotFoundException ex1) 
            {
                System.err.println(ex1.getStackTrace());
                System.exit(0);
            }
            
        }
        catch(IOException ex)
        {
            System.err.println(ex.getStackTrace());
            System.exit(0);
        }
        
        String ipu =  paramCo.getProperty("IP_UDP");
        int pu = Integer.parseInt(paramCo.getProperty("PORT_UDP"));
        int p = Integer.parseInt(paramCo.getProperty("PORT_SERVEUR"));
        
        Serveur_Chat_PFM sc = new Serveur_Chat_PFM(p, new ListeTaches(), 5,ipu,pu);
        sc.start();
    }
    
}

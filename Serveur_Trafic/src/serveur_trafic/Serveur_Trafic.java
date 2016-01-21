/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_trafic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author John
 */
public class Serveur_Trafic extends Thread{

    private int port;
    private SourceTaches tachesAExecuter;
    private ServerSocket SSocket = null;
    private int nbrThreads;
   
    public Serveur_Trafic(int p, SourceTaches st, int nt)
    {
        port = p;
        tachesAExecuter = st;
        nbrThreads = nt;
    }
    
    public void run()
    {
        try
        {
            SSocket = new ServerSocket(port);
        }
        catch(IOException e)
        {
            System.err.println("Serveur chat : Erreur de la creation de socket  : " + e);
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
                System.out.println("Serveur trafic : attend un client sur le port " + port);
                CSocket = SSocket.accept();
                System.out.println("Serveur trafic : Client dispo");
            }
            catch(IOException e)
            {
                System.err.println("Serveur chat : Erreur d'accept : " + e);
            }

            tachesAExecuter.recordTache(new RunnableTraitementEntree(CSocket));
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
                
                paramCo.setProperty("PORT_SERVEUR_IN", "31042");
                paramCo.setProperty("PORT_SERVEUR_RES", "31041");
                paramCo.setProperty("IP_SERVER_COMPTA", "localhost");
                paramCo.setProperty("PORT_SERVER_COMPTA", "31048");

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
        
        int p = Integer.parseInt(paramCo.getProperty("PORT_SERVEUR_IN"));
        int pb = Integer.parseInt(paramCo.getProperty("PORT_SERVEUR_RES"));
        
        String ip = paramCo.getProperty("IP_SERVER_COMPTA");
        int pc = Integer.parseInt(paramCo.getProperty("PORT_SERVER_COMPTA"));
        
        Serveur_Trafic sc = new Serveur_Trafic(p, new ListeTaches(), 5);
        sc.start();
        
        Serveur_BOOMAP sb = new Serveur_BOOMAP(pb, new ListeTaches(), 5);
        sb.start();
        
        client_CHAMAP ch = new client_CHAMAP(ip, pc);
        ch.run();
    }
    
}

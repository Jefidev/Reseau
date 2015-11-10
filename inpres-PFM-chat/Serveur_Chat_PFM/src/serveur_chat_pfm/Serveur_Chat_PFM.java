/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_chat_pfm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author John
 */
public class Serveur_Chat_PFM extends Thread{

    private int port;
    private SourceTaches tachesAExecuter;
    private ServerSocket SSocket = null;
    private int nbrThreads;
    
    public Serveur_Chat_PFM(int p, SourceTaches st, int nt)
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

            tachesAExecuter.recordTache(new RunnableTraitement(CSocket));
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Serveur_Chat_PFM sc = new Serveur_Chat_PFM(31047, new ListeTaches(), 5);
        sc.start();
    }
    
}

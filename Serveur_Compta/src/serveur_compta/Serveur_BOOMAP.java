package serveur_compta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur_BOOMAP extends Thread{
    private int port;
    private SourceTaches tachesAExecuter;
    private ServerSocket SSocket = null;
    private int nbrThreads;

    public Serveur_BOOMAP(int p, SourceTaches st, int nt)
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
                System.out.println("Serveur BOOMAP : attend un client sur le port : " + port);
                CSocket = SSocket.accept();
                System.out.println("Serveur BOOMAP : Client dispo");
            }
            catch(IOException e)
            {
                System.err.println("Serveur chat : Erreur d'accept : " + e);
            }

            tachesAExecuter.recordTache(new RunnableBOOMAP(CSocket));
        }
    }
}

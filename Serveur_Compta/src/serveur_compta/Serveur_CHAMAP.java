package serveur_compta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur_CHAMAP extends Thread
{
    private final int port;
    private final SourceTaches tachesAExecuter;
    private ServerSocket SSocket = null;
    private final int nbrThreads;

    public Serveur_CHAMAP(int p, SourceTaches st, int nt)
    {
        port = p;
        tachesAExecuter = st;
        nbrThreads = nt;
    }
    
    @Override
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

            tachesAExecuter.recordTache(new RunnableCHAMAP(CSocket));
        }
    }
}

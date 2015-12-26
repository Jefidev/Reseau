package serveur_compta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;


public class Serveur_BISAMAP extends Thread
{
    private final int port;
    private final SourceTaches tachesAExecuter;
    private ServerSocket SSocket = null;
    private final int nbrThreads;
   
    public Serveur_BISAMAP(int p, SourceTaches st, int nt)
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
                System.out.println("Serveur trafic : attend un client sur le port " + port);
                CSocket = SSocket.accept();
                System.out.println("Serveur trafic : Client dispo");
            }
            catch(IOException e)
            {
                System.err.println("Serveur chat : Erreur d'accept : " + e);
            }

            tachesAExecuter.recordTache(new RunnableBISAMAP(CSocket));
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
        
        Serveur_BISAMAP sc = new Serveur_BISAMAP(p, new ListeTaches(), 5);
        sc.start();
        
        Serveur_CHAMAP sb = new Serveur_CHAMAP(pb, new ListeTaches(), 5);
        sb.start();
    }
}

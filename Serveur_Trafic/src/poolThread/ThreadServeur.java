/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolThread;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Jerome
 */
public class ThreadServeur extends Thread{
    
    private int port;
    private SourceTaches taches;
    private ConsoleServeur trace;
    private ServerSocket SSocket = null;
    private int nbrThrads = 5; //à recupérer dans un properties
    
    public ThreadServeur(int p, SourceTaches st, ConsoleServeur cs)
    {
        port = p;
        taches = st;
        trace = cs;
    }
    
    public void run()
    {
        try
        {
            SSocket = new ServerSocket(port);
        }
        catch(IOException e)
        {
            System.err.println("Erreur de la creation de socket  : " + e);
        }
        
        for(int i = 0; i < nbrThrads; i++)
        {
            ThreadClient tc = new ThreadClient(taches, "Thread n° " + i);
            tc.start();
        }
        
    }
}

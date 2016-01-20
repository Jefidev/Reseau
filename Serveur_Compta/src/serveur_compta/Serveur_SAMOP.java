/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_compta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author Jerome
 */
public class Serveur_SAMOP extends Thread{
    
    private final int port;
    private final SourceTaches tachesAExecuter;
    private SSLServerSocket SSocket = null;
    private final int nbrThreads;
   
    public Serveur_SAMOP(int p, SourceTaches st, int nt)
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
            //Ouverture d'une socket SSL
            KeyStore ServerKs = KeyStore.getInstance("JKS");
            FileInputStream fichierKeyStore  = new FileInputStream("keystores/serveur_ssl_KS");
            
            char[] password_keystore = "sslserveur".toCharArray();
            
            ServerKs.load(fichierKeyStore, password_keystore);
            
            //Recuperation du contexte SSL
            SSLContext SllC = SSLContext.getInstance("SSLv3");
            
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ServerKs, password_keystore);
            
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ServerKs);
            
            //Init du contexte SSL
            SllC.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            
            SSLServerSocketFactory socketfactory = SllC.getServerSocketFactory();
            
            SSocket = (SSLServerSocket) socketfactory.createServerSocket(port);

        }
        catch(IOException e)
        {
            System.err.println("Serveur_SAMOP : Erreur de la creation de socket  : " + e);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException ex) {
            Logger.getLogger(Serveur_SAMOP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(Serveur_SAMOP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(Serveur_SAMOP.class.getName()).log(Level.SEVERE, null, ex);
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
                System.out.println("Serveur_SAMOP : Attente d'un client sur le port " + port);
                CSocket = SSocket.accept();
                System.out.println("Serveur_SAMOP : Client dispo");
            }
            catch(IOException e)
            {
                System.err.println("Serveur_SAMOP : Erreur d'accept : " + e);
            }

            tachesAExecuter.recordTache(new Runnable_BISAMAP(CSocket));
        }
    }
}

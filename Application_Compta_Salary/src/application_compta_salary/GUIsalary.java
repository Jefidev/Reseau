/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application_compta_salary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
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
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author Jerome
 */
public class GUIsalary extends javax.swing.JFrame {
    
    private SSLSocket cliSock;
    private String ip_serveur;
    private int port_serveur;
    
    private DataInputStream dis;
    private DataOutputStream dos;

    /**
     * Creates new form GUIsalary
     */
    public GUIsalary(String ip, int p) {
        initComponents();
        
        ip_serveur = ip;
        port_serveur = p;
    }
    
    
    public String connect()
    {
        try
        {
            //Recuperation du keystore
            KeyStore clientKS = KeyStore.getInstance("JKS");
            
            //Chargement du fichier keystore
            FileInputStream fichierKeyStore  = new FileInputStream("keystores/client_ssl_KS");
            char[] password = "sslclient".toCharArray();
            clientKS.load(fichierKeyStore, password);
            
            //Contexte SSL
            SSLContext sslContexte =  SSLContext.getInstance("SSLv3");
            
            //Factory nécessaires
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(clientKS, password);
            
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(clientKS);
            
            sslContexte.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            
            //Factory SSl
            SSLSocketFactory socketFactory = sslContexte.getSocketFactory();
            
            cliSock = (SSLSocket) socketFactory.createSocket(ip_serveur, port_serveur);
            
            dis = new DataInputStream(new BufferedInputStream(cliSock.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(cliSock.getOutputStream()));
        }
        catch(UnknownHostException e)
        {
            return "Serveur indisponible";
        }
        catch(IOException e)
        {
            return "Connexion au serveur échouée";
        } catch (KeyStoreException ex) {
            return "Certificats introuvables";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GUIsalary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(GUIsalary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(GUIsalary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(GUIsalary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "ok";
    }
    
    public void logout()
    {
        try {
            cliSock.close();
        } catch (IOException ex) {
            System.err.println("Application salary : erreur fermeture client : " + ex.getStackTrace());
        }
    }
    
    
    public void SendMsg(String msg)
    {
        String chargeUtile = msg;
        int taille = chargeUtile.length();
        StringBuffer message = new StringBuffer(String.valueOf(taille) + "#" + chargeUtile);
            
        try
        {               
            dos.write(message.toString().getBytes());
            dos.flush();
        }
        catch(IOException e)
        {
            System.err.println("RunnableTraitement : Erreur d'envoi de msg (IO) : " + e);
        }
    }
    
    /* Réception d'un message du client */
    public String ReceiveMsg()
    {
        byte b;
        StringBuffer taille = new StringBuffer();
        StringBuffer message = new StringBuffer();
        
        try
        {
            while ((b = dis.readByte()) != (byte)'#')
            {                   
                if (b != (byte)'#')
                    taille.append((char)b);
            }
                
            for (int i = 0; i < Integer.parseInt(taille.toString()); i++)
            {
                b = dis.readByte();
                message.append((char)b);
            }  
        }
        catch(IOException e)
        {
            System.err.println("RunnableTraitement : Erreur de reception de msg (IO) : " + e);
        }
            
        return message.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connexionPanel = new PanelPackage.ConnexionPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(connexionPanel, "connexion");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIsalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIsalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIsalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIsalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                /*Fichier properties*/
                String pathProperties = "appComptaSalary.properties";

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
                        paramCo.setProperty("IP_SERVEUR", "localhost");
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
                
                String ip = paramCo.getProperty("IP_SERVEUR");
                int p = Integer.parseInt(paramCo.getProperty("PORT_SERVEUR"));
                
                
                new GUIsalary(ip, p).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelPackage.ConnexionPanel connexionPanel;
    // End of variables declaration//GEN-END:variables
}

package application_trafic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jerome
 */
public class GUI_Trafic extends javax.swing.JFrame {
    
    private Socket cliSock;
    private String ip_serveur;
    private int port_serveur;
    
    private DataInputStream dis;
    private DataOutputStream dos;

    /**
     * Creates new form GUI_Trafic
     */
    public GUI_Trafic(String ip, int p) {
        initComponents();
        
        ip_serveur = ip;
        port_serveur = p;
    }
    
    public void connect()
    {
        try
        {
            cliSock = new Socket(ip_serveur, port_serveur);
            System.out.println(cliSock.getInetAddress().toString());
            dis = new DataInputStream(new BufferedInputStream(cliSock.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(cliSock.getOutputStream()));
        }
        catch(UnknownHostException e)
        {
            login_panel.setErreur("Serveur indisponible");
            System.err.println("Application trafic : Host non trouvé : " + e);
        }
        catch(IOException e)
        {
            login_panel.setErreur("Connexion au serveur échouée");
            System.err.println("Application trafic : Pas de connexion ? : " + e);
        }
    }
    
    public void logout()
    {
        try {
            cliSock.close();
        } catch (IOException ex) {
            System.err.println("Application trafic : erreur fermeture client : " + ex.getStackTrace());
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

        login_panel = new application_trafic.Login_Panel();
        menu_Panel = new application_trafic.Menu_Panel();
        avec_reservation_panel = new application_trafic.Avec_reservation_panel();
        sans_reservation_panel1 = new application_trafic.Sans_reservation_panel();
        liste_Panel = new application_trafic.liste_Panel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(login_panel, "login");
        getContentPane().add(menu_Panel, "menu");
        getContentPane().add(avec_reservation_panel, "avecReservation");
        getContentPane().add(sans_reservation_panel1, "sansReservation");
        getContentPane().add(liste_Panel, "list");

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
            java.util.logging.Logger.getLogger(GUI_Trafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Trafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Trafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Trafic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                /*Fichier properties*/
                String pathProperties = "appTrafic.properties";

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

                        paramCo.setProperty("PORT_SERVEUR", "31042");
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
                new GUI_Trafic(ip, p).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private application_trafic.Avec_reservation_panel avec_reservation_panel;
    private application_trafic.liste_Panel liste_Panel;
    private application_trafic.Login_Panel login_panel;
    private application_trafic.Menu_Panel menu_Panel;
    private application_trafic.Sans_reservation_panel sans_reservation_panel1;
    // End of variables declaration//GEN-END:variables
}

package applic_mail;

import classLibrary.ThreadPooling;
import java.awt.CardLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Store;


public class GUI_Mail extends javax.swing.JFrame
{
    
    private String host;
    private Session sess;
    private Store mailStore;
    private String mailAdress;
    private String curUser;

    
    public GUI_Mail() {
        initComponents();
        readProp();
        
        Properties prop = System.getProperties();
        prop.put("mail.pop3.host", host);
        prop.put("mail.smtp.host", host);
        prop.put("mail.disable.top", true);
        
        sess = Session.getDefaultInstance(prop, null);
    }
    
    public void setMailAdress(String ma)
    {
        mailAdress = ma;
    }
    
    public String getHost()
    {
        return host;
    }
    
    public Session getSession()
    {
        return sess;
    }
    
    public void setUser(String u)
    {
        curUser = u;
        mailAdress = u + "@u2.tech.hepl.local";
        
        //Creation d'un directory pour les pieces jointes du user si c'est necessaire
        inboxPanel.createDirectory(u);
    }
    
    public String getMailAdress()
    {
        return mailAdress;
    }
    
    //Le store reçu = connexion acceptée
    public void setStore(Store s)
    {
        mailStore = s;
        
        //On envoie le store au panneau inbox (le store contient, entre autres, les messages reçus)
        inboxPanel.setStore(s);
        inboxPanel.refreshMailList();
        
        //On lance le thread qui va voir si des messages ont été reçus toutes les 5 min.
        ThreadPooling tp = new ThreadPooling(inboxPanel);
        tp.start();
    }
    
    //Methode permettant de changer le panel affiché
    public void changeLayout(String nomCard)
    {
        CardLayout card = (CardLayout) this.getContentPane().getLayout();
        card.show(this.getContentPane(), nomCard);
    }
    
    public void readProp()
    {
        /* Fichier properties */
        String pathProperties = "mailProp.txt";
        
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
                
                paramCo.setProperty("MAIL_HOST", "u2");
                
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
        
        host = paramCo.getProperty("MAIL_HOST");
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connexionPanel = new applic_mail.connexionPanel();
        inboxPanel = new applic_mail.inboxPanel();
        sendMessagePanel = new applic_mail.sendMessagePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(connexionPanel, "connexion");
        getContentPane().add(inboxPanel, "inbox");
        getContentPane().add(sendMessagePanel, "nouveauMessage");

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
            java.util.logging.Logger.getLogger(GUI_Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Mail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Mail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private applic_mail.connexionPanel connexionPanel;
    private applic_mail.inboxPanel inboxPanel;
    private applic_mail.sendMessagePanel sendMessagePanel;
    // End of variables declaration//GEN-END:variables
}

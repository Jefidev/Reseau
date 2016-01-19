package application_compta;

import java.awt.CardLayout;
import java.net.*;
import javax.crypto.SecretKey;


public class ApplicationCompta extends javax.swing.JFrame
{
    public static Socket cliSock = null;
    public Boolean isConnected = false;
    public SecretKey CleSecreteChiffrement, CleSecreteHMAC;
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new application_compta.Menu();
        ValidationFacture = new application_compta.ValidationFacture();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(Menu, "Menu");
        getContentPane().add(ValidationFacture, "ValidationFacture");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public ApplicationCompta()
    {
        initComponents();
        
        this.setTitle("Application Compta");
        application_compta.Utility.InitialisationFlux();

        // Lancement du login
        (new application_compta.Login(this, true)).setVisible(true);
        System.out.println("isConnected = " + isConnected);
        if (!isConnected)
            System.exit(0);
    }
    
    
    public void ChangePanel(String newPanel)
    {
        CardLayout card = (CardLayout)this.getContentPane().getLayout();
        card.show(this.getContentPane(), newPanel);
    }

        
    public static void main(String args[])
    {
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
            java.util.logging.Logger.getLogger(ApplicationCompta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicationCompta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicationCompta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationCompta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ApplicationCompta().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private application_compta.Menu Menu;
    private application_compta.ValidationFacture ValidationFacture;
    // End of variables declaration//GEN-END:variables
}

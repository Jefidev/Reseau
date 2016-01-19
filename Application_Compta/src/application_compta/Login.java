package application_compta;

import java.io.*;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;


public class Login extends javax.swing.JDialog
{
    public Login(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.setTitle("Login");
        ErrorLabel.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginLabel = new javax.swing.JLabel();
        PwdLabel = new javax.swing.JLabel();
        LoginTF = new javax.swing.JTextField();
        PwdPF = new javax.swing.JPasswordField();
        ConnexionButton = new javax.swing.JButton();
        ErrorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LoginLabel.setText("Login :");

        PwdLabel.setText("Mot de passe :");

        ConnexionButton.setText("Connexion");
        ConnexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnexionButtonActionPerformed(evt);
            }
        });

        ErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErrorLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorLabel.setText("Connexion refusée !");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PwdLabel)
                                .addGap(18, 18, 18)
                                .addComponent(PwdPF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LoginLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LoginTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ErrorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ConnexionButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginLabel)
                    .addComponent(LoginTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PwdLabel)
                    .addComponent(PwdPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConnexionButton)
                    .addComponent(ErrorLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void ConnexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnexionButtonActionPerformed

        try
        {   
            if (PwdPF.getPassword().length == 0 || LoginTF.getText().isEmpty())
                return;
            
            // sels
            long temps = (new Date()).getTime();
            double aleatoire = Math.random();
            String Password = new String(PwdPF.getPassword());

            // envoi
            byte[] pwdDigest = Crypto.saltDigest(Password, temps, aleatoire);
            Utility.SendMsg(ProtocoleBISAMAP.LOGIN, "");
            Utility.dos.writeUTF(LoginTF.getText());
            Utility.dos.writeLong(temps);
            Utility.dos.writeDouble(aleatoire);
            Utility.dos.writeInt(pwdDigest.length);                    
            Utility.dos.write(pwdDigest);
            Utility.dos.flush();
            
            // réponse
            String reponse = Utility.ReceiveMsg();  
            String[] parts = reponse.split("#");
            
            if (parts[0].equals("OUI"))
            {   
                // Lecture des données
                int longueur = Utility.dis.readInt();
                byte[] CleSecreteChiffrementChiffreeAsym = new byte[longueur];
                Utility.dis.readFully(CleSecreteChiffrementChiffreeAsym);        
                longueur = Utility.dis.readInt();
                byte[] CleSecreteHMACChiffreeAsym = new byte[longueur];
                Utility.dis.readFully(CleSecreteHMACChiffreeAsym);
                
                byte[] CleSecreteChiffrementDechiffree = Crypto.asymDecrypt(CleSecreteChiffrementChiffreeAsym, "KSAppCompta.p12", "azerty", "azerty", "AppCompta");
                byte[] CleSecreteHMACDechiffree = Crypto.asymDecrypt(CleSecreteHMACChiffreeAsym, "KSAppCompta.p12", "azerty", "azerty", "AppCompta");
                
                ApplicationCompta a = (ApplicationCompta) this.getParent();
                a.CleSecreteChiffrement = new SecretKeySpec(CleSecreteChiffrementDechiffree, 0, CleSecreteChiffrementDechiffree.length, "DES");
                a.CleSecreteHMAC = new SecretKeySpec(CleSecreteHMACDechiffree, 0, CleSecreteHMACDechiffree.length, "DES");
                
                System.out.println("=====>> CleSecreteChiffrement = " + a.CleSecreteChiffrement);
                System.out.println("=====>> CleSecreteHMAC = " + a.CleSecreteHMAC);
                
                a.isConnected = true;
                this.dispose();
            }
            else
                ErrorLabel.setVisible(true);
        }
        catch (IOException ex)
        {
            System.err.println("Login : IOException : " + ex.getMessage());
        }
    }//GEN-LAST:event_ConnexionButtonActionPerformed

    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnexionButton;
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JTextField LoginTF;
    private javax.swing.JLabel PwdLabel;
    private javax.swing.JPasswordField PwdPF;
    // End of variables declaration//GEN-END:variables
}

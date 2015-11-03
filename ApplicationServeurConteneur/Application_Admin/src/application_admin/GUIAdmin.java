/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application_admin;

import java.io.*;
import java.net.Socket;


/**
 *
 * @author Jerome
 */
public class GUIAdmin extends javax.swing.JFrame {
    
    
    private Socket cliSocket;
    
    private DataInputStream dis;
    private DataOutputStream dos;
    
    /**
     * Creates new form GUIAdmin
     */
    public GUIAdmin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        LoginTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        connexionButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        pauseButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listTextArea = new javax.swing.JTextArea();
        listerButton = new javax.swing.JButton();
        ipLabel = new javax.swing.JLabel();
        ipTextField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        portTextField = new javax.swing.JTextField();
        ServeurStatusLabel = new javax.swing.JLabel();
        continuerButton = new javax.swing.JButton();
        reponseLabel = new javax.swing.JLabel();
        secondesTextField = new javax.swing.JTextField();
        secondesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginLabel.setText("Login ");

        passwordTextField.setText("Mot de passe");

        connexionButton.setText("Connexion");
        connexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionButtonActionPerformed(evt);
            }
        });

        stopButton.setText("STOP");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        pauseButton1.setText("PAUSE");
        pauseButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButton1ActionPerformed(evt);
            }
        });

        listTextArea.setColumns(20);
        listTextArea.setRows(5);
        jScrollPane1.setViewportView(listTextArea);

        listerButton.setText("LISTER CLIENTS");
        listerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listerButtonActionPerformed(evt);
            }
        });

        ipLabel.setText("IP ");

        portLabel.setText("port");

        ServeurStatusLabel.setText("Status du serveur : ");

        continuerButton.setText("CONTINUER");
        continuerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuerButtonActionPerformed(evt);
            }
        });

        reponseLabel.setText("reponse serveur : ");

        secondesTextField.setText("0");

        secondesLabel.setText("nbr secondes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ServeurStatusLabel)
                                    .addComponent(reponseLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(secondesLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(secondesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(pauseButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(continuerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(ipLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ipTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordTextField)
                            .addComponent(portLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField)
                            .addComponent(portTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connexionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipLabel)
                    .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginLabel)
                    .addComponent(passwordTextField)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connexionButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pauseButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(continuerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secondesLabel)
                            .addComponent(secondesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ServeurStatusLabel)
                        .addGap(18, 18, 18)
                        .addComponent(reponseLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(listerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionButtonActionPerformed
        
        if(connexionButton.getText().equals("Connexion"))
        {
            try
            {
                cliSocket = new Socket(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
                dis = new DataInputStream(new BufferedInputStream(cliSocket.getInputStream()));
                dos = new DataOutputStream(new BufferedOutputStream(cliSocket.getOutputStream()));
            } 
            catch (IOException ex) 
            {
                System.err.println("Erreur gui admin connexion : " + ex);
            }

            String message = protocoleCSA.LOGIN + "#"+LoginTextField.getText()+"#"+passwordField.getText();

            SendMsg(message);

            System.out.println(ReceiveMsg()); 

            connexionButton.setText("Déconnexion");
        }
        else
        {
            connexionButton.setText("Connexion");
            deconnexion();
        }
    }//GEN-LAST:event_connexionButtonActionPerformed

    private void listerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listerButtonActionPerformed
        if(cliSocket == null)
            return;
        
        SendMsg(protocoleCSA.LISTCLIENT + "#");
        
        String str = ReceiveMsg();
        
        String split[] = str.split("#");
        
        listTextArea.setText("");
        for(int i = 1; i < split.length; i++)
            listTextArea.append(split[i] + "\n");
         
    }//GEN-LAST:event_listerButtonActionPerformed

    private void pauseButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButton1ActionPerformed
        SendMsg(protocoleCSA.PAUSE + "#");
        
        String str = ReceiveMsg();
    }//GEN-LAST:event_pauseButton1ActionPerformed

    private void continuerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuerButtonActionPerformed
        SendMsg(protocoleCSA.CONTINUER + "#");
        
        String str = ReceiveMsg();
    }//GEN-LAST:event_continuerButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        
        int sec = 0;
        
        try
        {
            sec = Integer.parseInt(secondesTextField.getText());
        }
        catch(NumberFormatException nfe)
        {
            sec = 0;
        }
        
        if(sec > 1000 || sec < 0)
            sec = 0;
        
        SendMsg(protocoleCSA.STOP + "#" + sec);
        
        String str = ReceiveMsg();
    }//GEN-LAST:event_stopButtonActionPerformed
    
    public void deconnexion()
    {
        SendMsg(protocoleCSA.LOGOUTCSA + "#");
        ReceiveMsg();
        try
        {
            dos.close();
            dis.close();
            cliSocket.close();
            cliSocket = null;
            System.out.println("ClientServeurBateau : Client déconnecté");
        }
        catch(IOException e)
        {
            System.err.println("ClientServeurBateau : Erreur de déconnexion : " + e);
        }
    }
    
    
    public void SendMsg(String chargeUtile)
    {
        int taille = chargeUtile.length();
        String message = String.valueOf(taille) + "#" + chargeUtile;
            
        try
        {           
            dos.write(message.getBytes());
            dos.flush();
        }
        catch(IOException e)
        {
            System.err.println("ClientServeurBateu : Erreur d'envoi de msg (IO) : " + e);
        }
    }
        
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
            System.err.println("ClientServeurBateau : Erreur de reception de msg (IO) : " + e);
        }
        
        reponseLabel.setText("reponse serveur : " + message.toString()); // à retirer
        return message.toString();
    }
    
    
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
            java.util.logging.Logger.getLogger(GUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField LoginTextField;
    private javax.swing.JLabel ServeurStatusLabel;
    private javax.swing.JButton connexionButton;
    private javax.swing.JButton continuerButton;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JTextField ipTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea listTextArea;
    private javax.swing.JButton listerButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordTextField;
    private javax.swing.JButton pauseButton1;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JLabel reponseLabel;
    private javax.swing.JLabel secondesLabel;
    private javax.swing.JTextField secondesTextField;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}
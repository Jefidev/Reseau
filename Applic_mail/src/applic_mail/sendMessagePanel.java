/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applic_mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class sendMessagePanel extends javax.swing.JPanel {

    /**
     * Creates new form sendMessagePanel
     */
    public sendMessagePanel() {
        initComponents();
        errorLabel.setVisible(false);
        okLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        sujetTextField = new javax.swing.JTextField();
        sujetLabel = new javax.swing.JLabel();
        envoyerButton = new javax.swing.JButton();
        destinataireTextField = new javax.swing.JTextField();
        destinataireLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        inboxButton = new javax.swing.JButton();
        okLabel = new javax.swing.JLabel();

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane1.setViewportView(messageTextArea);

        sujetLabel.setText("Sujet : ");

        envoyerButton.setText("Envoyer");
        envoyerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envoyerButtonActionPerformed(evt);
            }
        });

        destinataireLabel.setText("Destinataire : ");

        errorLabel.setForeground(new java.awt.Color(255, 0, 51));
        errorLabel.setText("jLabel1");

        inboxButton.setText("Inbox");
        inboxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inboxButtonActionPerformed(evt);
            }
        });

        okLabel.setForeground(new java.awt.Color(51, 204, 0));
        okLabel.setText("Le mail est envoyé");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(errorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okLabel)
                        .addGap(134, 134, 134)
                        .addComponent(inboxButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(envoyerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(destinataireLabel)
                            .addComponent(sujetLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destinataireTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sujetTextField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinataireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinataireLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sujetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sujetLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(envoyerButton)
                    .addComponent(errorLabel)
                    .addComponent(inboxButton)
                    .addComponent(okLabel))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void envoyerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envoyerButtonActionPerformed
    
        errorLabel.setVisible(false);
        okLabel.setVisible(false);
        
        if(sujetTextField.getText().isEmpty())
        {
            errorLabel.setText("Vous devez indiquer le sujet du mail");
            errorLabel.setVisible(true);
        }
        
        if(destinataireTextField.getText().isEmpty())
        {
            errorLabel.setText("Vous devez indiquer un destinataire");
            errorLabel.setVisible(true);
        }
        
        GUI_Mail container = (GUI_Mail)SwingUtilities.getWindowAncestor(this);
        Session sess = container.getSession();
        
        MimeMessage message = new MimeMessage(sess);
        
        try 
        {
            message.setFrom(new InternetAddress(container.getMailAdress()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireTextField.getText()));
            message.setSubject(sujetTextField.getText());
            message.setText(messageTextArea.getText());
            
            Transport.send(message);
        } 
        catch (AddressException ex) 
        {
           errorLabel.setText("Erreur d'envois");
           errorLabel.setVisible(true);
           ex.printStackTrace();
        } 
        catch (MessagingException ex) 
        {
            errorLabel.setText("Erreur d'envois");
           errorLabel.setVisible(true);
           ex.printStackTrace();
        }
        
        okLabel.setVisible(true);
        
    }//GEN-LAST:event_envoyerButtonActionPerformed

    private void inboxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inboxButtonActionPerformed
       GUI_Mail container = (GUI_Mail)SwingUtilities.getWindowAncestor(this);
       container.changeLayout("inbox");
    }//GEN-LAST:event_inboxButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel destinataireLabel;
    private javax.swing.JTextField destinataireTextField;
    private javax.swing.JButton envoyerButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton inboxButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JLabel okLabel;
    private javax.swing.JLabel sujetLabel;
    private javax.swing.JTextField sujetTextField;
    // End of variables declaration//GEN-END:variables
}

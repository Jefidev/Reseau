/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applic_mail;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class sendMessagePanel extends javax.swing.JPanel {
    
    private MimeBodyPart pieceJointe;

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
        ajouterButton = new javax.swing.JButton();
        pieceJointeLabel = new javax.swing.JLabel();

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

        ajouterButton.setText("Ajouter pièce jointe");
        ajouterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterButtonActionPerformed(evt);
            }
        });

        pieceJointeLabel.setText("jLabel1");

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
                            .addComponent(sujetTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ajouterButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pieceJointeLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ajouterButton)
                    .addComponent(pieceJointeLabel))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        
        MimeMessage messageComplet = new MimeMessage(sess);
        
        try 
        {
            messageComplet.setFrom(new InternetAddress(container.getMailAdress()));
            messageComplet.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataireTextField.getText()));
            messageComplet.setSubject(sujetTextField.getText());
            messageComplet.setSentDate(new Date());
            
            Multipart contenu = new MimeMultipart();
            
            MimeBodyPart corpsMessage = new MimeBodyPart();
            corpsMessage.setText(messageTextArea.getText());
            
            contenu.addBodyPart(corpsMessage);
            
            if(pieceJointe != null)
                contenu.addBodyPart(pieceJointe);
            
            Transport.send(messageComplet);
            pieceJointe = null;
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
        destinataireTextField.setText("");
        sujetTextField.setText("");
        messageTextArea.setText("");
        
    }//GEN-LAST:event_envoyerButtonActionPerformed

    private void inboxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inboxButtonActionPerformed

        errorLabel.setVisible(false);
        okLabel.setVisible(false);
        
        GUI_Mail container = (GUI_Mail)SwingUtilities.getWindowAncestor(this);
        container.changeLayout("inbox");
    }//GEN-LAST:event_inboxButtonActionPerformed

    private void ajouterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterButtonActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showDialog(this, "Choix pièce jointe");
        
        //Si aucun fichier n'a été selectionné on return
        if(jfc.getSelectedFile() == null)
            return;
        
        String filePath = jfc.getSelectedFile().getAbsolutePath();
        
        //Creation de la partie de message contenant la pièce jointe
        pieceJointe = new MimeBodyPart();
        DataSource ds = new FileDataSource(filePath);
        try {
            pieceJointe.setDataHandler(new DataHandler(ds));
            pieceJointe.setFileName(filePath);
        } catch (MessagingException ex) {
            Logger.getLogger(sendMessagePanel.class.getName()).log(Level.SEVERE, null, ex);//TO DO message erreur
        }
    }//GEN-LAST:event_ajouterButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouterButton;
    private javax.swing.JLabel destinataireLabel;
    private javax.swing.JTextField destinataireTextField;
    private javax.swing.JButton envoyerButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton inboxButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JLabel okLabel;
    private javax.swing.JLabel pieceJointeLabel;
    private javax.swing.JLabel sujetLabel;
    private javax.swing.JTextField sujetTextField;
    // End of variables declaration//GEN-END:variables
}

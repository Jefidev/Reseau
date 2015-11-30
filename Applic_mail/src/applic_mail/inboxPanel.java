/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applic_mail;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class inboxPanel extends javax.swing.JPanel {
    
    private Store storeMail;
    private Message[] messageList;

    /**
     * Creates new form inboxPanel
     */
    public inboxPanel() {
        initComponents();
        inboxList.setModel(new DefaultListModel());
    }
    
    public void setStore(Store s)
    {
        storeMail = s;
    }
    
    public synchronized void refresh()
    {
        
        try {
            Folder fichierMail = storeMail.getFolder("INBOX");
            
            fichierMail.open(Folder.READ_ONLY);
            messageList = fichierMail.getMessages();
            
        } catch (MessagingException ex) {
            Logger.getLogger(inboxPanel.class.getName()).log(Level.SEVERE, null, ex);//TO DO message d'erreur
        }
        
        DefaultListModel l = (DefaultListModel) inboxList.getModel();
        l.clear();
        
        for(Message m : messageList)
        {
            try {
                l.addElement(m.getSubject());
            } catch (MessagingException ex) {
                Logger.getLogger(inboxPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        inboxList = new javax.swing.JList();
        inboxLabel = new javax.swing.JLabel();
        nouveauButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        contenusTextArea = new javax.swing.JTextArea();
        fromLabel = new javax.swing.JLabel();
        receiveDateLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();

        inboxList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inboxListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(inboxList);

        inboxLabel.setText("Inbox : ");

        nouveauButton.setText("Nouveau");
        nouveauButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nouveauButtonActionPerformed(evt);
            }
        });

        contenusTextArea.setEditable(false);
        contenusTextArea.setColumns(20);
        contenusTextArea.setLineWrap(true);
        contenusTextArea.setRows(5);
        jScrollPane2.setViewportView(contenusTextArea);

        fromLabel.setText("From : ");

        receiveDateLabel.setToolTipText("");

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fromLabel)
                                    .addComponent(receiveDateLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inboxLabel)
                        .addGap(121, 121, 121)
                        .addComponent(nouveauButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inboxLabel)
                    .addComponent(nouveauButton)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(receiveDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nouveauButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nouveauButtonActionPerformed
        GUI_Mail container = (GUI_Mail)SwingUtilities.getWindowAncestor(this);
        container.changeLayout("nouveauMessage");
    }//GEN-LAST:event_nouveauButtonActionPerformed

    private void inboxListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inboxListMouseClicked
        if(inboxList.getSelectedIndex() < 0)
            return;
        
        Message m = messageList[inboxList.getSelectedIndex()];
        try {
            if(m.isMimeType("text/plain"))
                contenusTextArea.setText(m.getContent().toString());
            
            //Parcours de la liste des personnes qui ont envoyé un mail
            String from = "From : ";
            for(Address a : m.getFrom())
            {
                from += " "+a.toString();
            }
            fromLabel.setText(from);
            
            //Date de reception TO DO
            //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            //receiveDateLabel.setText("test: " + df.format(m.getSentDate()));
        } catch (IOException ex) {
            Logger.getLogger(inboxPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(inboxPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_inboxListMouseClicked

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        
        try {
            storeMail.close();
        } catch (MessagingException ex) {
            Logger.getLogger(inboxPanel.class.getName()).log(Level.SEVERE, null, ex);//TO DO message erreur
        }
        
        GUI_Mail container = (GUI_Mail)SwingUtilities.getWindowAncestor(this);
        container.changeLayout("connexion");
    }//GEN-LAST:event_logoutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea contenusTextArea;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel inboxLabel;
    private javax.swing.JList inboxList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton nouveauButton;
    private javax.swing.JLabel receiveDateLabel;
    // End of variables declaration//GEN-END:variables
}

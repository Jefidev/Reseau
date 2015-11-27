/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applic_mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class inboxPanel extends javax.swing.JPanel {

    /**
     * Creates new form inboxPanel
     */
    public inboxPanel() {
        initComponents();
        inboxList.setModel(new DefaultListModel());
    }
    
    public void setMessage(Message[] msg)
    {
        DefaultListModel l = (DefaultListModel) inboxList.getModel();
        for(Message m : msg)
        {
            try {
                l.addElement(m.getSubject());
            } catch (MessagingException ex) {
                System.err.println(ex);
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

        jScrollPane1.setViewportView(inboxList);

        inboxLabel.setText("Inbox : ");

        nouveauButton.setText("Nouveau");
        nouveauButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nouveauButtonActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(563, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inboxLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nouveauButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nouveauButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inboxLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nouveauButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nouveauButtonActionPerformed
        GUI_Mail container = (GUI_Mail)SwingUtilities.getWindowAncestor(this);
        container.changeLayout("envoyer");
    }//GEN-LAST:event_nouveauButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel inboxLabel;
    private javax.swing.JList inboxList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nouveauButton;
    // End of variables declaration//GEN-END:variables
}

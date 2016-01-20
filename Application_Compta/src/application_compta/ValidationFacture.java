package application_compta;

import java.io.IOException;
import javax.swing.SwingUtilities;
import library_compta.Convert;
import library_compta.Crypto;
import library_compta.Facture;
import library_compta.ProtocoleBISAMAP;


public class ValidationFacture extends javax.swing.JPanel
{
    private ApplicationCompta a;
    
    public ValidationFacture()
    {
        initComponents();
        a = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        FeedbackLabel.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuButton = new javax.swing.JButton();
        TitreLabel = new javax.swing.JLabel();
        NextBill = new javax.swing.JButton();
        ValiderFacture = new javax.swing.JButton();
        FeedbackLabel = new javax.swing.JLabel();

        MenuButton.setLabel("Retour au menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("FACTURE PLUS ANCIENNE NON VALIDEE :");

        NextBill.setText("Prochaine facture");
        NextBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextBillActionPerformed(evt);
            }
        });

        ValiderFacture.setText("Valider la facture");
        ValiderFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderFactureActionPerformed(evt);
            }
        });

        FeedbackLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FeedbackLabel.setForeground(new java.awt.Color(255, 0, 0));
        FeedbackLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(NextBill)
                        .addGap(18, 18, 18)
                        .addComponent(ValiderFacture)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MenuButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 78, Short.MAX_VALUE)
                        .addComponent(TitreLabel)
                        .addGap(84, 84, 84))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FeedbackLabel)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(FeedbackLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MenuButton)
                    .addComponent(NextBill)
                    .addComponent(ValiderFacture))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        a.ChangePanel("Menu");
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void NextBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextBillActionPerformed
        FeedbackLabel.setVisible(false);
        
        Utility.SendMsg(ProtocoleBISAMAP.GET_NEXT_BILL, "");
        
        String reponse = Utility.ReceiveMsg();  
        String[] parts = reponse.split("#");
        
        if (parts[0].equals("OUI"))
        {
            try
            {
                int longueur = Utility.dis.readInt();
                byte[] factureToDecrypt = new byte[longueur];
                Utility.dis.readFully(factureToDecrypt);
                
                byte[] factureDecryptee = Crypto.symDecrypt(a.CleSecreteChiffrement, factureToDecrypt);
                Facture facture = (Facture)Convert.ByteArrayToObject(factureDecryptee);
            }
            catch (IOException ex)
            {
                FeedbackLabel.setText("Probleme d'IO côté client");
                FeedbackLabel.setVisible(true);
            }       
        }
        else
        {
            FeedbackLabel.setText(parts[1]);
            FeedbackLabel.setVisible(true);
        }
    }//GEN-LAST:event_NextBillActionPerformed

    private void ValiderFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderFactureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ValiderFactureActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FeedbackLabel;
    private javax.swing.JButton MenuButton;
    private javax.swing.JButton NextBill;
    private javax.swing.JLabel TitreLabel;
    private javax.swing.JButton ValiderFacture;
    // End of variables declaration//GEN-END:variables
}

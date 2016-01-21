package application_compta;

import javax.swing.SwingUtilities;
import library_compta.ProtocoleBISAMAP;


public class Menu extends javax.swing.JPanel
{
    public Menu()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitreLabel = new javax.swing.JLabel();
        ValidationFactButton = new javax.swing.JButton();
        ListerFacturesButton = new javax.swing.JButton();
        RecPayButton = new javax.swing.JButton();
        ListWaitingButton = new javax.swing.JButton();
        QuitterButton = new javax.swing.JButton();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("MENU");

        ValidationFactButton.setText("Validation de factures");
        ValidationFactButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValidationFactButtonActionPerformed(evt);
            }
        });

        ListerFacturesButton.setText("Lister les factures");
        ListerFacturesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListerFacturesButtonActionPerformed(evt);
            }
        });

        RecPayButton.setLabel("Enregistrement du paiement d'une facture");
        RecPayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecPayButtonActionPerformed(evt);
            }
        });

        ListWaitingButton.setText("Liste des factures non payées");
        ListWaitingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListWaitingButtonActionPerformed(evt);
            }
        });

        QuitterButton.setText("Quitter");
        QuitterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TitreLabel)
                .addGap(251, 251, 251))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(QuitterButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ValidationFactButton)
                            .addComponent(ListerFacturesButton)
                            .addComponent(RecPayButton)
                            .addComponent(ListWaitingButton))
                        .addGap(0, 295, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitreLabel)
                .addGap(18, 18, 18)
                .addComponent(ValidationFactButton)
                .addGap(18, 18, 18)
                .addComponent(ListerFacturesButton)
                .addGap(18, 18, 18)
                .addComponent(RecPayButton)
                .addGap(18, 18, 18)
                .addComponent(ListWaitingButton)
                .addContainerGap(103, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(QuitterButton)
                .addContainerGap())
        );

        ListWaitingButton.getAccessibleContext().setAccessibleName("Factures non payées");
    }// </editor-fold>//GEN-END:initComponents

    private void ValidationFactButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValidationFactButtonActionPerformed
        ApplicationCompta app = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("ValidationFacture");
    }//GEN-LAST:event_ValidationFactButtonActionPerformed

    private void ListerFacturesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListerFacturesButtonActionPerformed
        ApplicationCompta app = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("ListerFactures");
    }//GEN-LAST:event_ListerFacturesButtonActionPerformed

    private void RecPayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecPayButtonActionPerformed
        ApplicationCompta app = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("RecPay");
    }//GEN-LAST:event_RecPayButtonActionPerformed

    private void ListWaitingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListWaitingButtonActionPerformed
        ApplicationCompta app = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("ListWaiting");
    }//GEN-LAST:event_ListWaitingButtonActionPerformed

    private void QuitterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitterButtonActionPerformed
        Utility.SendMsg(ProtocoleBISAMAP.LOGOUT, null);
        System.exit(0);
    }//GEN-LAST:event_QuitterButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ListWaitingButton;
    private javax.swing.JButton ListerFacturesButton;
    private javax.swing.JButton QuitterButton;
    private javax.swing.JButton RecPayButton;
    private javax.swing.JLabel TitreLabel;
    private javax.swing.JButton ValidationFactButton;
    // End of variables declaration//GEN-END:variables
}

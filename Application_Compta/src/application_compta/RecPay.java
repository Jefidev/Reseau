package application_compta;

import javax.swing.SwingUtilities;
import library_compta.ProtocoleBISAMAP;


public class RecPay extends javax.swing.JPanel
{
    public RecPay()
    {
        initComponents();
        ErreurL.setVisible(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitreLabel = new javax.swing.JLabel();
        idFactureL = new javax.swing.JLabel();
        idFactureTF = new javax.swing.JTextField();
        MontantL = new javax.swing.JLabel();
        MontantTF = new javax.swing.JTextField();
        EnregistrerButton = new javax.swing.JButton();
        MenuButton = new javax.swing.JButton();
        ErreurL = new javax.swing.JLabel();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("ENREGISTREMENT DU PAIEMENT D'UNE FACTURE :");

        idFactureL.setText("Id facture :");

        MontantL.setText("Montant TVAC :");

        EnregistrerButton.setText("Enregistrer");
        EnregistrerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnregistrerButtonActionPerformed(evt);
            }
        });

        MenuButton.setText("Retour au menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        ErreurL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErreurL.setForeground(new java.awt.Color(255, 0, 0));
        ErreurL.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(MenuButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TitreLabel)
                        .addGap(38, 38, 38))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idFactureL)
                            .addComponent(MontantL)
                            .addComponent(ErreurL))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idFactureTF)
                            .addComponent(MontantTF, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(EnregistrerButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitreLabel)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idFactureL)
                    .addComponent(idFactureTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MontantL)
                    .addComponent(MontantTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(EnregistrerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(ErreurL)
                .addGap(50, 50, 50)
                .addComponent(MenuButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        ApplicationCompta a = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        a.ChangePanel("Menu");
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void EnregistrerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnregistrerButtonActionPerformed
        try
        {
            ErreurL.setVisible(false);
            
            String idFacture = idFactureTF.getText();
            if (idFacture.isEmpty())
            {
                ErreurL.setText("Entrer un id de facture !");
                ErreurL.setVisible(true);
                return;
            }
            
            if(MontantTF.getText().isEmpty())
            {
                ErreurL.setText("Entrer un id de facture !");
                ErreurL.setVisible(true);
                return;
            }
            double montant = Integer.parseInt(MontantTF.getText());
            
            
            
            
            Utility.SendMsg(ProtocoleBISAMAP.REC_PAY, "");

            
            
            
            
            
            String reponse = Utility.ReceiveMsg();
            String[] parts = reponse.split("#");

            ErreurL.setText(parts[1]);
            ErreurL.setVisible(true);
        }
        catch(NumberFormatException ex)
        {
            ErreurL.setText("Entrer un montant valide !");
            ErreurL.setVisible(true);
        }
    }//GEN-LAST:event_EnregistrerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EnregistrerButton;
    private javax.swing.JLabel ErreurL;
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel MontantL;
    private javax.swing.JTextField MontantTF;
    private javax.swing.JLabel TitreLabel;
    private javax.swing.JLabel idFactureL;
    private javax.swing.JTextField idFactureTF;
    // End of variables declaration//GEN-END:variables
}

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
    private Facture facture;
    
    
    public ValidationFacture()
    {
        initComponents();
        a = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        FeedbackLabel.setVisible(false);
        CacherLabels();
        facture = null;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuButton = new javax.swing.JButton();
        TitreLabel = new javax.swing.JLabel();
        NextBillButton = new javax.swing.JButton();
        ValiderFactureButton = new javax.swing.JButton();
        FeedbackLabel = new javax.swing.JLabel();
        IdFactureL = new javax.swing.JLabel();
        IdSocieteL = new javax.swing.JLabel();
        MoisAnneeL = new javax.swing.JLabel();
        TotalHTVAL = new javax.swing.JLabel();
        TotalTVACL = new javax.swing.JLabel();
        FactValideeL = new javax.swing.JLabel();
        LoginL = new javax.swing.JLabel();
        FactEnvoyeeL = new javax.swing.JLabel();
        MoyenEnvoiL = new javax.swing.JLabel();
        FactPayeeL = new javax.swing.JLabel();
        IdFactureReponseL = new javax.swing.JLabel();
        IdSocieteReponseL = new javax.swing.JLabel();
        MoisAnneeReponseL = new javax.swing.JLabel();
        TotalHTVAReponseL = new javax.swing.JLabel();
        TotalTVACReponseL = new javax.swing.JLabel();
        FactValideeReponseL = new javax.swing.JLabel();
        LoginReponseL = new javax.swing.JLabel();
        FactEnvoyeeReponseL = new javax.swing.JLabel();
        MoyenEnvoiReponseL = new javax.swing.JLabel();
        FactPayeeReponseL = new javax.swing.JLabel();
        RefuserFactureButton = new javax.swing.JButton();

        MenuButton.setLabel("Retour au menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("FACTURE PLUS ANCIENNE NON VALIDEE :");

        NextBillButton.setText("Prochaine facture");
        NextBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextBillButtonActionPerformed(evt);
            }
        });

        ValiderFactureButton.setText("Valider la facture");
        ValiderFactureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderFactureButtonActionPerformed(evt);
            }
        });

        FeedbackLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FeedbackLabel.setForeground(new java.awt.Color(255, 0, 0));
        FeedbackLabel.setText("jLabel1");

        IdFactureL.setText("Id facture :");

        IdSocieteL.setText("Id société :");

        MoisAnneeL.setText("Mois et année concernés :");

        TotalHTVAL.setText("Total HTVA :");

        TotalTVACL.setText("Total TVAC :");

        FactValideeL.setText("Facture validée ?");

        LoginL.setText("Comptable validateur :");

        FactEnvoyeeL.setText("Facture envoyée ?");

        MoyenEnvoiL.setText("Moyen d'envoi :");

        FactPayeeL.setText("Facture payée ?");

        IdFactureReponseL.setText("jLabel1");

        IdSocieteReponseL.setText("jLabel2");

        MoisAnneeReponseL.setText("jLabel3");

        TotalHTVAReponseL.setText("jLabel4");

        TotalTVACReponseL.setText("jLabel5");

        FactValideeReponseL.setText("jLabel6");

        LoginReponseL.setText("jLabel7");

        FactEnvoyeeReponseL.setText("jLabel8");

        MoyenEnvoiReponseL.setText("jLabel9");

        FactPayeeReponseL.setText("jLabel10");

        RefuserFactureButton.setText("Refuser la facture");
        RefuserFactureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefuserFactureButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(NextBillButton)
                        .addGap(18, 18, 18)
                        .addComponent(ValiderFactureButton)
                        .addGap(18, 18, 18)
                        .addComponent(RefuserFactureButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MenuButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 78, Short.MAX_VALUE)
                        .addComponent(TitreLabel)
                        .addGap(84, 84, 84))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FeedbackLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IdFactureL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IdFactureReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IdSocieteL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IdSocieteReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TotalHTVAL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalHTVAReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TotalTVACL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalTVACReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MoisAnneeL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MoisAnneeReponseL)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(FactPayeeL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FactPayeeReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MoyenEnvoiL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MoyenEnvoiReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(FactEnvoyeeL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FactEnvoyeeReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LoginL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LoginReponseL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(FactValideeL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FactValideeReponseL)))
                        .addGap(131, 131, 131))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitreLabel)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IdFactureL)
                    .addComponent(FactValideeL)
                    .addComponent(IdFactureReponseL)
                    .addComponent(FactValideeReponseL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IdSocieteL)
                    .addComponent(LoginL)
                    .addComponent(IdSocieteReponseL)
                    .addComponent(LoginReponseL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MoisAnneeL)
                    .addComponent(FactEnvoyeeL)
                    .addComponent(MoisAnneeReponseL)
                    .addComponent(FactEnvoyeeReponseL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalHTVAL)
                    .addComponent(MoyenEnvoiL)
                    .addComponent(TotalHTVAReponseL)
                    .addComponent(MoyenEnvoiReponseL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalTVACL)
                    .addComponent(FactPayeeL)
                    .addComponent(TotalTVACReponseL)
                    .addComponent(FactPayeeReponseL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(FeedbackLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MenuButton)
                    .addComponent(NextBillButton)
                    .addComponent(ValiderFactureButton)
                    .addComponent(RefuserFactureButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        a.ChangePanel("Menu");
    }//GEN-LAST:event_MenuButtonActionPerformed

    
    private void NextBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextBillButtonActionPerformed
        FeedbackLabel.setVisible(false);
        CacherLabels();
        
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
                
                byte[] factureDecryptee = Crypto.SymDecrypt(a.CleSecreteChiffrement, factureToDecrypt);
                facture = (Facture)Convert.ByteArrayToObject(factureDecryptee);
                
                RemplirLabels();
                AfficherLabels();
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
    }//GEN-LAST:event_NextBillButtonActionPerformed

    
    private void ValiderFactureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderFactureButtonActionPerformed
        ModifFacture(facture.IdFacture + "#" + "1");
    }//GEN-LAST:event_ValiderFactureButtonActionPerformed

    
    private void RefuserFactureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefuserFactureButtonActionPerformed
        ModifFacture(facture.IdFacture + "#" + "2");
    }//GEN-LAST:event_RefuserFactureButtonActionPerformed

    
    private void ModifFacture(String msg)
    {
        FeedbackLabel.setVisible(false);
        
        Utility.SendMsg(ProtocoleBISAMAP.VALIDATE_BILL, msg);
        
        // Envoi signature du comptable
        
        String toSign = ProtocoleBISAMAP.VALIDATE_BILL + msg;
        byte[] signature = Crypto.CreateSignature(toSign.getBytes(), "KSAppCompta.p12", "azerty", "azerty", "AppCompta");

        String reponse = Utility.ReceiveMsg();
        String[] parts = reponse.split("#");
        
        if (parts[0].equals("OUI"))
        {
            FeedbackLabel.setText(parts[1]);
            FeedbackLabel.setVisible(true);    
        }
        else
        {
            FeedbackLabel.setText(parts[1]);
            FeedbackLabel.setVisible(true);
        }
    }
    
    
    private void CacherLabels()
    {
        IdFactureReponseL.setVisible(false);
        IdSocieteReponseL.setVisible(false);
        MoisAnneeReponseL.setVisible(false);
        TotalHTVAReponseL.setVisible(false);
        TotalTVACReponseL.setVisible(false);
        FactValideeReponseL.setVisible(false);
        LoginReponseL.setVisible(false);
        FactEnvoyeeReponseL.setVisible(false);
        MoyenEnvoiReponseL.setVisible(false);
        FactPayeeReponseL.setVisible(false);
    }

    
    private void AfficherLabels()
    {
        IdFactureReponseL.setVisible(true);
        IdSocieteReponseL.setVisible(true);
        MoisAnneeReponseL.setVisible(true);
        TotalHTVAReponseL.setVisible(true);
        TotalTVACReponseL.setVisible(true);
        FactValideeReponseL.setVisible(true);
        LoginReponseL.setVisible(true);
        FactEnvoyeeReponseL.setVisible(true);
        MoyenEnvoiReponseL.setVisible(true);
        FactPayeeReponseL.setVisible(true);
    }
    
    
    private void RemplirLabels()
    {
        String reponse;
        
        IdFactureReponseL.setText(facture.IdFacture);
        IdSocieteReponseL.setText(facture.IdSociete);
        MoisAnneeReponseL.setText(facture.MoisAnnee);
        TotalHTVAReponseL.setText(String.valueOf(facture.TotalHTVA));
        TotalTVACReponseL.setText(String.valueOf(facture.TotalTVAC));
        LoginReponseL.setText(facture.Login);
        MoyenEnvoiReponseL.setText(facture.MoyenEnvoi);
        
        if(facture.FlagFactValidee == 0)
            reponse = "Pas encore";
        else if(facture.FlagFactValidee == 1)
            reponse = "Oui";
        else
            reponse = "Refusée";
        FactValideeReponseL.setText(reponse);
       
        if(facture.FlagFactEnvoyee == 0)
            reponse = "Non";
        else
            reponse = "Oui";
        FactEnvoyeeReponseL.setText(reponse);

        if(facture.FlagFactPayee == 0)
            reponse = "Non";
        else
            reponse = "Oui";
        FactPayeeReponseL.setText(reponse);
    }
    
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FactEnvoyeeL;
    private javax.swing.JLabel FactEnvoyeeReponseL;
    private javax.swing.JLabel FactPayeeL;
    private javax.swing.JLabel FactPayeeReponseL;
    private javax.swing.JLabel FactValideeL;
    private javax.swing.JLabel FactValideeReponseL;
    private javax.swing.JLabel FeedbackLabel;
    private javax.swing.JLabel IdFactureL;
    private javax.swing.JLabel IdFactureReponseL;
    private javax.swing.JLabel IdSocieteL;
    private javax.swing.JLabel IdSocieteReponseL;
    private javax.swing.JLabel LoginL;
    private javax.swing.JLabel LoginReponseL;
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel MoisAnneeL;
    private javax.swing.JLabel MoisAnneeReponseL;
    private javax.swing.JLabel MoyenEnvoiL;
    private javax.swing.JLabel MoyenEnvoiReponseL;
    private javax.swing.JButton NextBillButton;
    private javax.swing.JButton RefuserFactureButton;
    private javax.swing.JLabel TitreLabel;
    private javax.swing.JLabel TotalHTVAL;
    private javax.swing.JLabel TotalHTVAReponseL;
    private javax.swing.JLabel TotalTVACL;
    private javax.swing.JLabel TotalTVACReponseL;
    private javax.swing.JButton ValiderFactureButton;
    // End of variables declaration//GEN-END:variables
}

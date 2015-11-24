package application_data_analysis;

import javax.swing.SwingUtilities;


public class StatInferTestConf extends javax.swing.JPanel
{
    public StatInferTestConf()
    {
        initComponents();
        ErrorSaisieLabel.setVisible(false);
        pvalueLabel.setVisible(false);
        pvalueReponseLabel.setVisible(false);
        ResultatLabel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitreLabel = new javax.swing.JLabel();
        SujetLabel = new javax.swing.JLabel();
        NbContainersLabel = new javax.swing.JLabel();
        NbContainersTF = new javax.swing.JTextField();
        ErrorSaisieLabel = new javax.swing.JLabel();
        TesterButton = new javax.swing.JButton();
        Sujet2Label = new javax.swing.JLabel();
        RetourMenuButton = new javax.swing.JButton();
        pvalueLabel = new javax.swing.JLabel();
        pvalueReponseLabel = new javax.swing.JLabel();
        ResultatLabel = new javax.swing.JLabel();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("Test d'hypothèse de conformité");

        SujetLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SujetLabel.setText("Sujet :");

        NbContainersLabel.setText("Nombre de containers de l'échantillon :");

        ErrorSaisieLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErrorSaisieLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorSaisieLabel.setText("Doit être un entier positif !");

        TesterButton.setText("Tester");
        TesterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesterButtonActionPerformed(evt);
            }
        });

        Sujet2Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Sujet2Label.setText("Le temps moyen de stationnement d'un container est supposé être de 10 jours, est-ce vrai ?");

        RetourMenuButton.setText("Retour au menu");
        RetourMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetourMenuButtonActionPerformed(evt);
            }
        });

        pvalueLabel.setText("P-value : ");

        pvalueReponseLabel.setText("jLabel1");

        ResultatLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NbContainersLabel)
                                .addGap(18, 18, 18)
                                .addComponent(NbContainersTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TesterButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ErrorSaisieLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SujetLabel)
                                    .addComponent(Sujet2Label))
                                .addGap(0, 8, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(TitreLabel)
                                .addGap(159, 159, 159))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(RetourMenuButton)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pvalueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pvalueReponseLabel))
                            .addComponent(ResultatLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitreLabel)
                .addGap(18, 18, 18)
                .addComponent(SujetLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sujet2Label)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NbContainersLabel)
                    .addComponent(ErrorSaisieLabel)
                    .addComponent(NbContainersTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TesterButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pvalueLabel)
                    .addComponent(pvalueReponseLabel))
                .addGap(18, 18, 18)
                .addComponent(ResultatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(RetourMenuButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RetourMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetourMenuButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("Menu");
    }//GEN-LAST:event_RetourMenuButtonActionPerformed

    private void TesterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesterButtonActionPerformed
                
        ErrorSaisieLabel.setVisible(false);
        pvalueLabel.setVisible(false);
        pvalueReponseLabel.setVisible(false);
        ResultatLabel.setVisible(false);
        
        try
        {
            int nbContainers = Integer.parseInt(NbContainersTF.getText());
            if (nbContainers <= 0)
            {
                ErrorSaisieLabel.setVisible(true);
                return;
            }

            Utility.SendMsg(ProtocolePIDEP.GET_STAT_INFER_TEST_CONF, NbContainersTF.getText());

            // Réponse
            String reponse = Utility.ReceiveMsg();
            String[] parts = reponse.split("#");
            
            if (!parts[0].equals("NON"))
            {
                pvalueReponseLabel.setText(parts[0]);
                pvalueReponseLabel.setVisible(true);
                pvalueLabel.setVisible(true);
                
            }
            ResultatLabel.setText(parts[1]);
            ResultatLabel.setVisible(true);
        }
        catch (NumberFormatException ex)
        {
            ErrorSaisieLabel.setVisible(true);
        }
    }//GEN-LAST:event_TesterButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrorSaisieLabel;
    private javax.swing.JLabel NbContainersLabel;
    private javax.swing.JTextField NbContainersTF;
    private javax.swing.JLabel ResultatLabel;
    private javax.swing.JButton RetourMenuButton;
    private javax.swing.JLabel Sujet2Label;
    private javax.swing.JLabel SujetLabel;
    private javax.swing.JButton TesterButton;
    private javax.swing.JLabel TitreLabel;
    private javax.swing.JLabel pvalueLabel;
    private javax.swing.JLabel pvalueReponseLabel;
    // End of variables declaration//GEN-END:variables
}

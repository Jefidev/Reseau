package application_data_analysis;

import javax.swing.SwingUtilities;


public class StatInferTestHomog extends javax.swing.JPanel
{
    public StatInferTestHomog()
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
        DestinationALabel = new javax.swing.JLabel();
        DestinationBLabel = new javax.swing.JLabel();
        DestinationATF = new javax.swing.JTextField();
        DestinationBTF = new javax.swing.JTextField();
        pvalueLabel = new javax.swing.JLabel();
        pvalueReponseLabel = new javax.swing.JLabel();
        ResultatLabel = new javax.swing.JLabel();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("Test d'hypothèse d'homogénéité");

        SujetLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SujetLabel.setText("Sujet : Le temps moyen de stationnement d'un container est-il le même");

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
        Sujet2Label.setText("si il est à destination de la ville A ou de la ville B saisies ?");

        RetourMenuButton.setText("Retour au menu");
        RetourMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetourMenuButtonActionPerformed(evt);
            }
        });

        DestinationALabel.setText("Destination A :");

        DestinationBLabel.setText("Destination B :");

        pvalueLabel.setText("P-value : ");

        pvalueReponseLabel.setText("jLabel1");

        ResultatLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(TitreLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(Sujet2Label)))
                        .addGap(0, 127, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(RetourMenuButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(NbContainersLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(NbContainersTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(SujetLabel))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(DestinationBLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(DestinationBTF))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(DestinationALabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(DestinationATF, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(TesterButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ErrorSaisieLabel)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pvalueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pvalueReponseLabel))
                    .addComponent(ResultatLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NbContainersLabel)
                    .addComponent(NbContainersTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DestinationALabel)
                            .addComponent(DestinationATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DestinationBLabel)
                            .addComponent(DestinationBTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pvalueLabel)
                            .addComponent(pvalueReponseLabel))
                        .addGap(18, 18, 18)
                        .addComponent(ResultatLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(RetourMenuButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(ErrorSaisieLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TesterButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                ErrorSaisieLabel.setText("Doit être un entier positif (>1) !");
                ErrorSaisieLabel.setVisible(true);
                return;
            }
            
            if(DestinationATF.getText().isEmpty() || DestinationBTF.getText().isEmpty() || DestinationATF.getText().equals(DestinationBTF.getText()))
            {
                ErrorSaisieLabel.setText("Entrer deux destinations différentes !");
                ErrorSaisieLabel.setVisible(true);
                return;
            }                
            
            String ChargeUtile = NbContainersTF.getText() + "#" + DestinationATF.getText() + "#" + DestinationBTF.getText();

            Utility.SendMsg(ProtocolePIDEP.GET_STAT_INFER_TEST_HOMOG, ChargeUtile);

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
            ErrorSaisieLabel.setText("Doit être un entier positif (>1) !");
            ErrorSaisieLabel.setVisible(true);
        }
    }//GEN-LAST:event_TesterButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DestinationALabel;
    private javax.swing.JTextField DestinationATF;
    private javax.swing.JLabel DestinationBLabel;
    private javax.swing.JTextField DestinationBTF;
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

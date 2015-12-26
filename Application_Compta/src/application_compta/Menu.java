package application_compta;

import javax.swing.SwingUtilities;


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
        StatDescrContButton = new javax.swing.JButton();
        GrCouleurRepButton = new javax.swing.JButton();
        GrCouleurCompButton = new javax.swing.JButton();
        StatInferTestConfButton = new javax.swing.JButton();
        StatInferTestHomogButton = new javax.swing.JButton();
        StatInferTestAnovaButton = new javax.swing.JButton();
        QuitterButton = new javax.swing.JButton();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("MENU");

        StatDescrContButton.setText("Statistiques constructives descriptives : moyenne, mode, médiane, écart-type");
        StatDescrContButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatDescrContButtonActionPerformed(evt);
            }
        });

        GrCouleurRepButton.setText("Répartition du nombre de containers par destination (diagramme sectoriel)");
        GrCouleurRepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrCouleurRepButtonActionPerformed(evt);
            }
        });

        GrCouleurCompButton.setText("Répartition du nombre de containers par destination par trimestre (histogramme)");
        GrCouleurCompButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrCouleurCompButtonActionPerformed(evt);
            }
        });

        StatInferTestConfButton.setText("Test d'hypothèse de conformité");
        StatInferTestConfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatInferTestConfButtonActionPerformed(evt);
            }
        });

        StatInferTestHomogButton.setText("Test d'hypothèse d'homogénéité");
        StatInferTestHomogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatInferTestHomogButtonActionPerformed(evt);
            }
        });

        StatInferTestAnovaButton.setText("Test d'hypothèse de type ANOVA");
        StatInferTestAnovaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatInferTestAnovaButtonActionPerformed(evt);
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
                        .addComponent(StatInferTestAnovaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(QuitterButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StatDescrContButton)
                            .addComponent(GrCouleurRepButton)
                            .addComponent(GrCouleurCompButton)
                            .addComponent(StatInferTestConfButton)
                            .addComponent(StatInferTestHomogButton))
                        .addGap(0, 111, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitreLabel)
                .addGap(18, 18, 18)
                .addComponent(StatDescrContButton)
                .addGap(18, 18, 18)
                .addComponent(GrCouleurRepButton)
                .addGap(18, 18, 18)
                .addComponent(GrCouleurCompButton)
                .addGap(18, 18, 18)
                .addComponent(StatInferTestConfButton)
                .addGap(18, 18, 18)
                .addComponent(StatInferTestHomogButton)
                .addGap(18, 18, 18)
                .addComponent(StatInferTestAnovaButton)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(QuitterButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StatDescrContButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatDescrContButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("StatDescrCont");
    }//GEN-LAST:event_StatDescrContButtonActionPerformed

    private void GrCouleurRepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrCouleurRepButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("GrCouleurRep");
    }//GEN-LAST:event_GrCouleurRepButtonActionPerformed

    private void GrCouleurCompButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrCouleurCompButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("GrCouleurComp");
    }//GEN-LAST:event_GrCouleurCompButtonActionPerformed

    private void StatInferTestConfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatInferTestConfButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("StatInferTestConf");
    }//GEN-LAST:event_StatInferTestConfButtonActionPerformed

    private void StatInferTestHomogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatInferTestHomogButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("StatInferTestHomog");
    }//GEN-LAST:event_StatInferTestHomogButtonActionPerformed

    private void StatInferTestAnovaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatInferTestAnovaButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("StatInferTestAnova");
    }//GEN-LAST:event_StatInferTestAnovaButtonActionPerformed

    private void QuitterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitterButtonActionPerformed
        Utility.SendMsg(ProtocoleBISAMAP.LOGOUT, null);
        System.exit(0);
    }//GEN-LAST:event_QuitterButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GrCouleurCompButton;
    private javax.swing.JButton GrCouleurRepButton;
    private javax.swing.JButton QuitterButton;
    private javax.swing.JButton StatDescrContButton;
    private javax.swing.JButton StatInferTestAnovaButton;
    private javax.swing.JButton StatInferTestConfButton;
    private javax.swing.JButton StatInferTestHomogButton;
    private javax.swing.JLabel TitreLabel;
    // End of variables declaration//GEN-END:variables
}

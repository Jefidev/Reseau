package application_data_analysis;

import javax.swing.SwingUtilities;


public class Menu extends javax.swing.JPanel {

    public Menu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitreLabel = new javax.swing.JLabel();
        StatDescrContButton = new javax.swing.JButton();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("MENU");

        StatDescrContButton.setText("Statistiques constructives descriptives : moyenne, mode, médiane, écart-type");
        StatDescrContButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatDescrContButtonActionPerformed(evt);
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
                .addComponent(StatDescrContButton)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitreLabel)
                .addGap(18, 18, 18)
                .addComponent(StatDescrContButton)
                .addContainerGap(231, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StatDescrContButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatDescrContButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("StatDescrCont");
    }//GEN-LAST:event_StatDescrContButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StatDescrContButton;
    private javax.swing.JLabel TitreLabel;
    // End of variables declaration//GEN-END:variables
}

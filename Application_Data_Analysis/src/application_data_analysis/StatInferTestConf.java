package application_data_analysis;


public class StatInferTestConf extends javax.swing.JPanel
{
    public StatInferTestConf()
    {
        initComponents();
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

        Sujet2Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Sujet2Label.setText("Le temps moyen de stationnement d'un container est supposé être de 10 jours, est-ce vrai ?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TitreLabel)
                .addGap(159, 159, 159))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap(162, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrorSaisieLabel;
    private javax.swing.JLabel NbContainersLabel;
    private javax.swing.JTextField NbContainersTF;
    private javax.swing.JLabel Sujet2Label;
    private javax.swing.JLabel SujetLabel;
    private javax.swing.JButton TesterButton;
    private javax.swing.JLabel TitreLabel;
    // End of variables declaration//GEN-END:variables
}

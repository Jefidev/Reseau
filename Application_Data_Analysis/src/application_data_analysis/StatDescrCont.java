package application_data_analysis;


public class StatDescrCont extends javax.swing.JPanel
{
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonGroup = new javax.swing.ButtonGroup();
        Titre1Label = new javax.swing.JLabel();
        Titre2Label = new javax.swing.JLabel();
        NbContainersLabel = new javax.swing.JLabel();
        NbContainersTF = new javax.swing.JTextField();
        ErrorNbContainersLabel = new javax.swing.JLabel();
        DechargesRB = new javax.swing.JRadioButton();
        ChargesRB = new javax.swing.JRadioButton();
        CalculerButton = new javax.swing.JButton();
        MoyenneLabel = new javax.swing.JLabel();
        EcartTypeLabel = new javax.swing.JLabel();
        MedianeLabel = new javax.swing.JLabel();
        ModeLabel = new javax.swing.JLabel();
        EcartTypeReponseLabel = new javax.swing.JLabel();
        MedianeReponseLabel = new javax.swing.JLabel();
        ModeReponseLabel = new javax.swing.JLabel();
        MoyenneReponseLabel = new javax.swing.JLabel();

        Titre1Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Titre1Label.setForeground(new java.awt.Color(0, 0, 255));
        Titre1Label.setText("Paramètres statistiques sur le poids des containers traités dans l'année");

        Titre2Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Titre2Label.setForeground(new java.awt.Color(0, 0, 255));
        Titre2Label.setText("sur base d'un échantillon aléatoire de mouvements");

        NbContainersLabel.setText("Nombre de containers de l'échantillon :");

        ErrorNbContainersLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorNbContainersLabel.setText("Doit être un entier positif !");

        DechargesRB.setText("Déchargés");

        ChargesRB.setText("Chargés");

        CalculerButton.setText("Calculer");
        CalculerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculerButtonActionPerformed(evt);
            }
        });

        MoyenneLabel.setText("Moyenne =");

        EcartTypeLabel.setText("Ecart-type =");

        MedianeLabel.setText("Médiane =");

        ModeLabel.setText("Mode =");

        EcartTypeReponseLabel.setText("jLabel4");

        MedianeReponseLabel.setText("jLabel3");

        ModeReponseLabel.setText("jLabel2");

        MoyenneReponseLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Titre1Label)
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Titre2Label)
                                .addGap(111, 111, 111))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NbContainersLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(DechargesRB)
                                        .addGap(18, 18, 18)
                                        .addComponent(ChargesRB)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(NbContainersTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ErrorNbContainersLabel))
                                    .addComponent(CalculerButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EcartTypeLabel)
                                    .addComponent(MedianeLabel)
                                    .addComponent(ModeLabel)
                                    .addComponent(MoyenneLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MoyenneReponseLabel)
                                    .addComponent(ModeReponseLabel)
                                    .addComponent(MedianeReponseLabel)
                                    .addComponent(EcartTypeReponseLabel))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titre1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Titre2Label)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NbContainersLabel)
                    .addComponent(NbContainersTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ErrorNbContainersLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DechargesRB)
                    .addComponent(ChargesRB)
                    .addComponent(CalculerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MoyenneLabel)
                    .addComponent(MoyenneReponseLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModeLabel)
                    .addComponent(ModeReponseLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MedianeLabel)
                    .addComponent(MedianeReponseLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EcartTypeLabel)
                    .addComponent(EcartTypeReponseLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    public StatDescrCont()
    {
        initComponents();
        
        ButtonGroup.add(DechargesRB);
        ButtonGroup.add(ChargesRB);
        
        // Désactiver les boutons à réactiver quand connecté
        ErrorNbContainersLabel.setVisible(false);
        MoyenneReponseLabel.setVisible(false);
        ModeReponseLabel.setVisible(false);
        MedianeReponseLabel.setVisible(false);
        EcartTypeReponseLabel.setVisible(false);
    }
        
    private void CalculerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculerButtonActionPerformed

        try
        {
            int nbContainers = Integer.parseInt(NbContainersTF.getText());

            // IN-OUT

            String ChargeUtile = nbContainers + "IN";
            Utility.SendMsg(ProtocolePIDEP.GET_STAT_DESCR_CONT, ChargeUtile);

            // Réponse
            String reponse = Utility.ReceiveMsg();
            String[] parts = reponse.split("#");

            MoyenneReponseLabel.setText(parts[0]);
            MoyenneReponseLabel.setVisible(true);
            ModeReponseLabel.setText(parts[1]);
            ModeReponseLabel.setVisible(true);
            MedianeReponseLabel.setText(parts[2]);
            MedianeReponseLabel.setVisible(true);
            EcartTypeReponseLabel.setText(parts[3]);
            EcartTypeReponseLabel.setVisible(true);
        }
        catch (NumberFormatException ex)
        {
            ErrorNbContainersLabel.setVisible(true);
        }
    }//GEN-LAST:event_CalculerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ButtonGroup;
    private javax.swing.JButton CalculerButton;
    private javax.swing.JRadioButton ChargesRB;
    private javax.swing.JRadioButton DechargesRB;
    private javax.swing.JLabel EcartTypeLabel;
    private javax.swing.JLabel EcartTypeReponseLabel;
    private javax.swing.JLabel ErrorNbContainersLabel;
    private javax.swing.JLabel MedianeLabel;
    private javax.swing.JLabel MedianeReponseLabel;
    private javax.swing.JLabel ModeLabel;
    private javax.swing.JLabel ModeReponseLabel;
    private javax.swing.JLabel MoyenneLabel;
    private javax.swing.JLabel MoyenneReponseLabel;
    private javax.swing.JLabel NbContainersLabel;
    private javax.swing.JTextField NbContainersTF;
    private javax.swing.JLabel Titre1Label;
    private javax.swing.JLabel Titre2Label;
    // End of variables declaration//GEN-END:variables
}

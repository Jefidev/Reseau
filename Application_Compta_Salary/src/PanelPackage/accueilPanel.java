/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PanelPackage;

import application_compta_salary.GUIsalary;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class AccueilPanel extends javax.swing.JPanel {

    /**
     * Creates new form accueilPanel
     */
    public AccueilPanel() {
        initComponents();
        resetError();
    }
    
    private void resetError()
    {
        errorAskLabel.setVisible(false);
        errorPayementLabel.setVisible(false);
        errorPayements.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titreLabel = new javax.swing.JLabel();
        payementButton = new javax.swing.JButton();
        payementLabel = new javax.swing.JLabel();
        loginEmployeTF = new javax.swing.JTextField();
        errorPayementLabel = new javax.swing.JLabel();
        payementsButton = new javax.swing.JButton();
        askButton = new javax.swing.JButton();
        moisLabel = new javax.swing.JLabel();
        moisCombo = new javax.swing.JComboBox<String>();
        anneeCombo = new javax.swing.JComboBox<String>();
        logoutButton = new javax.swing.JButton();
        errorAskLabel = new javax.swing.JLabel();
        errorPayements = new javax.swing.JLabel();

        titreLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titreLabel.setText("Application salary");

        payementButton.setText("Payement");
        payementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payementButtonActionPerformed(evt);
            }
        });

        payementLabel.setText("Login employé à payer : ");

        errorPayementLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorPayementLabel.setText("jLabel1");

        payementsButton.setText("Payements");
        payementsButton.setToolTipText("");
        payementsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payementsButtonActionPerformed(evt);
            }
        });

        askButton.setText("Payements effectues");
        askButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                askButtonActionPerformed(evt);
            }
        });

        moisLabel.setText("Date : ");

        moisCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        anneeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2016", "2015", "2014", " " }));

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        errorAskLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorAskLabel.setText("jLabel1");

        errorPayements.setForeground(new java.awt.Color(255, 0, 0));
        errorPayements.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(titreLabel)
                            .addGap(260, 260, 260))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(payementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(payementLabel)
                                .addComponent(loginEmployeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(140, 140, 140)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorPayements)
                            .addComponent(errorAskLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(askButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(moisLabel)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(moisCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(anneeCombo, 0, 98, Short.MAX_VALUE))))
                                .addComponent(payementsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(errorPayementLabel)
                                .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(payementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(payementLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loginEmployeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorPayementLabel)
                .addGap(18, 18, 18)
                .addComponent(payementsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorPayements)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(askButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(moisLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(moisCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(anneeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorAskLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void askButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_askButtonActionPerformed
        resetError();
        
        //Recuperation de la frame pour les methodes de connexion
        GUIsalary frame = (GUIsalary)SwingUtilities.getWindowAncestor(this);
        
        String date = anneeCombo.getSelectedItem()+"/"+moisCombo.getSelectedItem();
        
        frame.SendMsg("ASK_PAYEMENTS#"+date);
        String[] resutat = frame.ReceiveMsg().split("#");

        if(resutat[0].equals("ERR"))
        {
            errorAskLabel.setText(resutat[1]);
            errorAskLabel.setVisible(true);
            return;
        }
        
        DisplayPanel d = frame.getDisplay();
        d.setTitle("Payements effectués le " + date);
        d.setDisplay(resutat, "OK");
        
        frame.changeLayout("display");
    }//GEN-LAST:event_askButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        resetError();
        
        //Recuperation de la frame pour les methodes de connexion
        GUIsalary frame = (GUIsalary)SwingUtilities.getWindowAncestor(this);
        
        frame.SendMsg("LOGOUT");
        frame.changeLayout("connexion");
        return;
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void payementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payementButtonActionPerformed
        
        resetError();
        //Recuperation de la frame pour les methodes de connexion
        GUIsalary frame = (GUIsalary)SwingUtilities.getWindowAncestor(this);
        
        if(loginEmployeTF.getText().isEmpty())
        {
            errorPayementLabel.setText("Vous devez mentionner un login d'employé");
            errorPayementLabel.setVisible(true);
            return;
        }
        String login = loginEmployeTF.getText();
        //On envoie le login
        frame.SendMsg("LAUNCH_PAYEMENT#"+login);
        
        String[] resultat = frame.ReceiveMsg().split("#");
        
        if(resultat[0].equals("ERR"))
        {
            errorPayementLabel.setText(resultat[1]);
            errorPayementLabel.setVisible(true);
            return;
        }
        
        DisplayPanel d = frame.getDisplay();
        d.setTitle("Payements effectués pour " + login);
        d.setDisplay(resultat, "OK");
        
        frame.changeLayout("display");
    }//GEN-LAST:event_payementButtonActionPerformed

    private void payementsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payementsButtonActionPerformed
        resetError();
        //Recuperation de la frame pour les methodes de connexion
        GUIsalary frame = (GUIsalary)SwingUtilities.getWindowAncestor(this);

        frame.SendMsg("LAUNCH_PAYEMENTS");
        
        String[] resultat = frame.ReceiveMsg().split("#");
        
        if(resultat[0].equals("ERR"))
        {
            errorPayements.setText(resultat[1]);
            errorPayements.setVisible(true);
            return;
        }
        
        DisplayPanel d = frame.getDisplay();
        d.setTitle("Payements effectués");
        d.setDisplay(resultat, "OK");
        
        frame.changeLayout("display");
    }//GEN-LAST:event_payementsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> anneeCombo;
    private javax.swing.JButton askButton;
    private javax.swing.JLabel errorAskLabel;
    private javax.swing.JLabel errorPayementLabel;
    private javax.swing.JLabel errorPayements;
    private javax.swing.JTextField loginEmployeTF;
    private javax.swing.JButton logoutButton;
    private javax.swing.JComboBox<String> moisCombo;
    private javax.swing.JLabel moisLabel;
    private javax.swing.JButton payementButton;
    private javax.swing.JLabel payementLabel;
    private javax.swing.JButton payementsButton;
    private javax.swing.JLabel titreLabel;
    // End of variables declaration//GEN-END:variables
}

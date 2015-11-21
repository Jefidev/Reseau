/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application_trafic;

import java.awt.CardLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author John
 */
public class liste_Panel extends javax.swing.JPanel {

    /**
     * Creates new form liste_Panel
     */
    public liste_Panel() {
        initComponents();
        resultList.setModel(new DefaultListModel());
        errorLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rechercheTitre = new javax.swing.JLabel();
        rechercheCombo = new javax.swing.JComboBox();
        critereLabel = new javax.swing.JLabel();
        resultatLabel = new javax.swing.JLabel();
        recherchePanel = new javax.swing.JPanel();
        date_panel1 = new application_trafic.recherchePanel.date_panel();
        societe_panel1 = new application_trafic.recherchePanel.societe_panel();
        destination_panel1 = new application_trafic.recherchePanel.destination_panel();
        menuButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList();
        errorLabel = new javax.swing.JLabel();
        titre = new javax.swing.JLabel();

        rechercheTitre.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        rechercheTitre.setText("Recherche mouvements");

        rechercheCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date", "Nom société", "Destination" }));
        rechercheCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheComboActionPerformed(evt);
            }
        });

        critereLabel.setText("Critère de recherche : ");

        resultatLabel.setText("Résultat : ");

        recherchePanel.setLayout(new java.awt.CardLayout());
        recherchePanel.add(date_panel1, "date");
        recherchePanel.add(societe_panel1, "societe");
        recherchePanel.add(destination_panel1, "destination");

        menuButton.setText("Menu");
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(resultList);

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("jLabel1");

        titre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        titre.setText("id  ---  Container  ---   Transporteur  entrant --- date arrivée --- transporteur sortant --- poid --- date depart ---  destination --- societe");
        titre.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resultatLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(rechercheTitre)
                                .addGap(169, 169, 169)
                                .addComponent(menuButton))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errorLabel))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(titre)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(critereLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rechercheCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recherchePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(94, 94, 94))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rechercheTitre)
                    .addComponent(menuButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(critereLabel)
                        .addComponent(rechercheCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(recherchePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(resultatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titre)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(errorLabel)
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rechercheComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheComboActionPerformed
        if(rechercheCombo.getSelectedIndex() == 0)
            ((CardLayout)recherchePanel.getLayout()).show(recherchePanel, "date");

        else if(rechercheCombo.getSelectedIndex() == 1)
            ((CardLayout)recherchePanel.getLayout()).show(recherchePanel, "societe");
        
        else
            ((CardLayout)recherchePanel.getLayout()).show(recherchePanel, "destination");
    }//GEN-LAST:event_rechercheComboActionPerformed

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        GUI_Trafic frame = (GUI_Trafic)SwingUtilities.getWindowAncestor(this);
        ((CardLayout)frame.getContentPane().getLayout()).show(frame.getContentPane(), "menu");
    }//GEN-LAST:event_menuButtonActionPerformed
    
    
    public void recherche(String msg)
    {
        errorLabel.setVisible(false);
        GUI_Trafic frame = (GUI_Trafic)SwingUtilities.getWindowAncestor(this);
        
        frame.SendMsg(msg);
        ((DefaultListModel)resultList.getModel()).clear();
        
        String[] reponse = frame.ReceiveMsg().split("#");
        
        if(reponse[0].equals("ERR"))
        {
            errorLabel.setText(reponse[1]);
            errorLabel.setVisible(true);
            return;
        }
        
        ((DefaultListModel)resultList.getModel()).clear();

        for(String s : reponse)
        {
            if(s.equals("ACK"))
                continue;
            
            ((DefaultListModel)resultList.getModel()).addElement(s);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel critereLabel;
    private application_trafic.recherchePanel.date_panel date_panel1;
    private application_trafic.recherchePanel.destination_panel destination_panel1;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton menuButton;
    private javax.swing.JComboBox rechercheCombo;
    private javax.swing.JPanel recherchePanel;
    private javax.swing.JLabel rechercheTitre;
    private javax.swing.JList resultList;
    private javax.swing.JLabel resultatLabel;
    private application_trafic.recherchePanel.societe_panel societe_panel1;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables

}

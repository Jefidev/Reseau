/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applic_data_mining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.math3.stat.inference.TTest;

/**
 *
 * @author Jerome
 */
public class GUI_Data_Mining extends javax.swing.JFrame {
    
    private ArrayList<Mais> donneesMais;
    
    /**
     * Creates new form GUI_Data_Mining
     */
    public GUI_Data_Mining() {
        initComponents();
        resetErrorLabel();
    }
    
    public final void resetErrorLabel()
    {
        errorLabel.setVisible(false);
        errorLabelEnonce1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Choisir = new javax.swing.JButton();
        filePathTextArea = new javax.swing.JTextField();
        titreLabel = new javax.swing.JLabel();
        fichierLabel = new javax.swing.JLabel();
        importationButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        enonce1Label = new javax.swing.JLabel();
        cmTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        choixParcelleCombo = new javax.swing.JComboBox();
        verifierHauteur = new javax.swing.JButton();
        errorLabelEnonce1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Choisir.setText("Choisir");
        Choisir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChoisirActionPerformed(evt);
            }
        });

        filePathTextArea.setEditable(false);

        titreLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titreLabel.setText("Appli Data Mining");

        fichierLabel.setText("Fichier de donnée :");

        importationButton.setText("Importer");
        importationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importationButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("jLabel1");

        enonce1Label.setText("La hauteur des pieds est de ");

        jLabel1.setText("CM pour la parcelle");

        choixParcelleCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nord", "Sud", "Est", "Ouest" }));

        verifierHauteur.setText("Vérifier");
        verifierHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierHauteurActionPerformed(evt);
            }
        });

        errorLabelEnonce1.setForeground(new java.awt.Color(255, 0, 0));
        errorLabelEnonce1.setText("jLabel2");

        jLabel2.setText("La hauteur de pied est-elle similaire pour la parcelle ");
        jLabel2.setToolTipText("");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titreLabel)
                .addGap(182, 182, 182))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filePathTextArea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Choisir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importationButton)
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fichierLabel)
                            .addComponent(errorLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enonce1Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmTF, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choixParcelleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verifierHauteur))
                            .addComponent(errorLabelEnonce1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(129, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel)
                .addGap(17, 17, 17)
                .addComponent(fichierLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Choisir)
                    .addComponent(filePathTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importationButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabel)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enonce1Label)
                    .addComponent(cmTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(choixParcelleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifierHauteur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabelEnonce1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChoisirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChoisirActionPerformed
        //Affichage d'une dialog pour choisir un fichier à parser
        JFileChooser jfc = new JFileChooser();
        jfc.showDialog(this, "Fichier Maïs");
        
        //Si aucun fichier n'a été selectionné on return
        if(jfc.getSelectedFile() == null)
            return;
        //on stock le path dans la textArea
        filePathTextArea.setText(jfc.getSelectedFile().getAbsolutePath());
        
    }//GEN-LAST:event_ChoisirActionPerformed

    private void importationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importationButtonActionPerformed
        
        resetErrorLabel();
        
        if(filePathTextArea.getText().isEmpty())
        {
            errorLabel.setText("Vous devez choisir un fichier à importer");
            errorLabel.setVisible(true);
            return;
        }
        
        BufferedReader readFile = null;
        try {
            readFile = new BufferedReader(new FileReader(filePathTextArea.getText()));
        } 
        catch (FileNotFoundException ex) 
        {
            errorLabel.setText("Impossible d'ouvrir le fichier");
            errorLabel.setVisible(true);
            return;
        }
        
        String ligne;
        donneesMais = new ArrayList<>();
        //Lecture du fichier ligne par ligne
        try {
            while((ligne = readFile.readLine()) != null)
            {
                //split de la chaine sur les tabulations
                //Contenus 15 champs -> champs utilisés : [1]Hauteur, [2]Masse, [3]NbrGrains, 
                //[7] Enracinement, [10]Parcelle, [11] Hauteur J7
                String[] champ = ligne.split("\\t");
                
                //On test pour savoir si ce n'est pas l'entête du fichier
                if(champ[0].equalsIgnoreCase("Individu"))
                    continue;
                
                //Pas assez de données pour le champs lu -> on zap
                if(champ.length != 15)
                    continue;
                
                //TRAITEMENT DES DONNEES
                int hauteur, masse, nbrGrain, hauteurJ7;
                String enracinement, parcelle;
                
                //Remplissage des champs (NA = valeur inconnue)
                if(champ[1].equalsIgnoreCase("NA"))
                    hauteur = -1;
                else
                    hauteur = Integer.parseInt(champ[1]);
                
                if(champ[2].equalsIgnoreCase("NA"))
                    masse = -1;
                else
                    masse = Integer.parseInt(champ[2]);
                
                if(champ[3].equalsIgnoreCase("NA"))
                    nbrGrain = -1;
                else
                    nbrGrain = Integer.parseInt(champ[3]);
                
                if(champ[7].equalsIgnoreCase("NA"))
                    enracinement = null;
                else
                    enracinement = champ[7];
                
                if(champ[10].equalsIgnoreCase("NA"))
                    parcelle = null;
                else
                    parcelle = champ[10];
                
                if(champ[11].equalsIgnoreCase("NA"))
                    hauteurJ7 = -1;
                else
                    hauteurJ7 =  Integer.parseInt(champ[11]);
                
                //Nouvel objet maïs et remplissage tableau
                
                donneesMais.add(new Mais(hauteur, hauteurJ7, parcelle, enracinement, masse, nbrGrain));
                
            }
        } catch (IOException ex) {
            errorLabel.setText("Impossible de lire le fichier");
            errorLabel.setVisible(true);
        }
    }//GEN-LAST:event_importationButtonActionPerformed

    private void verifierHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierHauteurActionPerformed
        resetErrorLabel();
        
        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer une fichier de données");
            errorLabel.setVisible(true);
            return;
        }
        
        //Recuperation de la taille dans le textField
        int taille;
        
        try
        {
            taille = Integer.parseInt(cmTF.getText());
        }
        catch(NumberFormatException ex)
        {
            errorLabelEnonce1.setText("Vous devez rentrer un nombre de centimetre valide");
            errorLabelEnonce1.setVisible(true);
            return;
        }
        
        //Recuperation des donnees pour le calcul (hauteur des plans sur la parcelle choisie)
        ArrayList<Double> tabTemp = new ArrayList<>();
        for(Mais m : donneesMais)
        {
            if(m.getParcelle().equals(choixParcelleCombo.getSelectedItem().toString()))
            {
               if(m.getHauteur() != -1)//Si la hauteur est connue
                   tabTemp.add((double)m.getHauteur());
            } 
        }
        
        //On prepare un tableau por ttest
        double[] tabTest = new double[tabTemp.size()];
        
        int indice = 0;
        for(double d : tabTemp)
        {
            tabTest[indice] = d;
            indice++;
        }
        
        //Test
        TTest test = new TTest();
        double pvalue = test.tTest(taille, tabTest);
        
        //Affichage dialog résultat
        String hypothese = "\"La hauteur des pieds de la parcelle " + choixParcelleCombo.getSelectedItem().toString() + " est de "+taille+"cm\"";
        
        if(pvalue < 0.025)
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est a rejeter. (pvalue : " + pvalue + ")" );
        else
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est validée. (pvalue : " + pvalue + ")" );
  
    }//GEN-LAST:event_verifierHauteurActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Data_Mining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Data_Mining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Data_Mining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Data_Mining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Data_Mining().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Choisir;
    private javax.swing.JComboBox choixParcelleCombo;
    private javax.swing.JTextField cmTF;
    private javax.swing.JLabel enonce1Label;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel errorLabelEnonce1;
    private javax.swing.JLabel fichierLabel;
    private javax.swing.JTextField filePathTextArea;
    private javax.swing.JButton importationButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel titreLabel;
    private javax.swing.JButton verifierHauteur;
    // End of variables declaration//GEN-END:variables
}
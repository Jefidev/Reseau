package applic_data_mining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.apache.commons.math3.stat.inference.TTest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class GUI_Data_Mining extends javax.swing.JFrame {
    
    private ArrayList<Mais> donneesMais;

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
        parcelleSimilaire1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        parcelleSimilaire2 = new javax.swing.JComboBox();
        verifierSimilaire = new javax.swing.JButton();
        errorSimilaireLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        verifier4ParcellesButton = new javax.swing.JButton();
        parseokLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        enracinementCombo = new javax.swing.JComboBox();
        verifierEnracinementButton = new javax.swing.JButton();
        errorEnracinementLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        parcelle5Combo = new javax.swing.JComboBox();
        verifierQ5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        verifierQ6Button = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        parcelle1Q7Combobox = new javax.swing.JComboBox();
        et = new javax.swing.JLabel();
        parcelle2Q7Combobox = new javax.swing.JComboBox();
        verifierQ7 = new javax.swing.JButton();
        errorLabelQ7 = new javax.swing.JLabel();
        error4parcellesLabel = new javax.swing.JLabel();

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

        parcelleSimilaire1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nord", "Sud", "Est", "Ouest" }));

        jLabel3.setText("et la ");

        parcelleSimilaire2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nord", "Sud", "Est", "Ouest" }));

        verifierSimilaire.setText("Vérifier");
        verifierSimilaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierSimilaireActionPerformed(evt);
            }
        });

        errorSimilaireLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorSimilaireLabel.setText("jLabel4");

        jLabel4.setText("La hauteur des pieds est similaires pour les 4 parcelles ? ");

        verifier4ParcellesButton.setActionCommand("Vérifier");
        verifier4ParcellesButton.setLabel("Vérifier");
        verifier4ParcellesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifier4ParcellesButtonActionPerformed(evt);
            }
        });

        parseokLabel.setForeground(new java.awt.Color(0, 153, 51));
        parseokLabel.setText("Parsing reussis");

        jLabel5.setText("Hauteur similaire pour les 4 parcelles selon l'enracinement. Enracinement :  ");

        enracinementCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Faible", "Moyen", "Fort", "Tres.fort" }));

        verifierEnracinementButton.setText("Vérifier");
        verifierEnracinementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierEnracinementButtonActionPerformed(evt);
            }
        });

        errorEnracinementLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorEnracinementLabel.setText("Pas assez de valeurs pour cet enracinement");

        jLabel6.setText("La hauteur dépend-elle de la masse dans la parcelle ");

        parcelle5Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nord", "Sud", "Est", "Ouest" }));

        verifierQ5.setText("Vérifier");
        verifierQ5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierQ5ActionPerformed(evt);
            }
        });

        jLabel7.setText("Le nombre de grains dépend-il de la hauteur ?");

        verifierQ6Button.setText("Vérifier");
        verifierQ6Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierQ6ButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Les variations des hauteurs des pieds sont-elles similaires pour les parcelles ");

        parcelle1Q7Combobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nord", "Sud", "Est", "Ouest" }));

        et.setText("et");

        parcelle2Q7Combobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nord", "Sud", "Est", "Ouest" }));

        verifierQ7.setText("vérifier");
        verifierQ7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierQ7ActionPerformed(evt);
            }
        });

        errorLabelQ7.setForeground(new java.awt.Color(255, 0, 0));
        errorLabelQ7.setText("jLabel9");

        error4parcellesLabel.setForeground(new java.awt.Color(255, 0, 0));
        error4parcellesLabel.setText("jLabel4");

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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(errorLabel)
                                .addGap(302, 302, 302)
                                .addComponent(parseokLabel))
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
                                .addComponent(parcelleSimilaire1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(parcelleSimilaire2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verifierSimilaire))
                            .addComponent(errorSimilaireLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verifier4ParcellesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(error4parcellesLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enracinementCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(verifierEnracinementButton))
                            .addComponent(errorEnracinementLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(parcelle5Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verifierQ5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verifierQ6Button))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(parcelle1Q7Combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(et)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(parcelle2Q7Combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(verifierQ7))
                            .addComponent(errorLabelQ7))
                        .addContainerGap(53, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errorLabel)
                    .addComponent(parseokLabel))
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
                    .addComponent(parcelleSimilaire1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(parcelleSimilaire2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifierSimilaire))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorSimilaireLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(verifier4ParcellesButton)
                    .addComponent(error4parcellesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(enracinementCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifierEnracinementButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorEnracinementLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(parcelle5Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifierQ5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(verifierQ6Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(parcelle1Q7Combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(et)
                    .addComponent(parcelle2Q7Combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifierQ7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabelQ7)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        public GUI_Data_Mining() {
        initComponents();
        resetErrorLabel();
    }
    
    
        
        
    public final void resetErrorLabel()
    {
        errorLabel.setVisible(false);
        errorLabelEnonce1.setVisible(false);
        errorSimilaireLabel.setVisible(false);
        parseokLabel.setVisible(false);
        errorEnracinementLabel.setVisible(false);
        errorLabelQ7.setVisible(false);
        error4parcellesLabel.setVisible(false);
    }
    
    
    
    
    private void ChoisirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChoisirActionPerformed
        //Affichage d'une dialog pour choisir un fichier à parser
        JFileChooser jfc = new JFileChooser();
        jfc.showDialog(this, "OK");
        
        //Si aucun fichier n'a été selectionné on return
        if(jfc.getSelectedFile() == null)
            return;
        
        //on stocke le path dans la textArea
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
        try
        {
            while((ligne = readFile.readLine()) != null)
            {
                //split de la chaine sur les tabulations
                //Contenu : 15 champs -> champs utilisés : [1]Hauteur, [2]Masse, [3]NbrGrains, 
                //[7] Enracinement, [10]Parcelle, [11] Hauteur J7
                String[] champ = ligne.split("\\t");
                
                //On teste pour savoir si ce n'est pas l'en-tête du fichier
                if(champ[0].equalsIgnoreCase("Individu"))
                    continue;
                
                //Pas assez de données pour le champs lu -> on zappe
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
        
        parseokLabel.setVisible(true);
    }//GEN-LAST:event_importationButtonActionPerformed

    
    
    
    private void verifierHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierHauteurActionPerformed
        resetErrorLabel();
        
        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer un fichier de données");
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
            errorLabelEnonce1.setText("Vous devez rentrer un nombre de centimetres valide");
            errorLabelEnonce1.setVisible(true);
            return;
        }
        
        //Recuperation des donnees pour le calcul (hauteur des plans sur la parcelle choisie)
        double[] tabTest = donneesMais.stream()
                .filter(m -> m.getParcelle().equals(choixParcelleCombo.getSelectedItem().toString()))
                .filter(m -> m.getHauteur() != -1)  // Hauteur connue
                .mapToDouble(m -> m.getHauteur())
                .toArray();
               
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

    
    
    
    private void verifierSimilaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierSimilaireActionPerformed
        resetErrorLabel();

        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer une fichier de données");
            errorLabel.setVisible(true);
            return;
        }
        
        //parcelles différentes
        if(parcelleSimilaire1.getSelectedItem().equals(parcelleSimilaire2.getSelectedItem()))
        {
            errorSimilaireLabel.setText("Les parcelles à comparer doivent être différentes");
            errorSimilaireLabel.setVisible(true);
            return;
        }
        
        //Recuperation des donnees
        ArrayList<Double> hauteurParcelle1 = new ArrayList<>();
        ArrayList<Double> hauteurParcelle2 = new ArrayList<>();
        
        for(Mais m : donneesMais)
        {
            if(m.getHauteur() == -1)
                continue;
            
            if(m.getParcelle().equals(parcelleSimilaire1.getSelectedItem()))
                hauteurParcelle1.add((double)m.getHauteur());
            
            if(m.getParcelle().equals(parcelleSimilaire2.getSelectedItem()))
                hauteurParcelle2.add((double)m.getHauteur());  
        }
        
        //Preparation des tableaux pour le ttest
        int taille = hauteurParcelle1.size();
        if(hauteurParcelle2.size() < taille)
            taille = hauteurParcelle2.size();
        
        double[] tabParcelle1 = new double[taille];
        double[] tabParcelle2 = new double[taille];
        
        for(int cpt = 0; cpt < taille; cpt++)
        {
            tabParcelle1[cpt] = hauteurParcelle1.get(cpt);
            tabParcelle2[cpt] = hauteurParcelle2.get(cpt);
        }
        
        //Test
        TTest test = new TTest();
        double pvalue = test.tTest(tabParcelle1, tabParcelle2);
        
        //Affichage dialog résultat
        String hypothese = "\"La hauteur des pieds de la parcelle " + parcelleSimilaire1.getSelectedItem() + " est similaire à celle de la parcelle ";
        hypothese += parcelleSimilaire2.getSelectedItem() + " \"";
        
        if(pvalue < 0.025)
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est a rejeter. (pvalue : " + pvalue + ")" );
        else
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est validée. (pvalue : " + pvalue + ")" );
    }//GEN-LAST:event_verifierSimilaireActionPerformed

    
    
    
    private void verifier4ParcellesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifier4ParcellesButtonActionPerformed
        resetErrorLabel();
        
        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer un fichier de données");
            errorLabel.setVisible(true);
            return;
        }
        
        //Recuperation des donnees
        ArrayList<Double> hauteurParcelle1 = new ArrayList<>();
        ArrayList<Double> hauteurParcelle2 = new ArrayList<>();
        ArrayList<Double> hauteurParcelle3 = new ArrayList<>();
        ArrayList<Double> hauteurParcelle4 = new ArrayList<>();
        
        for(Mais m : donneesMais)
        {
            if(m.getHauteur() == -1)
                continue;
            
            if(m.getParcelle().equalsIgnoreCase("Nord"))
                hauteurParcelle1.add((double)m.getHauteur());
            
            else if(m.getParcelle().equalsIgnoreCase("Sud"))
                hauteurParcelle2.add((double)m.getHauteur());
            
            else if(m.getParcelle().equalsIgnoreCase("Est"))
                hauteurParcelle3.add((double)m.getHauteur());
            
            else if(m.getParcelle().equalsIgnoreCase("Ouest"))
                hauteurParcelle4.add((double)m.getHauteur());
        }
        
        //Preparation des tableaux pour le ttest
        int taille = hauteurParcelle1.size();
        
        if(hauteurParcelle2.size() < taille)
            taille = hauteurParcelle2.size();
        
        if(hauteurParcelle3.size() < taille)
            taille = hauteurParcelle3.size();
        
        if(hauteurParcelle4.size() < taille)
            taille = hauteurParcelle4.size();
        
        double[] tabParcelle1 = new double[taille];
        double[] tabParcelle2 = new double[taille];
        double[] tabParcelle3 = new double[taille];
        double[] tabParcelle4 = new double[taille];
        
        
        for(int cpt = 0; cpt < taille; cpt++)
        {
            tabParcelle1[cpt] = hauteurParcelle1.get(cpt);
            tabParcelle2[cpt] = hauteurParcelle2.get(cpt);
            tabParcelle3[cpt] = hauteurParcelle3.get(cpt);
            tabParcelle4[cpt] = hauteurParcelle4.get(cpt);
        }
        
        ArrayList<double[]> arrayAnova = new ArrayList<>();
        
        arrayAnova.add(tabParcelle1);
        arrayAnova.add(tabParcelle2);
        arrayAnova.add(tabParcelle3);
        arrayAnova.add(tabParcelle4);
        
        //Test
        OneWayAnova test = new OneWayAnova();
        double pvalue = 0;
        
        try
        {
            pvalue = test.anovaPValue(arrayAnova);
        }
        catch(DimensionMismatchException ex)
        {
            error4parcellesLabel.setText("Pas assez de valeurs dans l'échantillon");
            error4parcellesLabel.setVisible(true);
            return;
        }
        
        //Affichage dialog résultat
        String hypothese = "\"La hauteur des pieds des 4 parcelles est similaire\"";
        
        if(pvalue < 0.025)
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est a rejeter. (pvalue : " + pvalue + ")" );
        else
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est validée. (pvalue : " + pvalue + ")" );
    }//GEN-LAST:event_verifier4ParcellesButtonActionPerformed

    
    
    
    private void verifierEnracinementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierEnracinementButtonActionPerformed
        resetErrorLabel();
        
        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer un fichier de données");
            errorLabel.setVisible(true);
            return;
        }
        
        //Recuperation des donnees
        ArrayList<Double> hauteurParcelle1 = new ArrayList<>();
        ArrayList<Double> hauteurParcelle2 = new ArrayList<>();
        ArrayList<Double> hauteurParcelle3 = new ArrayList<>();
        ArrayList<Double> hauteurParcelle4 = new ArrayList<>();
        
        for(Mais m : donneesMais)
        {
            //Si hauteur inconnue on zappe
            if(m.getHauteur() == -1)
                continue;
            
            //Si l'enracinement ne correspond pas à celui choisi on zappe
            if(!m.getEnracinement().equalsIgnoreCase(enracinementCombo.getSelectedItem().toString()))
                continue;
            
            if(m.getParcelle().equalsIgnoreCase("Nord"))
                hauteurParcelle1.add((double)m.getHauteur());
            
            else if(m.getParcelle().equalsIgnoreCase("Sud"))
                hauteurParcelle2.add((double)m.getHauteur());
            
            else if(m.getParcelle().equalsIgnoreCase("Est"))
                hauteurParcelle3.add((double)m.getHauteur());
            
            else if(m.getParcelle().equalsIgnoreCase("Ouest"))
                hauteurParcelle4.add((double)m.getHauteur());
        }
        
        //Preparation des tableaux pour le ttest
        int taille = hauteurParcelle1.size();
        
        if(hauteurParcelle2.size() < taille)
            taille = hauteurParcelle2.size();
        
        if(hauteurParcelle3.size() < taille)
            taille = hauteurParcelle3.size();
        
        if(hauteurParcelle4.size() < taille)
            taille = hauteurParcelle4.size();
        
        double[] tabParcelle1 = new double[taille];
        double[] tabParcelle2 = new double[taille];
        double[] tabParcelle3 = new double[taille];
        double[] tabParcelle4 = new double[taille];
        
        
        for(int cpt = 0; cpt < taille; cpt++)
        {
            tabParcelle1[cpt] = hauteurParcelle1.get(cpt);
            tabParcelle2[cpt] = hauteurParcelle2.get(cpt);
            tabParcelle3[cpt] = hauteurParcelle3.get(cpt);
            tabParcelle4[cpt] = hauteurParcelle4.get(cpt);
        }
        
        ArrayList<double[]> arrayAnova = new ArrayList<>();
        
        arrayAnova.add(tabParcelle1);
        arrayAnova.add(tabParcelle2);
        arrayAnova.add(tabParcelle3);
        arrayAnova.add(tabParcelle4);
        
        //Test
        OneWayAnova test = new OneWayAnova();
        double pvalue  = 0;
        
        try
        {
            pvalue = test.anovaPValue(arrayAnova);
        }
        catch(DimensionMismatchException ex)
        {
            errorEnracinementLabel.setVisible(true);
            return;
        }
        //Affichage dialog résultat
        String hypothese = "\"La hauteur des pieds des 4 parcelles est similaire pour un enracinement "+ enracinementCombo.getSelectedItem()+ " \"";
        
        if(pvalue < 0.025)
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est a rejeter. (pvalue : " + pvalue + ")" );
        else
            JOptionPane.showMessageDialog(this, "L'hypothèse "+ hypothese+" est validée. (pvalue : " + pvalue + ")" );
    }//GEN-LAST:event_verifierEnracinementButtonActionPerformed
    
    
    
    
    //La hauteur dépend-elle de la masse dans une parcelle donnée ?
    private void verifierQ5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierQ5ActionPerformed
        resetErrorLabel();
        
        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer un fichier de données");
            errorLabel.setVisible(true);
            return;
        }
        
        //Recuperation des donnees
        ArrayList<Double> hauteurTemp = new ArrayList<>();
        ArrayList<Double> masseTemp = new ArrayList<>();
        
        for(Mais m : donneesMais)
        {
            //Si on ne connait pas la hauteur ou la masse
            if(m.getHauteur() == -1 || m.getMasse() == -1)
                continue;
            
            //Si ce n'est pas la bonne parcelle
            if(!m.getParcelle().equalsIgnoreCase(parcelle5Combo.getSelectedItem().toString()))
                continue;
            
            hauteurTemp.add((double)m.getHauteur());
            masseTemp.add((double)m.getMasse());
        }
        
        Number[] masseTab = new Double[hauteurTemp.size()];
        Number[] hauteurTab = new Double[hauteurTemp.size()];
        
        //pour le graphe
        XYSeries xys = new XYSeries("Relation hauteur/masse parcelle " + parcelle5Combo.getSelectedItem());
        
        for(int cpt = 0; cpt < hauteurTemp.size(); cpt++)
        {
            masseTab[cpt] = masseTemp.get(cpt);
            hauteurTab[cpt] = hauteurTemp.get(cpt);
            xys.add(masseTab[cpt], hauteurTab[cpt]);
        }

        //Calcul de la correlation
        double r = Statistics.getCorrelation(masseTab, hauteurTab);
        String result = getCorrelationText(r);
        
        System.out.println(r);
        
        XYSeriesCollection xyCollection = new XYSeriesCollection();
        xyCollection.addSeries(xys);
        
        JFreeChart jfc = ChartFactory.createScatterPlot("Relation hauteur/masse parcelle " + parcelle5Combo.getSelectedItem() + " (" + result + " r : "+ r +" )",
                "Masse",
                "Hauteur", 
                xyCollection, 
                PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel cp = new ChartPanel(jfc);
        JDialog graph = new JDialog(this, true);
        
        graph.setSize(600, 600);
        graph.setContentPane(cp);
        graph.setTitle("Relation hauteur/masse parcelle " + parcelle5Combo.getSelectedItem());
        graph.setVisible(true);
    }//GEN-LAST:event_verifierQ5ActionPerformed

    
    
    
    //Le nombre de grains dépend-il de la hauteur ?
    private void verifierQ6ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierQ6ButtonActionPerformed
        resetErrorLabel();
        
        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer un fichier de données");
            errorLabel.setVisible(true);
            return;
        }
        
        //Recuperation des donnees
        ArrayList<Double> hauteurTemp = new ArrayList<>();
        ArrayList<Double> nbrGrainsTemp = new ArrayList<>();
        
        for(Mais m : donneesMais)
        {
            //Si on ne connait pas la hauteur ou la masse
            if(m.getHauteur() == -1 || m.getNbrGrains() == -1)
                continue;
            
            hauteurTemp.add((double)m.getHauteur());
            nbrGrainsTemp.add((double)m.getNbrGrains());
        }
        
        Number[] nbrGrainsTab = new Double[hauteurTemp.size()];
        Number[] hauteurTab = new Double[hauteurTemp.size()];
        
        //pour le graphe
        XYSeries xys = new XYSeries("Relation nombre de grains/hauteur parcelle ");
        
        for(int cpt = 0; cpt < hauteurTemp.size(); cpt++)
        {
            nbrGrainsTab[cpt] = nbrGrainsTemp.get(cpt);
            hauteurTab[cpt] = hauteurTemp.get(cpt);
            xys.add(nbrGrainsTab[cpt], hauteurTab[cpt]);
        }

        //Calcul de la correlation
        double r = Statistics.getCorrelation(nbrGrainsTab, hauteurTab);
        String result = getCorrelationText(r);
        
        XYSeriesCollection xyCollection = new XYSeriesCollection();
        xyCollection.addSeries(xys);
        
        JFreeChart jfc = ChartFactory.createScatterPlot("Relation nombre de grains/hauteur parcelle (" + result + "  r : "+ r +" )", 
                "Nombre de grains",
                "Hauteur", 
                xyCollection, 
                PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel cp = new ChartPanel(jfc);
        JDialog graph = new JDialog(this, true);
        
        graph.setSize(600, 600);
        graph.setContentPane(cp);
        graph.setTitle("Relation nombre de grains/masse parcelle " + parcelle5Combo.getSelectedItem());
        graph.setVisible(true);
    }//GEN-LAST:event_verifierQ6ButtonActionPerformed

    
    
    
    //les variations des hauteurs des pieds sont-elles similaires pour les parcelles Nord et Sud
    private void verifierQ7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierQ7ActionPerformed
        resetErrorLabel();

        if(donneesMais == null)
        {
            errorLabel.setText("Vous devez importer un fichier de données");
            errorLabel.setVisible(true);
            return;
        }
        
        //parcelles différentes
        if(parcelle2Q7Combobox.getSelectedItem().equals(parcelle1Q7Combobox.getSelectedItem()))
        {
            errorLabelQ7.setText("Les parcelles à comparer doivent être différentes");
            errorLabelQ7.setVisible(true);
            return;
        }
        
        //Recuperation des donnees
        ArrayList<Double> arrayListParcelle1 = new ArrayList<>();
        ArrayList<Double> arrayListParcelle2 = new ArrayList<>();
        
        for(Mais m : donneesMais)
        {
            if(m.getHauteur() == -1)
                continue;
            
            if(m.getParcelle().equals(parcelle1Q7Combobox.getSelectedItem()))
                arrayListParcelle1.add((double)m.getHauteur());
            
            else if(m.getParcelle().equals(parcelle2Q7Combobox.getSelectedItem()))
                arrayListParcelle2.add((double)m.getHauteur());
            
        }
        
        //Preparation des tableaux pour l'anova
        int taille = arrayListParcelle1.size();
        
        if(arrayListParcelle2.size() < taille)
            taille = arrayListParcelle2.size();
        
        double[] tabParcelle1 = new double[taille];
        double[] tabParcelle2 = new double[taille];
        
        for(int cpt = 0; cpt < taille; cpt++)
        {
            tabParcelle1[cpt] = arrayListParcelle1.get(cpt);
            tabParcelle2[cpt] = arrayListParcelle2.get(cpt);
        }
        
        ArrayList<double[]> arrayAnova = new ArrayList<>();
        
        arrayAnova.add(tabParcelle1);
        arrayAnova.add(tabParcelle2);
        
        //Test
        OneWayAnova test = new OneWayAnova();
        double fvalue = 0;
        
        try
        {
            fvalue = test.anovaFValue(arrayAnova);
        }
        catch(DimensionMismatchException ex)
        {
            errorLabelQ7.setText("Pas assez de valeur pour effectuer le test");
            errorLabelQ7.setVisible(true);
            return;
        }
        
        String result;
        
        if(fvalue < 97.5)
            result = "Hypothèse validée";
        else
            result = "Hypothèse rejetée";
            
        
        //Affichage du graphique à moustache
        //Creation des deux listes demandées par la methode add de DefaultBoxAndWhiskerCategoryDataset
        DefaultBoxAndWhiskerCategoryDataset dataSet = new DefaultBoxAndWhiskerCategoryDataset();
        dataSet.add(arrayListParcelle1, 1, "parcelle " + parcelle1Q7Combobox.getSelectedItem());
        dataSet.add(arrayListParcelle2, 1, "parcelle " + parcelle2Q7Combobox.getSelectedItem());
        
        JFreeChart jfc = ChartFactory.createBoxAndWhiskerChart("variation de hauteur entre deux parcelles (" + result + " F-value : " + fvalue + ")", 
                "Nom des parcelles",
                "Hauteur", 
                dataSet,
                false);
        
        ChartPanel cp = new ChartPanel(jfc);
        JDialog graph = new JDialog(this, true);
        
        graph.setSize(600, 600);
        graph.setContentPane(cp);
        graph.setTitle("Relation nombre de grains/masse parcelle " + parcelle5Combo.getSelectedItem());
        graph.setVisible(true);
    }//GEN-LAST:event_verifierQ7ActionPerformed
    
    
    
    
    //Renvoie une phrase qui correspond à la signification de la correlation
    private String getCorrelationText(double r)
    {
        if(r < 0.2)
            return "Aucune corrélation";
        else if(r < 0.3)
            return "Lien faible";
        else if(r < 0.6)
            return "Lien modéré";
        else if(r < 0.7)
            return "Lien fort";
        else
            return "Lien très fort";
    }
    
    
    
    
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
    private javax.swing.JComboBox enracinementCombo;
    private javax.swing.JLabel error4parcellesLabel;
    private javax.swing.JLabel errorEnracinementLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel errorLabelEnonce1;
    private javax.swing.JLabel errorLabelQ7;
    private javax.swing.JLabel errorSimilaireLabel;
    private javax.swing.JLabel et;
    private javax.swing.JLabel fichierLabel;
    private javax.swing.JTextField filePathTextArea;
    private javax.swing.JButton importationButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox parcelle1Q7Combobox;
    private javax.swing.JComboBox parcelle2Q7Combobox;
    private javax.swing.JComboBox parcelle5Combo;
    private javax.swing.JComboBox parcelleSimilaire1;
    private javax.swing.JComboBox parcelleSimilaire2;
    private javax.swing.JLabel parseokLabel;
    private javax.swing.JLabel titreLabel;
    private javax.swing.JButton verifier4ParcellesButton;
    private javax.swing.JButton verifierEnracinementButton;
    private javax.swing.JButton verifierHauteur;
    private javax.swing.JButton verifierQ5;
    private javax.swing.JButton verifierQ6Button;
    private javax.swing.JButton verifierQ7;
    private javax.swing.JButton verifierSimilaire;
    // End of variables declaration//GEN-END:variables
}

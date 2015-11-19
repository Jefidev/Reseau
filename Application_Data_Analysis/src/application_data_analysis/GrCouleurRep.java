package application_data_analysis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


public class GrCouleurRep extends javax.swing.JPanel
{
    public GrCouleurRep() {
        initComponents();
        ErrorAnneeLabel.setVisible(false);
        ErrorMoisLabel.setVisible(false);
        ErrorNoDataLabel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titre1Label = new javax.swing.JLabel();
        MenuButton = new javax.swing.JButton();
        AnneeLabel = new javax.swing.JLabel();
        Titre2Label = new javax.swing.JLabel();
        MoisLabel = new javax.swing.JLabel();
        AnneeTF = new javax.swing.JTextField();
        MoisTF = new javax.swing.JTextField();
        CalculerAnneeButton = new javax.swing.JButton();
        CalculerMoisButton = new javax.swing.JButton();
        ErrorAnneeLabel = new javax.swing.JLabel();
        ErrorMoisLabel = new javax.swing.JLabel();
        ErrorNoDataLabel = new javax.swing.JLabel();

        Titre1Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Titre1Label.setForeground(new java.awt.Color(0, 0, 255));
        Titre1Label.setText("Répartition du nombre de containers par destination");

        MenuButton.setText("Retour au menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        AnneeLabel.setText("Année :");

        Titre2Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Titre2Label.setForeground(new java.awt.Color(0, 0, 255));
        Titre2Label.setText("(pour une année ou un mois donné)");

        MoisLabel.setText("Mois :");

        CalculerAnneeButton.setText("Calculer (année)");
        CalculerAnneeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculerAnneeButtonActionPerformed(evt);
            }
        });

        CalculerMoisButton.setText("Calculer (mois)");
        CalculerMoisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculerMoisButtonActionPerformed(evt);
            }
        });

        ErrorAnneeLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErrorAnneeLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorAnneeLabel.setText("Insérer une année valide (entre 1000 et 9999) !");

        ErrorMoisLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErrorMoisLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorMoisLabel.setText("Insérer un mois valide (entre 1 et 12) !");

        ErrorNoDataLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErrorNoDataLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorNoDataLabel.setText("Aucune donnée correspondant aux critères n'a été trouvée");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 86, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(MenuButton)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Titre2Label)
                                .addGap(142, 142, 142))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Titre1Label)
                                .addGap(92, 92, 92))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AnneeLabel)
                                    .addComponent(MoisLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AnneeTF, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(MoisTF))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CalculerAnneeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CalculerMoisButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ErrorMoisLabel)
                                    .addComponent(ErrorAnneeLabel)))
                            .addComponent(ErrorNoDataLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titre1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Titre2Label)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AnneeLabel)
                    .addComponent(AnneeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CalculerAnneeButton)
                    .addComponent(ErrorAnneeLabel))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MoisLabel)
                    .addComponent(MoisTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CalculerMoisButton)
                    .addComponent(ErrorMoisLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(ErrorNoDataLabel)
                .addGap(37, 37, 37)
                .addComponent(MenuButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("Menu");
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void CalculerAnneeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculerAnneeButtonActionPerformed

        ErrorAnneeLabel.setVisible(false);
        ErrorMoisLabel.setVisible(false);
        ErrorNoDataLabel.setVisible(false);
        
        try
        {
            int annee = Integer.parseInt(AnneeTF.getText());
            
            if (annee <= 1000 &&  9999 <= annee)
            {
                ErrorAnneeLabel.setVisible(true);
                return;
            }
            
            
            Utility.SendMsg(ProtocolePIDEP.GET_GR_COULEUR_REP, AnneeTF.getText());
            AnneeTF.setText("");
        
            
            String reponse = Utility.ReceiveMsg();  
            String[] parts = reponse.split("#");
            
            if(parts[0].equals("NON"))
            {
                ErrorNoDataLabel.setVisible(true);
                return;
            }
            
            
            ObjectInputStream ois = new ObjectInputStream(ApplicationDataAnalysis.cliSock.getInputStream());
            HashMap<String, Object> map = (HashMap<String, Object>) ois.readObject();
            ShowPieChart(map);
        }
        catch(NumberFormatException ex)
        {
            ErrorAnneeLabel.setVisible(true);
        }
        catch (IOException ex)
        {
            System.err.println("GrCouleurRep : IOException : " + ex.getMessage());
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("GrCouleurRep : ClassNotFoundException : " + ex.getMessage());
        }
    }//GEN-LAST:event_CalculerAnneeButtonActionPerformed

    private void CalculerMoisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculerMoisButtonActionPerformed
        
        ErrorAnneeLabel.setVisible(false);
        ErrorMoisLabel.setVisible(false);
        ErrorNoDataLabel.setVisible(false);
        
        try
        {
            int mois = Integer.parseInt(MoisTF.getText());
            
            if (mois < 1 || 12 < mois)
            {
                ErrorMoisLabel.setVisible(true);
                return;
            }

            
            Utility.SendMsg(ProtocolePIDEP.GET_GR_COULEUR_REP, MoisTF.getText());
            MoisTF.setText("");
        
            
            String reponse = Utility.ReceiveMsg();  
            String[] parts = reponse.split("#");
            
            if(parts[0].equals("NON"))
            {
                ErrorNoDataLabel.setVisible(true);
                return;
            }
            
            
            ObjectInputStream ois = new ObjectInputStream(ApplicationDataAnalysis.cliSock.getInputStream());
            HashMap<String, Object> map = (HashMap<String, Object>) ois.readObject();
            ShowPieChart(map);
        }
        catch(NumberFormatException ex)
        {
            ErrorMoisLabel.setVisible(true);
            System.err.println("GrCouleurRep : NumberFormatException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("GrCouleurRep : IOException : " + ex.getMessage());
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("GrCouleurRep : ClassNotFoundException : " + ex.getMessage());
        }
    }//GEN-LAST:event_CalculerMoisButtonActionPerformed

    public void ShowPieChart(HashMap<String, Object> map)
    {
        ArrayList<String> listDestinations = (ArrayList<String>)map.get("DESTINATIONS");
        ArrayList<Integer> listCount = (ArrayList<Integer>)map.get("COUNT");
        
        DefaultPieDataset dpds = new DefaultPieDataset();
        for(int i = 0; i < listDestinations.size(); i++)
            dpds.setValue(listDestinations.get(i), listCount.get(i));
        
        JFreeChart jfc = ChartFactory.createPieChart("Répartition du nombre de containers par destination", dpds, true, true, true);
        ChartPanel cp = new ChartPanel(jfc);
        JDialog dialog = new JDialog();
        dialog.setSize(500, 500);
        dialog.setContentPane(cp);
        dialog.setTitle("Répartition du nombre de containers par destination");
        dialog.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AnneeLabel;
    private javax.swing.JTextField AnneeTF;
    private javax.swing.JButton CalculerAnneeButton;
    private javax.swing.JButton CalculerMoisButton;
    private javax.swing.JLabel ErrorAnneeLabel;
    private javax.swing.JLabel ErrorMoisLabel;
    private javax.swing.JLabel ErrorNoDataLabel;
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel MoisLabel;
    private javax.swing.JTextField MoisTF;
    private javax.swing.JLabel Titre1Label;
    private javax.swing.JLabel Titre2Label;
    // End of variables declaration//GEN-END:variables
}

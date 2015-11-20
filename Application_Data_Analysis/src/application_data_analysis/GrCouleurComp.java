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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class GrCouleurComp extends javax.swing.JPanel
{
    public GrCouleurComp()
    {
        initComponents();
        ErrorAnneeLabel.setVisible(false);
        ErrorNoDataLabel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titre1Label = new javax.swing.JLabel();
        Titre2Label = new javax.swing.JLabel();
        AnneeLabel = new javax.swing.JLabel();
        AnneeTF = new javax.swing.JTextField();
        CalculerButton = new javax.swing.JButton();
        ErrorAnneeLabel = new javax.swing.JLabel();
        ErrorNoDataLabel = new javax.swing.JLabel();
        MenuButton = new javax.swing.JButton();

        Titre1Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Titre1Label.setForeground(new java.awt.Color(0, 0, 255));
        Titre1Label.setText("Répartition du nombre de containers par destination");

        Titre2Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Titre2Label.setForeground(new java.awt.Color(0, 0, 255));
        Titre2Label.setText("(pour les trimestres d'une année donnée)");

        AnneeLabel.setText("Année :");

        CalculerButton.setText("Calculer");
        CalculerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculerButtonActionPerformed(evt);
            }
        });

        ErrorAnneeLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErrorAnneeLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorAnneeLabel.setText("Insérer une année valide (entre 1000 et 9999) !");

        ErrorNoDataLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErrorNoDataLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorNoDataLabel.setText("Aucune donnée correspondant aux critères n'a été trouvée");

        MenuButton.setText("Retour au menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(Titre2Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AnneeLabel)
                .addGap(18, 18, 18)
                .addComponent(AnneeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CalculerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(ErrorAnneeLabel)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ErrorNoDataLabel)
                            .addContainerGap(206, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 86, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(MenuButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(Titre1Label)
                                    .addGap(82, 82, 82)))
                            .addGap(10, 10, 10)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(Titre2Label)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AnneeLabel)
                    .addComponent(AnneeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CalculerButton)
                    .addComponent(ErrorAnneeLabel))
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Titre1Label)
                    .addGap(187, 187, 187)
                    .addComponent(ErrorNoDataLabel)
                    .addGap(37, 37, 37)
                    .addComponent(MenuButton)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CalculerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculerButtonActionPerformed

        ErrorAnneeLabel.setVisible(false);
        ErrorNoDataLabel.setVisible(false);

        try
        {
            int annee = Integer.parseInt(AnneeTF.getText());

            if (annee <= 1000 ||  9999 <= annee)
            {
                ErrorAnneeLabel.setVisible(true);
                return;
            }

            Utility.SendMsg(ProtocolePIDEP.GET_GR_COULEUR_COMP, AnneeTF.getText());
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
            ShowBarChart(map);
        }
        catch(NumberFormatException ex)
        {
            ErrorAnneeLabel.setVisible(true);
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("GrCouleurRep : ClassNotFoundException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("GrCouleurRep : IOException : " + ex.getMessage());
        }
    }//GEN-LAST:event_CalculerButtonActionPerformed

    private void ShowBarChart(HashMap<String, Object> map)
    {
        ArrayList<String> listDestinations = (ArrayList<String>)map.get("DESTINATIONS");
        ArrayList<Integer> listCount = (ArrayList<Integer>)map.get("COUNT");
        ArrayList<Integer> listTrimestres = (ArrayList<Integer>)map.get("TRIMESTRES");

        DefaultCategoryDataset dcds = new DefaultCategoryDataset();
        for(int i = 0; i < listDestinations.size(); i++)
            dcds.setValue(listCount.get(i), listTrimestres.get(i), listDestinations.get(i));
               
        JFreeChart jfc = ChartFactory.createBarChart("Répartition du nombre de containers par destination par trimestre", "Destinations", "Occurences", dcds, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel cp = new ChartPanel(jfc);
        JDialog dialog = new JDialog();
        dialog.setSize(500, 500);
        dialog.setContentPane(cp);
        dialog.setTitle("Répartition du nombre de containers par destination par trimestre");
        dialog.setVisible(true);
    }
    
    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        ApplicationDataAnalysis app = (ApplicationDataAnalysis)SwingUtilities.getWindowAncestor(this);
        app.ChangePanel("Menu");
    }//GEN-LAST:event_MenuButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AnneeLabel;
    private javax.swing.JTextField AnneeTF;
    private javax.swing.JButton CalculerButton;
    private javax.swing.JLabel ErrorAnneeLabel;
    private javax.swing.JLabel ErrorNoDataLabel;
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel Titre1Label;
    private javax.swing.JLabel Titre2Label;
    // End of variables declaration//GEN-END:variables
}

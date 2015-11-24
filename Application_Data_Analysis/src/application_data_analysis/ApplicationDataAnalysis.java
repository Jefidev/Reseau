package application_data_analysis;

import java.awt.CardLayout;
import java.net.*;


public class ApplicationDataAnalysis extends javax.swing.JFrame
{
    public static Socket cliSock = null;
    public Boolean isConnected = false;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonGroup = new javax.swing.ButtonGroup();
        Menu = new application_data_analysis.Menu();
        StatDescrCont = new application_data_analysis.StatDescrCont();
        GrCouleurRep = new application_data_analysis.GrCouleurRep();
        GrCouleurComp = new application_data_analysis.GrCouleurComp();
        StatInferTestConf = new application_data_analysis.StatInferTestConf();
        StatInferTestHomog = new application_data_analysis.StatInferTestHomog();
        StatInferTestAnova = new application_data_analysis.StatInferTestAnova();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(Menu, "Menu");
        getContentPane().add(StatDescrCont, "StatDescrCont");
        getContentPane().add(GrCouleurRep, "GrCouleurRep");
        getContentPane().add(GrCouleurComp, "GrCouleurComp");
        getContentPane().add(StatInferTestConf, "StatInferTestConf");
        getContentPane().add(StatInferTestHomog, "StatInferTestHomog");
        getContentPane().add(StatInferTestAnova, "StatInferTestAnova");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public ApplicationDataAnalysis()
    {
        initComponents();
        
        this.setTitle("Data Analysis");
        Utility.InitialisationFlux();

        // Lancement du login
        (new Login(this, true)).setVisible(true);
        System.err.println("isConnected = " + isConnected);
        this.dispose();
    }
    
    public void ChangePanel(String newPanel)
    {
        CardLayout card = (CardLayout)this.getContentPane().getLayout();
        card.show(this.getContentPane(), newPanel);
    }
       
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
            java.util.logging.Logger.getLogger(ApplicationDataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicationDataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicationDataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationDataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApplicationDataAnalysis().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ButtonGroup;
    private application_data_analysis.GrCouleurComp GrCouleurComp;
    private application_data_analysis.GrCouleurRep GrCouleurRep;
    private application_data_analysis.Menu Menu;
    private application_data_analysis.StatDescrCont StatDescrCont;
    private application_data_analysis.StatInferTestAnova StatInferTestAnova;
    private application_data_analysis.StatInferTestConf StatInferTestConf;
    private application_data_analysis.StatInferTestHomog StatInferTestHomog;
    // End of variables declaration//GEN-END:variables
}

package testbeanaccess;

import DBAcess.BeanDBAccessMySql;
import DBAcess.BeanDBAccessOracle;
import DBAcess.InterfaceRequestListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


public class guiTest extends javax.swing.JFrame implements InterfaceRequestListener
{    
    DBAcess.InterfaceBeansDBAccess beanBDD;
    
    public guiTest() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ipTextField = new javax.swing.JTextField();
        ipLabel = new javax.swing.JLabel();
        portTextField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        loginTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        typeBdCombo = new javax.swing.JComboBox();
        connexionButton = new javax.swing.JButton();
        deconnexionButton = new javax.swing.JButton();
        ajoutLabel = new javax.swing.JLabel();
        lectureLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        tableComboBox = new javax.swing.JComboBox();
        tableLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox();
        conditionLabel = new javax.swing.JLabel();
        whereTextField = new javax.swing.JTextField();
        performSelectButton = new javax.swing.JButton();
        loginUtilisateurLabel = new javax.swing.JLabel();
        loginUtilisateurTextField = new javax.swing.JTextField();
        passwordUtilisateurLabel = new javax.swing.JLabel();
        passwordUtilisateurTextField = new javax.swing.JTextField();
        updateCheck = new javax.swing.JCheckBox();
        conditionUpdateTextField = new javax.swing.JTextField();
        executeInstertUpdate = new javax.swing.JButton();
        erreurLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ipTextField.setToolTipText("");

        ipLabel.setText("IP :");

        portLabel.setText("Port :");

        loginLabel.setText("Login :");

        passwordLabel.setText("mot de passe :");

        typeBdCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Oracle", "MySQL" }));

        connexionButton.setText("Connexion");
        connexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionButtonActionPerformed(evt);
            }
        });

        deconnexionButton.setText("Déconnexion");
        deconnexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnexionButtonActionPerformed(evt);
            }
        });

        ajoutLabel.setText("Ajout/modification utilisateur :");

        lectureLabel.setText("Lecture d'une table : ");

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(resultTable);

        tableComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tableLabel.setText("Table : ");

        typeLabel.setText("Type requete :");

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "*", "count(*)" }));

        conditionLabel.setText("WHERE :");

        performSelectButton.setText("SELECT");
        performSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performSelectButtonActionPerformed(evt);
            }
        });

        loginUtilisateurLabel.setText("Login :");
        loginUtilisateurLabel.setToolTipText("");

        passwordUtilisateurLabel.setText("Password : ");

        updateCheck.setText("UPDATE");

        conditionUpdateTextField.setText("condition Update");

        executeInstertUpdate.setText("exécuter");
        executeInstertUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeInstertUpdateActionPerformed(evt);
            }
        });

        erreurLabel.setForeground(new java.awt.Color(255, 0, 0));
        erreurLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ajoutLabel)
                            .addComponent(lectureLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(passwordUtilisateurLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(passwordUtilisateurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(loginUtilisateurLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(loginUtilisateurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(99, 99, 99)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(updateCheck)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(conditionUpdateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(executeInstertUpdate))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(ipLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(portLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(loginLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(passwordLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(passwordField)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(typeBdCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(connexionButton)
                                .addGap(18, 18, 18)
                                .addComponent(deconnexionButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(erreurLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(performSelectButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tableLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(typeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(conditionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whereTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipLabel)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel)
                    .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeBdCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connexionButton)
                    .addComponent(deconnexionButton))
                .addGap(18, 18, 18)
                .addComponent(ajoutLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginUtilisateurLabel)
                    .addComponent(loginUtilisateurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCheck))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordUtilisateurLabel)
                    .addComponent(passwordUtilisateurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(conditionUpdateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(executeInstertUpdate))
                .addGap(17, 17, 17)
                .addComponent(lectureLabel)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableLabel)
                    .addComponent(typeLabel)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(conditionLabel)
                    .addComponent(whereTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(performSelectButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(erreurLabel)
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionButtonActionPerformed
        
        if(beanBDD != null)
            return;
            
        if(0 == typeBdCombo.getSelectedItem().toString().compareTo("Oracle"))
        {
            beanBDD =  new BeanDBAccessOracle();
            beanBDD.setBd("XE");
        }
        else
        {
            beanBDD =  new BeanDBAccessMySql();
            beanBDD.setBd("bdtrafic");   
        }
        
        beanBDD.setIp(ipTextField.getText());
        beanBDD.setPort(Integer.parseInt(portTextField.getText()));
        beanBDD.setUser(loginTextField.getText());
        beanBDD.setPassword(passwordField.getText());
        beanBDD.setClient(this);
        
        beanBDD.connexion();
        
        tableComboBox.setModel(new DefaultComboBoxModel(beanBDD.tablesDisponibles().toArray()));
    }//GEN-LAST:event_connexionButtonActionPerformed

    private void deconnexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnexionButtonActionPerformed
        beanBDD.finConnexion();
        beanBDD = null;
    }//GEN-LAST:event_deconnexionButtonActionPerformed

    private void performSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performSelectButtonActionPerformed
        
        if(beanBDD == null)
            return;
        if(whereTextField.getText().isEmpty())
            beanBDD.selection(typeComboBox.getSelectedItem().toString(), tableComboBox.getSelectedItem().toString(), null);
        else
            beanBDD.selection(typeComboBox.getSelectedItem().toString(), tableComboBox.getSelectedItem().toString(), whereTextField.getText());
    }//GEN-LAST:event_performSelectButtonActionPerformed

    private void executeInstertUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeInstertUpdateActionPerformed
        
        if(beanBDD == null)
            return;
        
        HashMap<String, String> donnee = new HashMap<>();
        donnee.put("LOGIN", loginUtilisateurTextField.getText());
        donnee.put("PASSWORD", passwordUtilisateurTextField.getText());
        
        if(updateCheck.isSelected())
            beanBDD.miseAJour("UTILISATEURS", donnee, conditionUpdateTextField.getText());
        
        else
            beanBDD.ecriture("UTILISATEURS", donnee);
            
    }//GEN-LAST:event_executeInstertUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(guiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(guiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(guiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(guiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new guiTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ajoutLabel;
    private javax.swing.JLabel conditionLabel;
    private javax.swing.JTextField conditionUpdateTextField;
    private javax.swing.JButton connexionButton;
    private javax.swing.JButton deconnexionButton;
    private javax.swing.JLabel erreurLabel;
    private javax.swing.JButton executeInstertUpdate;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JTextField ipTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lectureLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JLabel loginUtilisateurLabel;
    private javax.swing.JTextField loginUtilisateurTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordUtilisateurLabel;
    private javax.swing.JTextField passwordUtilisateurTextField;
    private javax.swing.JButton performSelectButton;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JTable resultTable;
    private javax.swing.JComboBox tableComboBox;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JComboBox typeBdCombo;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JCheckBox updateCheck;
    private javax.swing.JTextField whereTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultRequest(ResultSet res) {
  
        try
        {
            resultTable.setModel(buildTableModel(res));
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
    }
    
    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException{
        
        Vector<String> columnNames = new Vector<String>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        
        ResultSetMetaData metaData = rs.getMetaData();

        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        while (rs.next()) 
        {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return(new DefaultTableModel(data, columnNames));
    }

    @Override
    public void erreurRecue(String erreur) {
        erreurLabel.setText(erreur);
    }
}

package applic_mail;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import javax.swing.SwingUtilities;


public class connexionPanel extends javax.swing.JPanel
{
    public connexionPanel()
    {
        initComponents();
        errorLabel.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titreLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        loginTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        errorLabel = new javax.swing.JLabel();
        connexionButton = new javax.swing.JButton();

        titreLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titreLabel.setText("Messagerie connexion");

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginLabel.setText("Login : ");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordLabel.setText("Mot de passe : ");

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("jLabel1");

        connexionButton.setText("Connexion");
        connexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(errorLabel)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(titreLabel)
                                .addComponent(connexionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(passwordLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(loginLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connexionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorLabel)
                .addContainerGap(125, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void connexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionButtonActionPerformed
        
        errorLabel.setVisible(false);
        if(loginTextField.getText().isEmpty())
        {
            errorLabel.setText("Vous devez rentrer un login");
            errorLabel.setVisible(true);
            return;
        }
        
        if(passwordField.getText().isEmpty())
        {
            errorLabel.setText("Vous devez rentrer un mot de passe");
            errorLabel.setVisible(true);
            return;
        }
        
        GUI_Mail container = (GUI_Mail)SwingUtilities.getWindowAncestor(this);
        Store st = null;
        try
        {
            st = container.getSession().getStore("pop3");
            st.connect(container.getHost(), loginTextField.getText(), passwordField.getText());
            container.setStore(st);
        } 
        catch (NoSuchProviderException ex) 
        {
            errorLabel.setText("Impossible de se connecter");
            errorLabel.setVisible(true);
            ex.printStackTrace();
            return;
        } 
        catch (MessagingException ex) 
        {
            ex.printStackTrace();
            return;
        }
        
        container.setUser(loginTextField.getText());
        loginTextField.setText("");
        passwordField.setText("");
        
        container.changeLayout("inbox");
    }//GEN-LAST:event_connexionButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connexionButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel titreLabel;
    // End of variables declaration//GEN-END:variables
}

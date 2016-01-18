/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applets;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jerome
 */
public class LoginApplet extends javax.swing.JApplet {
    
    HashMap<String, String> paramValue = new HashMap<String, String>();
    
    /**
     * Initializes the applet LoginApplet
     */
    @Override
    public void init() {
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
            java.util.logging.Logger.getLogger(LoginApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        errorLabel.setVisible(false);
        System.out.println("version : 12");
    }
    
    @Override
    public void start()
    {
        errorLabel.setVisible(false);
        
        //Si on a reçus des parametres dans l'URL on les traite
        String url = getDocumentBase().toString();
        
        if(url.indexOf("?") == -1)
            return;
        
        String paramaters = url.substring(url.indexOf("?") + 1);
	parseParam(paramaters);
        
        //Si on reçoit un login
        if(paramValue.get("login") != null)
            loginTextField.setText(paramValue.get("login"));
        
        //Si on reçoit un login et un mot de passe
        if(paramValue.get("mdp") != null)
            passwordField.setText(paramValue.get("mdp"));
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        loginTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        loginLabel.setText("Votre identifiant : ");

        passwordLabel.setText("Votre mot de passe  : ");

        loginButton.setText("Entrer sur le site");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Je n'ai pas de mot de passe");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(passwordLabel)
                                .addComponent(loginLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(loginTextField)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorLabel)
                .addGap(13, 13, 13)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerButton)
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        if(loginTextField.getText().isEmpty())
            return;
        
        //On va établir la connexion par tunnel TCP avec la servlet visée
        String addressServlet = "/CaddieVirtuel/Controler"; //URL de la servlet
        URL pageCourante = getDocumentBase(); //URL de la page courante
        URLConnection connexionServlet = null;
        try {
            //Construction de l'URL de la servlet par rapport à l'Host (localhost ou ip) et au port de la page courante
            URL urlServ = new URL(pageCourante.getProtocol(), pageCourante.getHost(), pageCourante.getPort(), addressServlet);
            connexionServlet = urlServ.openConnection();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        //On veut pas de cache
        connexionServlet.setUseCaches(false);
        connexionServlet.setDefaultUseCaches(false);
        //Mais on veut pouvoir envoyer des trucs
        connexionServlet.setDoOutput(true);
        
        //Le message va être composer en utilisant un flux de byte
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        PrintWriter pw = new PrintWriter(baos, true);
        
        //Composition de la chaine qui constituera le corps du message.
        String parametrePost = null;
        try {
            parametrePost = "login=" + URLEncoder.encode(loginTextField.getText(), "UTF-8");
            parametrePost += "&password=" + URLEncoder.encode(passwordField.getText(), "UTF-8");
            //parametre pour indiquer le traitement à effectuer par la servle de controle
            parametrePost += "&action=login";
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginApplet.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        pw.print(parametrePost);
        pw.flush();

        try {
            //On peut allumer le feu.
            connexionServlet.setRequestProperty("Content-Length", String.valueOf(baos.size()));
            connexionServlet.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            baos.writeTo(connexionServlet.getOutputStream());
            
            BufferedReader e = new BufferedReader(new InputStreamReader(connexionServlet.getInputStream()));
            String r;
            r= e.readLine();
            
            if(r.equalsIgnoreCase("ok"))
            {
                URL s = new URL(pageCourante.getProtocol(), pageCourante.getHost(), pageCourante.getPort(), "/CaddieVirtuel/choixLangue.jsp?login="+loginTextField.getText());
                getAppletContext().showDocument(s);
                return;
            }
            
            errorLabel.setText(r);
            errorLabel.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(LoginApplet.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        
        if(loginTextField.getText().isEmpty())
        {
            errorLabel.setText("Vous devez indiquer un identifiant");
            errorLabel.setVisible(true);
            return; 
        }
        
        try {
            //On envoie le login à la page d'enregistrement
            URL pageCourante = getDocumentBase();
            URL s = new URL(pageCourante.getProtocol(), pageCourante.getHost(), pageCourante.getPort(), "/CaddieVirtuel/register.html?login="+loginTextField.getText());
            getAppletContext().showDocument(s);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    
    
    //parse the URL parameter
   private void parseParam(String parameters){
	  
	   StringTokenizer paramGroup = new StringTokenizer(parameters, "&");
	   
	   while(paramGroup.hasMoreTokens()){
	     
		   StringTokenizer value = new StringTokenizer(paramGroup.nextToken(), "=");
		   paramValue.put(value.nextToken(), value.nextToken());
	   }
   }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registerButton;
    // End of variables declaration//GEN-END:variables
}

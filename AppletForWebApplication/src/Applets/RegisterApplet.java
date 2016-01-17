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
public class RegisterApplet extends javax.swing.JApplet {

    HashMap<String, String> paramValue = new HashMap<String, String>();
    /**
     * Initializes the applet RegisterApplet
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
            java.util.logging.Logger.getLogger(RegisterApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    }
    
    @Override
    public void start()
    {
        errorLabel.setVisible(false);
        
        String url = getDocumentBase().toString();
        
        if(url.indexOf("?") == -1)
        {
            try {
                URL pageCourante = getDocumentBase();
                URL s = new URL(pageCourante.getProtocol(), pageCourante.getHost(), pageCourante.getPort(), "/CaddieVirtuel/index.html");
                getAppletContext().showDocument(s);
            } catch (MalformedURLException ex) {
                Logger.getLogger(RegisterApplet.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        String paramaters = url.substring(url.indexOf("?") + 1);
	parseParam(paramaters);
        
        loginLabel.setText("login : " + paramValue.get("login"));
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
        passwordLabel = new javax.swing.JLabel();
        confirmLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        confirmField = new javax.swing.JPasswordField();
        confirmerButton = new javax.swing.JButton();
        AnnulerButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        loginLabel.setText("Login : ");

        passwordLabel.setText("Mot de passe : ");

        confirmLabel.setText("Confirmer le mot de passe : ");

        confirmerButton.setText("Confirmer");
        confirmerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmerButtonActionPerformed(evt);
            }
        });

        AnnulerButton.setText("Annuler");
        AnnulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loginLabel)
                    .addComponent(passwordLabel)
                    .addComponent(confirmLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField)
                    .addComponent(confirmField, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(AnnulerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(loginLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmLabel)
                    .addComponent(confirmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmerButton)
                    .addComponent(AnnulerButton))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerButtonActionPerformed
        try {
            URL pageCourante = getDocumentBase();
                URL s = new URL(pageCourante.getProtocol(), pageCourante.getHost(), pageCourante.getPort(), "/CaddieVirtuel/index.html?login=" + paramValue.get("login") );
                getAppletContext().showDocument(s);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AnnulerButtonActionPerformed

    
    //Enregistrement du mec dans la BD si il existe pas
    private void confirmerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmerButtonActionPerformed
        
        errorLabel.setVisible(false);
        
        if(passwordField.getText().isEmpty())
            return;
        
        if(!passwordField.getText().equals(confirmField.getText()))
        {
            errorLabel.setText("Les deux mots de passe sont différents");
            errorLabel.setVisible(true);
            return;
        }
        
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
            parametrePost = "login=" + URLEncoder.encode(paramValue.get("login"), "UTF-8");
            parametrePost += "&password=" + URLEncoder.encode(passwordField.getText(), "UTF-8");
            //parametre pour indiquer le traitement à effectuer par la servle de controle
            parametrePost += "&action=register";
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
                String lien = "/CaddieVirtuel/index.html?login="+paramValue.get("login") + "&mdp=" + URLEncoder.encode(passwordField.getText(), "UTF-8");
                URL s = new URL(pageCourante.getProtocol(), pageCourante.getHost(), pageCourante.getPort(), lien);
                getAppletContext().showDocument(s);
                return;
            }
            
            errorLabel.setText(r);
            errorLabel.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(LoginApplet.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }//GEN-LAST:event_confirmerButtonActionPerformed

    
   //parse the URL parameter
   private void parseParam(String parameters){
	  
	   StringTokenizer paramGroup = new StringTokenizer(parameters, "&");
	   
	   while(paramGroup.hasMoreTokens()){
	     
		   StringTokenizer value = new StringTokenizer(paramGroup.nextToken(), "=");
		   paramValue.put(value.nextToken(), value.nextToken());
	   }
   }
   
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnnulerButton;
    private javax.swing.JPasswordField confirmField;
    private javax.swing.JLabel confirmLabel;
    private javax.swing.JButton confirmerButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    // End of variables declaration//GEN-END:variables
}

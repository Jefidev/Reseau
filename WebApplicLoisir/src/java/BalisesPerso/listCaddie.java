/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BalisesPerso;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import newBean.BeanBDAccess;
import newBean.connexionException;

/**
 *
 * @author Jerome
 */
public class listCaddie extends BodyTagSupport {
    
    private boolean erreur;
    private Statement requete;
    
    /**
     * Creates new instance of tag handler
     */
    public listCaddie() {
        super();
        erreur = false;
    }

    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   User methods.                                          ///
    ///                                                          ///
    ///   Modify these methods to customize your tag handler.    ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    /**
     * Method called from doStartTag(). Fill in this method to perform other
     * operations from doStartTag().
     */
    private void otherDoStartTagOperations() {
        BeanBDAccess bd = new BeanBDAccess();
        try {
            bd.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            requete = bd.getConnexion().createStatement();
        } catch (ClassNotFoundException ex) {
            erreur = true;
            System.err.println("Driver introuvable");
        } catch (SQLException ex) {
            erreur = true;
            System.err.println("Exception SQL");
        } catch (connexionException ex) {
            erreur = true;
            System.err.println("Erreur de connexion à la base");
        }
    }

    
    
    /**
     * Method called from doEndTag() Fill in this method to perform other
     * operations from doEndTag().
     */
    private void otherDoEndTagOperations() { }

    
    
    /**
     * Fill in this method to process the body content of the tag. You only need
     * to do this if the tag's BodyContent property is set to "JSP" or
     * "tagdependent." If the tag's bodyContent is set to "empty," then this
     * method will not be called.
     */
    private void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws IOException {
        //On recupere le contenu du caddie dans la session
        String contenuBody = bodyContent.getString();
        HashMap contenuCaddie = (HashMap) pageContext.getSession().getAttribute(contenuBody);
        float prixTotal = 0;
        
        //Affichage reservation place parc
        if(contenuCaddie.get("entreeParc") != null)
        {
            int qtt = (int)contenuCaddie.get("entreeParc");
            String date = (String)contenuCaddie.get("dateParc");
            
            out.println("<div>");
            out.println("<h2>Entree parc</h2>");
            out.println("<p>prix unitaire : 5</p>");
            out.println("<p>Quantite : " + contenuCaddie.get("entreeParc") + "</p>");
            out.println("<p>Date : " + qtt + "</p>");
            out.println("<p>prix total : " + String.valueOf(5 * qtt) + "</p>");

             //Formulaire pour supprimer la commande
            out.println("<form action=\"Controler\" method=\"POST\">");
            //action
            out.println("<input type=\"hidden\" name=\"action\" value=\"suppressionArticle\"/>");
            out.println("<input type=\"hidden\" name=\"idArticle\" value=\"entreeParc\"/>");
            
            out.println("<input type=\"submit\" value=\"Supprimer\">");
            out.println("</form>");
                
            out.println("</div>");
            
            prixTotal += 5*qtt;
        }
        
        try {
            ResultSet rs = requete.executeQuery("select * from produits");
            
            //Parcours des produits
            while(rs.next())
            {
                if(contenuCaddie.get(rs.getInt("ID_PRODUIT")) == null)
                    continue;
                
                //Si le produit a été commandé par le client
                int quantite = (int)contenuCaddie.get(rs.getInt("ID_PRODUIT"));
                float prix = (float) rs.getFloat("PRIX");
                
                out.println("<div>");
                out.println("<h2>"+ rs.getString("NOM") +"</h2>");
                out.println("<p>prix unitaire : " + String.valueOf(prix) + "</p>");
                out.println("<p>Quantite : " + String.valueOf(quantite) + "</p>");
                out.println("<p>prix total : " + String.valueOf(prix * quantite) + "</p>");
                
                //Formulaire pour supprimer la commande
                out.println("<form action=\"Controler\" method=\"POST\">");
                //action
                out.println("<input type=\"hidden\" name=\"action\" value=\"suppressionArticle\"/>");
                out.println("<input type=\"hidden\" name=\"idArticle\" value=\""+ rs.getInt("ID_PRODUIT") +"\"/>");

                out.println("<input type=\"submit\" value=\"Supprimer\">");
                out.println("</form>");
                
                out.println("</div>");
                
                prixTotal += prix * quantite;
            }
            
            
            //Affichage du boutton pour valider les achats
            out.println("<p> Prix total : "+ prixTotal + "</p>");
            out.println("<form action=\"Controler\" method=\"POST\">");
            //action
            out.println("<input type=\"hidden\" name=\"action\" value=\"achat\"/>");
            out.println("<input type=\"hidden\" name=\"total\" value=\"" + prixTotal + "\"/>");
            
            //E-mail facturation
            out.println("<p>E-mail de facturation : </p>");
            out.println("<input type=\"email\" name=\"mailFacture\">");
            
            out.println("<input type=\"submit\" value=\"Acheter\">");
            out.println("</form>");
            
        } catch (SQLException ex) {
            erreur = true;
            return;
        }
        
        bodyContent.clearBody();
    }

    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   Tag Handler interface methods.                         ///
    ///                                                          ///
    ///   Do not modify these methods; instead, modify the       ///
    ///   methods that they call.                                ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    /**
     * This method is called when the JSP engine encounters the start tag, after
     * the attributes are processed. Scripting variables (if any) have their
     * values set here.
     *
     * @return EVAL_BODY_BUFFERED if the JSP engine should evaluate the tag
     * body, otherwise return SKIP_BODY. This method is automatically generated.
     * Do not modify this method. Instead, modify the methods that this method
     * calls.
     */
    @Override
    public int doStartTag() throws JspException {
        otherDoStartTagOperations();
        
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }

    /**
     * This method is called after the JSP engine finished processing the tag.
     *
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP
     * page, otherwise return SKIP_PAGE. This method is automatically generated.
     * Do not modify this method. Instead, modify the methods that this method
     * calls.
     */
    @Override
    public int doEndTag() throws JspException {
        otherDoEndTagOperations();
        
        if (shouldEvaluateRestOfPageAfterEndTag()) {
            return EVAL_PAGE;
        } else {
            return SKIP_PAGE;
        }
    }

    /**
     * This method is called after the JSP engine processes the body content of
     * the tag.
     *
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body
     * again, otherwise return SKIP_BODY. This method is automatically
     * generated. Do not modify this method. Instead, modify the methods that
     * this method calls.
     */
    @Override
    public int doAfterBody() throws JspException {
        try {
            // This code is generated for tags whose bodyContent is "JSP"
            BodyContent bodyCont = getBodyContent();
            JspWriter out = bodyCont.getEnclosingWriter();
            
            writeTagBodyContent(out, bodyCont);
        } catch (Exception ex) {
            handleBodyContentException(ex);
        }
        
        if (theBodyShouldBeEvaluatedAgain()) {
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    /**
     * Handles exception from processing the body content.
     */
    private void handleBodyContentException(Exception ex) throws JspException {
        // Since the doAfterBody method is guarded, place exception handing code here.
        throw new JspException("Error in listCaddie tag", ex);
    }

    /**
     * Fill in this method to determine if the rest of the JSP page should be
     * generated after this tag is finished. Called from doEndTag().
     */
    private boolean shouldEvaluateRestOfPageAfterEndTag() {
        return !erreur;
    }

    /**
     * Fill in this method to determine if the tag body should be evaluated
     * again after evaluating the body. Use this method to create an iterating
     * tag. Called from doAfterBody().
     */
    private boolean theBodyShouldBeEvaluatedAgain() {
        //j'ai pas envie de lire 2 fois le body
        return false;
    }

    private boolean theBodyShouldBeEvaluated() {
        //if(erreur == true) return false et inversément
        return !erreur;
    }
    
}

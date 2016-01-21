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
import java.util.ResourceBundle;
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
public class displayProduct extends BodyTagSupport {
    
    private boolean erreur;
    private Statement requete;
    
    //balise des attributs
    private String erreurCommande;
    private int idItem;
    
    /**
     * Creates new instance of tag handler
     */
    public displayProduct() {
        super();
        erreur = false;
    }
    
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
    
    private boolean theBodyShouldBeEvaluated() {
        //if(erreur == true) return false et inversément
        return !erreur;
    }

    /**
     * Method called from doEndTag() Fill in this method to perform other
     * operations from doEndTag().
     */
    private void otherDoEndTagOperations() {/*nothing*/}
    
    
     /**
     * Fill in this method to determine if the rest of the JSP page should be
     * generated after this tag is finished. Called from doEndTag().
     */
    private boolean shouldEvaluateRestOfPageAfterEndTag() {
        return !erreur;
    }
    
    private boolean theBodyShouldBeEvaluatedAgain() {
        //j'ai pas envie de lire 2 fois le body
        return false;
    }  
    
    //Cette methode va permettre d'afficher ce que je veux.
    private void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws IOException {
        
        //Recuperation de la requete à executer
        ResultSet rs = null;
        ResourceBundle bundle = (ResourceBundle)pageContext.getSession().getAttribute("langue");

        try {
            rs = requete.executeQuery(bodyContent.getString());
        } catch (SQLException ex) {
            System.err.println("Erreur d'execution de la requete");
            ex.printStackTrace();
            erreur = true;
            return;
        }
        
        int idProduitErreur = 0;
        
        //Si il y a eu une erreur à l'expédition d'une commande
        if(erreurCommande != null)
            idProduitErreur = idItem;
            
        try {
            //Maintenant on va boucler sur notre result set pour afficher les produits
            while(rs.next())
            {
                out.println("<div>");
                out.println("<h2>" + bundle.getString("nom"+rs.getString("ID_PRODUIT"))+ "</h2>");
                out.println("<p>" + bundle.getString("desc"+rs.getString("ID_PRODUIT")) + "</p>");
                out.println("<p>"+bundle.getString("Prix")+" : "+ rs.getFloat("PRIX") + "</p>");
                
                int qttStock = rs.getInt("QUANTITE") - rs.getInt("RESERVE");
                
                out.println("<p>"+bundle.getString("Stock")+" : "+ qttStock +"</p>");
                
                //formulaire pour commander
                out.println("<form action=\"Controler\" method=\"POST\">");
                out.println("<p>"+bundle.getString("Commander")+" : </p>");
                out.println("<input type=\"number\" name = \"quantite\" value = \"1\" min=\"1\" max = \"" + qttStock + "\" />");
                
                //champ cacher pour la servlet de controle
                //id du produit
                out.println("<input type=\"hidden\" name=\"idProduit\" value=\""+ rs.getInt("ID_PRODUIT") +"\"/>");
                
                //action
                out.println("<input type=\"hidden\" name=\"action\" value=\"commande\"/>");
                
                out.println("<input type=\"submit\" value=\""+bundle.getString("Commander")+"\">");
                out.println("</form>");
                //Si il y a eu une erreur de command eon affiche le message
                if(idProduitErreur == rs.getInt("ID_PRODUIT"))
                    out.println("<p style=\"color: red\">"+ erreurCommande +"</p>");
                
                out.println("</div>");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur de parcours du resultset");
            ex.printStackTrace();
            erreur = true;
            return;
        }
        
        //On efface le body pour pas le réévaluer plus tard.
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
        throw new JspException("Error in displayProduct tag", ex);
    } 
    
    
    public void setErreurCommmande(String erreurCommande) {
        this.erreurCommande = erreurCommande;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
}

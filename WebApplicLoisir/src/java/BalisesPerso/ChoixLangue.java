/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BalisesPerso;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import newBean.BeanBDAccess;
import newBean.connexionException;

/**
 *
 * @author Jerome
 */
public class ChoixLangue extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        try {

            BeanBDAccess bd = new BeanBDAccess();
            bd.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            
            ResultSet rs = bd.selection("*", "LANGUES", null);
            
            out.println("<form action=\"Controler\" method=\"POST\">");
            out.println("<select name=\"langue\">");
            while(rs.next())
            {
                String langue = rs.getString(1);
                out.println("<option value=\""+langue+"\">" + langue + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"hidden\" name=\"action\" value=\"choixLangue\"/>");
            out.println("<input type=\"submit\" value=\"Continuer\"/>");
            out.println("</from>");
            
        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            Logger.getLogger(ChoixLangue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

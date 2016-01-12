/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BalisesPerso;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Jerome
 */
public class BaliseDate extends SimpleTagSupport {
    private String langue;
    private String date;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
            
            Date curDate = new Date();
            
            switch(langue)
            {
                case "UK" :
                    date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, Locale.UK).format(curDate);
                    break;
                default:
                    date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, Locale.FRANCE).format(curDate);
                    break;
            }
            out.println(date);
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in BaliseDate tag", ex);
        }
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BalisesPerso;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.jasper.el.JspELException;

/**
 *
 * @author Jerome
 */
public class produitsTagSupport extends BodyTagSupport{
    
    public produitsTagSupport()
    {
        super();
    }
    
    @Override
    public int doStartTag() throws JspException
    {
        System.err.println("do start tag");
        return EVAL_BODY_BUFFERED;
    }
    
    
    @Override
    public int doAfterBody() throws JspException
    {
        System.err.println("do after body");
        bodyContent.clearBody();
        return SKIP_BODY;
    }
    
    @Override
    public int doEndTag() throws JspException
    {
        System.err.println("do end tag");
        return EVAL_PAGE;
    }
}

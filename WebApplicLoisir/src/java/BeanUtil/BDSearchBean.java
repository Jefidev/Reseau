/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanUtil;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Jerome
 */
public class BDSearchBean implements Serializable {
    
    private String request;
    private ResultSet resultat;
    
    public BDSearchBean() {
        
    }
    
    public void setRequest(String r)
    {
        request = r;
    }
    
    public String getRequest()
    {
        return request;
    }
    
    public ResultSet getResultat()
    {
        return resultat;
    }
}

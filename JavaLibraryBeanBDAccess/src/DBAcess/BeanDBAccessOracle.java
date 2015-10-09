/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Utilisateur
 */
public class BeanDBAccessOracle extends BeanDBAccess implements Serializable {
    
    private String sampleProperty;
    
    public BeanDBAccessOracle() {
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        //String oldValue = sampleProperty;
        sampleProperty = value;
    }
}

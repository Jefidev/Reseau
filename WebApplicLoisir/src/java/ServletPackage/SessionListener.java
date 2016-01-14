/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletPackage;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Jerome
 */
public class SessionListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /*nothing*/
    }
    
    /**
     * @param se 
     * Ici on va supprimer les items reservés par le client qui a timeOut
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.err.println("destroy");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.sql.*;

/**
 *
 * @author Utilisateur
 */
public class WritingThreadDBAccess extends Thread {
    
    private String type;
    private Connection con;
    
    public WritingThreadDBAccess(String t, Connection c/*, String s, String f, String w*/) {
        type = t;
        con = c;
        /*select = s;
        from = f;
        where = w;*/
    }
    
    public void run()
    {
        //commit ! 
        
        switch(type)
        {
            case "INSERT" :
                break;
                
            case "UPDATE" :
                
                break;
                
            default :
                break;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilisateur
 */
public class WritingThreadDBAccess extends Thread {
    
    private Connection con;
    private String from;
    private HashMap donnees;
    private String where;
    
    public WritingThreadDBAccess(Connection c, String f, HashMap d) {
        con = c;
        from = f;
        donnees = d;
        where = null;
    }
    
    public WritingThreadDBAccess(Connection c, String f, HashMap d, String w) {
        con = c;
        from = f;
        donnees = d;
        where = w;
    }
    
    public void run()
    {
        String url;
        
        if (where == null)  // INSERT
        {
            String champs = "(";
            String valeurs = "(";
                
            Set cles = donnees.keySet();
            Iterator it = cles.iterator();
                
            while(it.hasNext())
            {
                Object cle = it.next();
                    
                champs = champs + cle.toString();
                valeurs = valeurs + "'" + donnees.get(cle).toString() + "'";
                    
                if (it.hasNext())
                {
                    champs = champs + ",";
                    valeurs = valeurs + ",";
                }
            }
                
            champs = champs + ")";
            valeurs = valeurs + ")";
                
            url = "insert into " + from + champs + " values " + valeurs;
        }
        else    // UPDATE
        {
            String valeurs = "";
                
            Set cles = donnees.keySet();
            Iterator it = cles.iterator();
                
            while(it.hasNext())
            {
                Object cle = it.next();
                valeurs = valeurs + cle.toString() + " = '"+ donnees.get(cle).toString() + "'";
                    
                if (it.hasNext())
                {
                    valeurs = valeurs + ", ";
                }
            }
                
            url = "update " + from + " set " + valeurs + " where  " + where;
        }
 
        try
        {
            PreparedStatement pStmt = con.prepareStatement(url);
            pStmt.executeQuery();
            con.commit();
        }
        catch (SQLException ex)
        {
            try
            {
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Erreur SQL : " + ex2.getMessage());
            }
            
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
    }
}

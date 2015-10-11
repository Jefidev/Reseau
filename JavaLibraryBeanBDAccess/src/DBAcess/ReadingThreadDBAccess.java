/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Utilisateur
 */
public class ReadingThreadDBAccess extends Thread {

    private Connection con;
    private String select;
    private String from;
    private String where;
    private InterfaceRequestListener client;
    
    public ReadingThreadDBAccess(Connection c, String s, String f, String w, InterfaceRequestListener cl) {
        con = c;
        select = s;
        from = f;
        where = w;
        client = cl;
    }
    
    public void run()
    {
        try
        {
            String url;
                    
            if(where != null)
             url = "select " + select + " from " + from + " where " + where;
            else
                url = url = "select " + select + " from " + from;
            
            System.out.println(url);
            PreparedStatement pStmt = con.prepareStatement(url);
            ResultSet rs = pStmt.executeQuery();
            
            
            
            client.resultRequest(rs);
            
        }
        catch (SQLException ex)
        {
            System.out.println("Erreur SQL : " + ex.getMessage());
        } 
    }
    
    
}

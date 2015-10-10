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
public class ReadingThreadDBAccess extends Thread {

    private Connection con;
    private String select;
    private String from;
    private String where;
    
    public ReadingThreadDBAccess(Connection c, String s, String f, String w) {
        con = c;
        select = s;
        from = f;
        where = w;
    }
    
    public void run()
    {
        try
        {
            String url = "select " + select + " from " + from + " where " + where;
            PreparedStatement pStmt = con.prepareStatement(url);
            ResultSet rs = pStmt.executeQuery();

            int cpt = 0;
            if (!rs.next())
            {
                System.out.println("Aucun tuple trouvé !");
                return;
            }

            do
            {
                System.out.println("ReadingThread");
                cpt++;
                String l = rs.getString(1);
                String p = rs.getString(2);
                System.out.println(cpt + " => " + l + " " + p);
            } while (rs.next());
        }
        catch (SQLException ex)
        {
            System.out.println("Erreur SQL : " + ex.getMessage());
        } 
    }
}

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
    
    public ReadingThreadDBAccess(Connection c) {
        con = c;
    }
    
    public void run()
    {
        try
        {
            PreparedStatement pStmt = con.prepareStatement("select * from UTILISATEURS");
            ResultSet rs = pStmt.executeQuery();

            int cpt = 0;
            if (!rs.next())
            {
                System.out.println("Aucun tuple trouvé !");
                return;
            }

            do
            {
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

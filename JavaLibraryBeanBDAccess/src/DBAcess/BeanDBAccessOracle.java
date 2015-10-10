/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.beans.*;
import java.io.Serializable;
import java.sql.*; // A TIRER QUAND THREAD
import java.lang.reflect.*; // Pour introspection pas nécessaire pour JDBC ?

/**
 *
 * @author Utilisateur
 */
public class BeanDBAccessOracle implements Serializable, BeanDBAccess { //extends BeanDBAccess implements Serializable {
    
    private String sampleProperty;
    
    public BeanDBAccessOracle() {
        main();
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        //String oldValue = sampleProperty;
        sampleProperty = value;
    }
    
    public void main()
    {
        try
        {
            System.out.println("Essai de connexion JDBC");
            Class leDriver = Class.forName("oracle.jdbc.driver.OracleDriver");

            // JUSTE POUR VOIR - A TIRER
            System.out.println("Driver JDBC-OBDC chargé -- Méthodes :");
            Method lesMethodesDuDriver[] = leDriver.getDeclaredMethods();
            for (int i=0; i< lesMethodesDuDriver.length; i++)
            System.out.println("méthode[" + i + "] = " + lesMethodesDuDriver[i]);

            Connection con = DriverManager.getConnection("jdbc:oracle:thin@localhost1521:TRAFIC");
            System.out.println("Connexion à la BDD trafic réalisée");
            PreparedStatement pStmt = con.prepareStatement("select * from ?");
            pStmt.setString(1, "UTILISATEURS");
            ResultSet rs = pStmt.executeQuery();
            System.out.println("Instruction SELECT sur utilisateurs envoyée à la BDD trafic");

            int cpt = 0;
            if (!rs.next())
            {
                System.out.println("Aucun tuple trouvé !");
                return;
            }

            do
            {
                if (cpt == 0) System.out.println("Parcours du curseur"); cpt++;
                String l = rs.getString("LOGIN");
                String p = rs.getString("PASSWORD");
                System.out.println(cpt + " => " + l + " " + p);
            } while (rs.next());
        }
        catch (SQLException ex)
        {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver adéquat non trouvable : " + ex.getMessage());
        }
    }
}

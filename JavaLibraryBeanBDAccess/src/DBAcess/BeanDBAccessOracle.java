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
    
    public void main() throws Exception
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
        while (rs.next())
        {
        if (cpt==0) System.out.println("Parcours du curseur"); cpt++;
        String cs = rs.getString("codeSto");
        System.out.println(" Récupération de codeSto");
        int x = rs.getInt("x"); int y = rs.getInt("y");
        System.out.println(" Récupération de x et y");
        double q = rs.getDouble("quantite");
        System.out.println(" Récupération de quantite");
        System.out.println(cpt + ". " + cs + " : " + x + "/" + y + " -> " +q);
        }
        instruc.executeUpdate("update produitsFinis " +
        "set prixRev =prixRev+10" );
        System.out.println("Instruction UPDATE sur produitsFinis envoyée à la BDD marie");
        rs = instruc.executeQuery("select * from produitsFinis");
        System.out.println("Instruction SELECT sur produitsFinis envoyée à la BDD marie");
        
        while (rs.next())
        {
        String cpf = rs.getString(1);
        double pr = rs.getDouble("prixRev");
        System.out.println(cpf + " : " + pr);
        }
    }
}

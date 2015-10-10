/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.beans.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilisateur
 */
public class BeanDBAccessOracle implements Serializable, InterfaceBeansDBAccess {
    
    private String ip;
    private int port;
    private String bd;
    private String user;
    private String pwd;
    private Connection con;
    
    public BeanDBAccessOracle() {
    }
    
    
    /* GETTERS ET SETTERS */
    
    @Override
    public String getIp() {
        return ip;
    }
    @Override
    public void setIp(String value) {
        ip = value;
    }
    
    @Override
    public int getPort() {
        return port;
    }
    @Override
    public void setPort(int value) {
        port = value;
    }
    
    @Override
    public String getBd() {
        return bd;
    }
    @Override
    public void setBd(String value) {
        bd = value;
    }
    
    @Override
    public String getUser() {
        return user;
    }
    @Override
    public void setUser(String value) {
        user = value;
    }
    
    @Override
    public String getPassword() {
        return pwd;
    }
    @Override
    public void setPassword(String value) {
        pwd = value;
    }
    
    
    /* BASE DE DONNEES */
    
    @Override
    public void connexion()
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:" + getUser() + "/" + getPassword() + "@" + getIp() + ":" + getPort() + ":" + getBd();
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
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
    
    @Override
    public void tablesDisponibles(ArrayList<String> l)
    {
        try
        {
            DatabaseMetaData m = con.getMetaData();
            ResultSet tables = m.getTables(con.getCatalog(), "TRAFIC", null, null);

            while (tables.next())
            {
                String t = tables.getString(3);
                l.add(t);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Erreur SQL : " + ex.getMessage());
        } 
    }
    
    @Override
    public void selection(String s, String f, String w)
    {
        ReadingThreadDBAccess rt = new ReadingThreadDBAccess(con, s, f, w);
        rt.start();
    }
    
    public void ecriture()
    {
       //commit ! 
    }
    
    public void miseAJour ()
    {
        //commit !
    }
    
    @Override
    public void finConnexion()
    {
        try
        {
            con.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(BeanDBAccessOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

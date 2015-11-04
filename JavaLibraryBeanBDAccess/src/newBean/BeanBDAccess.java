/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBean;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Jerome
 */
public class BeanBDAccess implements Serializable{
    
    private Connection conBD = null;
    private String curUser;
    
    public BeanBDAccess(){}
    
    public void connexionMySQl(String ip, int port, String user, String pwd, String bd) throws ClassNotFoundException, SQLException, connexionException
    {
        if(conBD != null)
            throw new connexionException("Le bean est déjà connecté à une BDD", 1);
            
        curUser = user;
        Class.forName("com.mysql.jdbc.driver");
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + bd;
        conBD = DriverManager.getConnection(url, user, pwd);
        conBD.setAutoCommit(false);
    }
    
    public void connexionOracle(String ip, int port, String user, String pwd, String bd) throws ClassNotFoundException, SQLException, connexionException
    {
        if(conBD != null)
            throw new connexionException("Le bean est déjà connecté à une BDD", 1);
        
        curUser = user;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:" + user + "/" + pwd + "@" + ip + ":" + port + ":" + bd;
        conBD = DriverManager.getConnection(url);
        conBD.setAutoCommit(false);
    }
    
    public void connexionCSV() throws ClassNotFoundException, SQLException, connexionException
    {
        if(conBD != null)
            throw new connexionException("Le bean est déjà connecté à une BDD", 1);
        
        Class.forName("jstels.jdbc.csv.CsvDriver2");
        conBD = DriverManager.getConnection("jdbc:jstels:csv:.?separator=;");
        conBD.setAutoCommit(false);
    }
    
    public ArrayList<String> tablesDisponibles() throws requeteException
    {
        if(curUser == null || conBD == null)
            throw new requeteException("Impossible de récupérer le nom des tables", 1);
        
        ArrayList<String> l = new ArrayList<String>();
        
        try
        {
            DatabaseMetaData md = conBD.getMetaData();
            ResultSet tables = md.getTables(conBD.getCatalog(), curUser, null, null);

            while (tables.next())
            {
                String t = tables.getString(3);
                l.add(t);
            }

            return l;
        }
        catch (SQLException ex)
        {
            System.out.println("Erreur SQL : " + ex.getMessage());
        }
        
        return null;
    }
    
    public ResultSet selection(String select, String from, String where) throws SQLException
    {
        String url;
        if(where != null)
            url = "select " + select + " from " + from + " where " + where;
        else
            url = "select " + select + " from " + from;
            
        PreparedStatement pStmt = conBD.prepareStatement(url);
        ResultSet rs = pStmt.executeQuery();
        
        return rs;
    }
    
    public synchronized void ecriture(String from, HashMap donnees) throws requeteException
    {
        String url;
        
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
            
        try
        {
            PreparedStatement pStmt = conBD.prepareStatement(url);
            pStmt.executeUpdate();
            conBD.commit();
        }
        catch (SQLException ex)
        {
            try
            {
                conBD.rollback();
            }
            catch (SQLException ex2)
            {
                throw new requeteException("Erreur de rollback", 3);
            }        
            throw new requeteException("Insert raté", 1);
        }
    }
    
    public synchronized void miseAJour(String from, HashMap donnees, String where) throws requeteException
    {
        String url;
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
        
        try
        {
            PreparedStatement pStmt = conBD.prepareStatement(url);
            pStmt.executeUpdate();
            conBD.commit();
        }
        catch (SQLException ex)
        {
            try
            {
                conBD.rollback();
            }
            catch (SQLException ex2)
            {
                throw new requeteException("Erreur de rollback", 3);
            }        
            throw new requeteException("Update raté", 1);
        }
    }
    
    public void finConnexion() throws SQLException
    {
        conBD.close();
        conBD = null;
    }
}

package DBAcess;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class BeanDBAccessMySql implements Serializable, InterfaceBeansDBAccess
{    
    private String ip;
    private int port;
    private String bd;
    private String user;
    private String pwd;
    private Connection con;
    private InterfaceRequestListener client;
    
    public BeanDBAccessMySql() {
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
    
    @Override
    public void setClient(InterfaceRequestListener c) {
        client = c;
    }
    
    
    /* BASE DE DONNEES */

    @Override
    public void connexion()
    {
        try
        {
            Class.forName("com.mysql.jdbc.driver");
            String url = "jdbc:mysql://" + getIp() + ":" + getPort() + "/" + getBd();
            con = DriverManager.getConnection(url, getUser(), getPassword());
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
    public ArrayList<String> tablesDisponibles()
    {
        ArrayList<String> l = new ArrayList<String>();
        
        try
        {
            DatabaseMetaData md = con.getMetaData();
            ResultSet tables = md.getTables(null, null, null, new String[] {"TABLE"});

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

    @Override
    public Thread selection(String s, String f, String w)
    {
        ReadingThreadDBAccess rt = new ReadingThreadDBAccess(con, s, f, w, client);
        rt.start();
        
        return rt;
    }

    @Override
    public Thread ecriture(String f, HashMap d)
    {
        WritingThreadDBAccess wt = new WritingThreadDBAccess(con, f, d, client);
        wt.start();
        
        return wt;
    }

    @Override
    public Thread miseAJour(String f, HashMap d, String w)
    {
        WritingThreadDBAccess wt = new WritingThreadDBAccess(con, f, d, w, client);
        wt.start();
        
        return wt;
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
            System.out.println("SQLException : " + ex);
        }
    }
}

package DBAcess;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;


public class BeanDBAccessCSV implements Serializable
{    
    private String ip;
    private int port;
    private String bd;
    private String user;
    private String pwd;
    private Connection con;
    private InterfaceRequestListener client;
    
    public BeanDBAccessCSV() {
    }

    
    public void connexion()
    {
        try
        {
            Class.forName("jstels.jdbc.csv.CsvDriver2");
            con = DriverManager.getConnection("jdbc:jstels:csv:.?separator=;");
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

    public Thread selection(String s, String f, String w)
    {
        ReadingThreadDBAccess rt = new ReadingThreadDBAccess(con, s, f, w, client);
        rt.start();
        
        return rt;
    }

    public Thread ecriture(String f, HashMap d)
    {
        WritingThreadDBAccess wt = new WritingThreadDBAccess(con, f, d, client);
        wt.start();
        
        return wt;
    }
    
    public Thread miseAJour(String f, HashMap d, String w)
    {
        WritingThreadDBAccess wt = new WritingThreadDBAccess(con, f, d, w, client);
        wt.start();
        
        return wt;
    }
    
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
    
    public void setClient(InterfaceRequestListener c) {
        client = c;
    }
}

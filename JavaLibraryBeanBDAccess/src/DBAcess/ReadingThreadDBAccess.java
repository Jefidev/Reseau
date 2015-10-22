package DBAcess;

import java.sql.*;


public class ReadingThreadDBAccess extends Thread
{
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
                url = "select " + select + " from " + from;
            
            PreparedStatement pStmt = con.prepareStatement(url);
            ResultSet rs = pStmt.executeQuery();

            client.resultRequest(rs);
        }
        catch (SQLException ex)
        {
            client.erreurRecue(ex.toString());
        } 
    } 
}

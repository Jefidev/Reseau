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
            String url = "select " + select + " from " + from + " where " + where;
            System.out.println(url);
            PreparedStatement pStmt = con.prepareStatement(url);
            ResultSet rs = pStmt.executeQuery();
            
            
            
            client.resultRequest(buildTableModel(rs));
            
        }
        catch (SQLException ex)
        {
            System.out.println("Erreur SQL : " + ex.getMessage());
        } 
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs){
        
        Vector<String> columnNames = new Vector<String>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        try
        {
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        return new DefaultTableModel(data, columnNames);
    }
    
}

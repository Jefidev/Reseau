/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jerome
 */
public interface InterfaceRequestListener {
    
    public void resultRequest(DefaultTableModel rs);
    
}

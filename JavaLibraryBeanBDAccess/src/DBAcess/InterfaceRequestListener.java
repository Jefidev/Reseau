package DBAcess;

import java.sql.ResultSet;


public interface InterfaceRequestListener
{    
    public void resultRequest(ResultSet rs);
    public void erreurRecue(String erreur);   
}

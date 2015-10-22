package DBAcess;

import java.util.ArrayList;
import java.util.HashMap;


public interface InterfaceBeansDBAccess
{    
    public String getIp();
    public void setIp(String value);
    public int getPort();
    public void setPort(int value);
    public String getBd();
    public void setBd(String value);
    public String getUser();
    public void setUser(String value);
    public String getPassword();
    public void setPassword(String value);

    public void setClient(InterfaceRequestListener c);
    
    public void connexion();
    public ArrayList<String> tablesDisponibles();
    public Thread selection(String s, String f, String w);
    public Thread ecriture(String f, HashMap d);
    public Thread miseAJour(String f, HashMap d, String w);
    public void finConnexion(); 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
interface InterfaceBeansDBAccess {
    
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
    
    public void connection();
    public void tablesDisponibles(ArrayList<String> l);
    public void selection(String s, String f, String w);
}

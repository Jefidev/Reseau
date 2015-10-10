/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

/**
 *
 * @author Utilisateur
 */
public interface BeanDBAccess {
    
    public String getIp();
    public void setIp(String value);
    public String getPort();
    public void setPort(String value);
    public String getBd();
    public void setBd(String value);
    public String getUser();
    public void setUser(String value);
    public String getPassword();
    public void setPassword(String value);  
}

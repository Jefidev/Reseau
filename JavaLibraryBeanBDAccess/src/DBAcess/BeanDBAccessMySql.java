/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcess;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Utilisateur
 */
public class BeanDBAccessMySql implements Serializable, BeanDBAccess { //extends BeanDBAccess implements Serializable {
    
    private String ip;
    private String port;
    private String bd;
    private String user;
    private String pwd;
    
    public BeanDBAccessMySql() {
    }
    
    
    /* GETTERS ET SETTERS */
    
    public String getIp() {
        return ip;
    }
    public void setIp(String value) {
        ip = value;
    }
    
    public String getPort() {
        return port;
    }
    public void setPort(String value) {
        port = value;
    }
    
    public String getBd() {
        return bd;
    }
    public void setBd(String value) {
        bd = value;
    }
    
    public String getUser() {
        return user;
    }
    public void setUser(String value) {
        user = value;
    }
    
    public String getPassword() {
        return pwd;
    }
    public void setPassword(String value) {
        pwd = value;
    }
}

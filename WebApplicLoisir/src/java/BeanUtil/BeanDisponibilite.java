/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanUtil;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import newBean.BeanBDAccess;
import newBean.connexionException;

/**
 *
 * @author Jerome
 */
public class BeanDisponibilite implements Serializable {
    
    //Variable utiles pour l'affichage
    private Date dateReservation;
    private String erreur;
    private int nbrReservation;
    
    public BeanDisponibilite() {
        erreur = null;
    }
    
    public void setDateReservation(String date)
    {
        if(date == null)
            return;
        
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        try
        {
            dateReservation = (Date) df.parse(date);
            setNbrReservation();
        } catch (ParseException ex) {
            setErreur("Format de date incorrecte");
        }
    }
    
    public String getDateReservation()
    {
        return dateReservation.toString();
    }
    
    public void setErreur(String err)
    {
        erreur = err;
    }
    
    public String getErreur()
    {
        return erreur;
    }
    
    public void setNbrReservation()
    {
        //Acces à la BD pour recuperer le nombre de reservation pour une jour donne
        BeanBDAccess accesBD = new BeanBDAccess();
 
        try { 
            
            //Recuperation de la date sous le bon format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.FRENCH);
            String date = sdf.format(dateReservation);
            System.out.println(date);
            
            //Connect + select des dates
            accesBD.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            //requete
            ResultSet rs = accesBD.selection(   "NBR_RESERVATIONS", 
                                                "RESERVATIONS_PARC", 
                                                "DATE_RESERVATION = '" + date + "'");
            
            if(!rs.next())
            {
                nbrReservation = 0;
                return;
            }
            
            nbrReservation = rs.getInt(1);
            
        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            erreur = "Erreur de connexion à la BD";
        }
    }
    
    public int getNbrReservation()
    {
        return nbrReservation;
    }
    
}

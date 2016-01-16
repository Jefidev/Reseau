/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        erreur = "";
    }
    
    public void setdateReservation(String date)
    {
        if(date == null || date.isEmpty())
            return;
        
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        try
        {
            dateReservation = df.parse(date);
            setNbrReservation();
        } catch (ParseException ex) {
            setErreur("Format de date incorrecte");
        }
    }
    
    public String getdateReservation()
    {
        if(dateReservation == null)
            return null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY", Locale.FRENCH);
        return sdf.format(dateReservation);
    }
    
    public void setErreur(String err)
    {
        if(err == null)
        {
            erreur = "";
            return;
        }
        
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
            SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY", Locale.FRENCH);
            String date = sdf.format(dateReservation);
            
            //Connect + select des dates
            accesBD.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            //requete
            ResultSet rs = accesBD.selection(   "*", 
                                                "RESERVATIONS_PARC", 
                                                "DATE_RESERVATION = '" + date + "'");
            
            if(!rs.next())
            {
                nbrReservation = 0;
                return;
            }
            
            nbrReservation = rs.getInt("NBR_RESERVATIONS");
            int nbrAttente = rs.getInt("ATTENTE_CONFIRMATION");
            //le nombre de reservation total
            nbrReservation = nbrReservation + nbrAttente;
            
        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            erreur = "Erreur de connexion à la BD";
        }
    }
    
    public int getNbrReservation()
    {
        return nbrReservation;
    }
    
}

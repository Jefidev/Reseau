/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import newBean.BeanBDAccess;
import newBean.connexionException;
import newBean.requeteException;

/**
 *
 * @author Jerome
 */
public class SessionListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /*nothing*/
    }
    
    /**
     * @param se 
     * Ici on va supprimer les items reservés par le client qui a timeOut
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.err.println("destroy");
        
        //Suppression des articles et des places réservées dans la BD
        
        HashMap contenuCaddie = (HashMap) se.getSession().getAttribute("caddie");
        
        if(contenuCaddie == null)
            return;
        
        BeanBDAccess bd = new BeanBDAccess();
        try {
            bd.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            
            //Si il faut supprimer une réservation de place dans le parc
            if(contenuCaddie.get("entreeParc") != null)
            {
                int nbrPlace = (int)contenuCaddie.get("entreeParc");
                String date = (String) contenuCaddie.get("dateParc");

                contenuCaddie.remove("entreeParc");
                contenuCaddie.remove("dateParc");

                //recuperation du nombre de reservation
                ResultSet rs = bd.selection("*", "RESERVATIONS_PARC", "DATE_RESERVATION = '" + date + "'");
                
                if(!rs.next())
                    return;
                
                int totalReserve = rs.getInt("ATTENTE_CONFIRMATION");
                int nouveauTotal = totalReserve - nbrPlace;
                
                HashMap champsMAJ = new HashMap();
                champsMAJ.put("ATTENTE_CONFIRMATION", nouveauTotal);
                
                bd.miseAJour("RESERVATIONS_PARC", champsMAJ, "DATE_RESERVATION = '" + date + "'");
                bd.commit();
            }

            //Suppression de tous les articles réservés dans le caddie
            Iterator entries = contenuCaddie.entrySet().iterator();
            
            while(entries.hasNext())
            {
               Entry entry = (Map.Entry) entries.next();
               
               int idArticle = (int)entry.getKey();
               int nbrReserve = (int)entry.getValue();

               //recuperation dans la BD du nombre total d'objets réservé
               ResultSet rs = bd.selection("*", "produits", "ID_PRODUIT = " + idArticle);
               
               if(!rs.next())
                    continue;
               
               int nbrTotalReserve = rs.getInt("RESERVE");
               int nouveauNbrReservation = nbrTotalReserve - nbrReserve;
               
               HashMap champsMAJ = new HashMap();
               champsMAJ.put("RESERVE", nouveauNbrReservation);
               
               bd.miseAJour("produits", champsMAJ, "ID_PRODUIT = " + idArticle);
               bd.commit();
            }

        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (requeteException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

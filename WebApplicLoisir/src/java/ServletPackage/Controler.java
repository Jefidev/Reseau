/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import newBean.BeanBDAccess;
import newBean.connexionException;
import newBean.requeteException;

/**
 *
 * @author Jerome
 * 
 * Cette servlet controler va servir à rediriger l'utilisateur sur la bonne page
 * selon sa requête
 */
public class Controler extends HttpServlet{
    
    private int idCommande;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String typeRequete = request.getParameter("action");
        
        switch(typeRequete)
        {
            case "login":
                    loginRequest(request, response);
                break;
            case "magasin":
                    magasinRequest(request, response);
                break;
            case "parc":
                    parcRequest(request, response);
                break;
            case "caddie":
                    caddieRequest(request, response);
                break;
            case "commande":
                    commandeRequest(request, response);
                break;
            case "retourAccueil":
                    retourAccueilRequest(request, response);
                break;
            case "achat":
                    validerAchatRequest(request, response);
                break;
            case "reserverParc":
                    reserverParcRequest(request, response);
                break;  
            case "suppressionArticle":
                    suppressionRequest(request, response);
                break;
            case "deconnexion":
                    logoutRequest(request, response);
                break;
        }
        
        //RequestDispatcher rd = getServletContext().getRequestDispatcher("/accueil.jsp");
        //rd.forward(request, response);
    }
    
    
    
    
    /*******************************
     * @param r : requête reçue par la servlet
     * @param out : Objet de reponse
     * Permet d'identifier le client en cours. Synchronized car accès BDD
     */
    private synchronized void loginRequest(HttpServletRequest r, HttpServletResponse out)
    {
        //Connexion à la base de donnée contenant les utilisateurs
        BeanBDAccess accesBD = new BeanBDAccess();
        try {
            
            //Flux pour répondre à l'applet
            PrintWriter fluxApplet = out.getWriter();
            
            accesBD.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            ResultSet rs = accesBD.selection("password", "CUSTOMERS", "login = '" + r.getParameter("login") + "'");
            
            if(!rs.next())
            {
                fluxApplet.println("Login invalide");
                return;
            }
            
            if(!rs.getString(1).equals(r.getParameter("password")))
            {
                fluxApplet.println("Mot de passe invalide");
                return;
            }
            
            fluxApplet.println("ok");
            
        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

    /****************************MAGASIN REQUEST*********
     * @param request
     * @param response 
     * Verifie si le client est log et redirige vers la page magasin
     */
    private void magasinRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession sess = request.getSession(true);
        
        if(!verifLogin(sess, response))
            return;
        
        //Parfait on peut lancer la servlet add hock
        
        RequestDispatcher rd = request.getRequestDispatcher("magasin.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    /****** Parc request
     * @param request
     * @param response
     * 
     * On verifie que le client est bien log et si c'est le cas on va rediriger vers la page parc
     */
    private void parcRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession sess = request.getSession(true);
        
        if(!verifLogin(sess, response))
            return;
        
        RequestDispatcher rd = request.getRequestDispatcher("parc.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    /****** Caddie request
     * @param request
     * @param response
     * 
     * On verifie que le client est bien log et si c'est le cas on va rediriger vers la page parc
     */
    private void caddieRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession sess = request.getSession(true);
        
        if(!verifLogin(sess, response))
            return;
        
        RequestDispatcher rd = request.getRequestDispatcher("caddie.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    /****** Achat request
     * @param request
     * @param response
     * 
     * On va enregistrer la commande dans la BD et vider le caddie.
     */
    private void validerAchatRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession sess = request.getSession(true);
        
        if(!verifLogin(sess, response))
            return;
        
        //recuperation du caddie
        HashMap contenuCaddie = (HashMap) sess.getAttribute("caddie");
        
        //Huston we've a problem
        if(contenuCaddie ==  null)
        {
            redirectErreur(request, response);
            return;
        }
        
        //Le cas du parc
        
        
        
        //Le cas des articles
        
        RequestDispatcher rd = request.getRequestDispatcher("payemment.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*COMMANDE REQUEST
    *Une commande à été demandée pour un produit (c'est toujours 1 produit à la foi)
    *Etape 1 : vérifier dans la BDD que le stock disponible est toujours suffisant
    *Etape 2 : vérifier sur le client qui commande existe bien dans la BD
    *Etape 3 : Réserver la quantité demandée par le client en mettant à jour la BD
    */
    //acces BD donc synchronized
    private synchronized void commandeRequest(HttpServletRequest request, HttpServletResponse response)
    {
        //Verification du log du client
        HttpSession session = request.getSession(true);
        
        if(!verifLogin(session, response))
            return;
        
        //Verification de la commande passee + qtt en stock   
        int qttSouhaitee = 0;
        int idProduit = 0;
        int qttReservee = 0;
        try
        {
            qttSouhaitee = Integer.parseInt(request.getParameter("quantite"));
            idProduit = Integer.parseInt(request.getParameter("idProduit"));
        }
        catch(NumberFormatException ex)
        {
            redirectErreur(request, response);
            return;
        }
        
        //Recuperation du produit à commander
        BeanBDAccess bd = new  BeanBDAccess();
        
        try {
            bd.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            ResultSet rs = bd.selection("*", "produits", "ID_PRODUIT = " + idProduit);
            
            if(!rs.next())
            {
                //Le produit n'existe pas y'a un problème
                redirectErreur(request, response);
                return;
            }
            
            int qttRestante = rs.getInt("QUANTITE") - rs.getInt("RESERVE");
            qttReservee = rs.getInt("RESERVE");
            
            //On va tester si le stock a pas changé
            boolean erreurQtt = false;
            if(qttRestante < qttSouhaitee)
            {
                request.setAttribute("erreurCommande", "La quantité d'article que vous avez commandée est supérieur à celle en stock");
                request.setAttribute("idProduit", idProduit);
                erreurQtt =  true;
            }
            
            if(qttSouhaitee < 0)
            {
                request.setAttribute("erreurCommande", "Vous ne pouvez pas commander une quantité nulle ou négative");
                request.setAttribute("idProduit", idProduit);
                erreurQtt =  true;
            }
            
            //Si y'a une erreur dans la quantité on renvoit à l'expéditeur
            if(erreurQtt)
            {
                RequestDispatcher rd = request.getRequestDispatcher("magasin.jsp");
                try {
                    rd.forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return;
            }
            
            //La quantite est ok. Il va falloir s'assurer que le user existe bien et si c'est le cas on réserve la qtt demandée
            rs = bd.selection("*", "CUSTOMERS", "LOGIN = '" + session.getAttribute("login") + "'");
            
            if(!rs.next())
            {
                System.err.println("Erreur session invalidate");
                //le mec n'existe pas c'est une imposture
                session.invalidate();
                //Redirection à la base de login
                response.sendRedirect("index.html");
                return;
            }
            
            //Tout est ok on va réserver la quantite voulue. Mise à jour de la BD
            HashMap mapMAJ = new HashMap();
            mapMAJ.put("RESERVE", String.valueOf(qttReservee + qttSouhaitee));
            
            bd.miseAJour("PRODUITS", mapMAJ, "ID_PRODUIT = " + idProduit);
            bd.commit();
            
            //On va ajouter la commande au caddie.
            
            //Aucun element dans le caddie
            if(session.getAttribute("caddie") ==  null)
            {
                HashMap caddie = new HashMap();
                caddie.put(idProduit, qttSouhaitee);
                session.setAttribute("caddie", caddie);
            }
            //Il y a déjà des éléments dans le caddie
            else
            {
                HashMap caddie = (HashMap) session.getAttribute("caddie");
                
                //Si on a pas encore commandé ça
                if(caddie.get(idProduit) ==  null)
                {
                    caddie.put(idProduit, qttSouhaitee);
                }
                //Si il y a deja des objets de ce type commande
                else
                {
                    int qttDejaCommandee = (int)caddie.get(idProduit);
                    caddie.replace(idProduit, qttDejaCommandee + qttSouhaitee);
                }
                
                session.setAttribute("caddie", caddie);
            }
            
            
            //On peut rediriger sur la page caddie
            RequestDispatcher rd = request.getRequestDispatcher("caddie.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
        } catch (SQLException | connexionException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
        } catch (IOException | requeteException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
        }
    }
    
    
    
    
    
    private synchronized void reserverParcRequest(HttpServletRequest request, HttpServletResponse response)
    {
        //Verification du log du client
        HttpSession session = request.getSession(true);
        
        if(!verifLogin(session, response))
            return;
        
        
        //Verification des places disponibles
        int nbrPlacesSouhaitees;
        int nbrPlacesDispo;
        int qttDejaReservee = 0;
        String dateSouhaitee;
        
        //Boolean pour savoir si une reservation existe déjà pour cette date ou pas
        boolean entryExist = false;
        
        //On test pour savoir si le champ contient une valeur correcte
        try
        {
            nbrPlacesSouhaitees = Integer.parseInt(request.getParameter("nbrPlace"));
            dateSouhaitee = request.getParameter("date");
        }
        catch(NumberFormatException ex)
        {
            redirectErreur(request, response);
            return;
        }
        
        //Acces à la base
        BeanBDAccess bd = new BeanBDAccess();

        try {
            //connexion
            bd.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            
            //recherche du nombre de réservations pour cette date
            ResultSet rs = bd.selection("*", "RESERVATIONS_PARC", "DATE_RESERVATION = '" + dateSouhaitee + "'");
            
            //Aucune réservation à ce jour
            if(!rs.next())
            {
                   nbrPlacesDispo = 20;
            }
            else
            {
                qttDejaReservee = rs.getInt("ATTENTE_CONFIRMATION");
                nbrPlacesDispo = 20 - (rs.getInt("NBR_RESERVATIONS") + qttDejaReservee);
                entryExist = true;
            }
            
            //verification du nombre de places
            if(nbrPlacesDispo < nbrPlacesSouhaitees)
            {
                //ajout d'infos à la requête avant la redirection 
                request.setAttribute("erreurCommande", "Il n'y a plus assez de places pour le jour souhaité");
                
                //redirection
                RequestDispatcher rd = request.getRequestDispatcher("parc.jsp");
                
                try {
                    rd.forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return;
            }
            
            //Le nbr de place est ok. Il va falloir s'assurer que le user existe bien
            rs = bd.selection("*", "CUSTOMERS", "LOGIN = '" + session.getAttribute("login") + "'");
            
            if(!rs.next())
            {
                System.err.println("Erreur session invalidate");
                //le mec n'existe pas c'est une imposture
                session.invalidate();
                //Redirection à la base de login
                response.sendRedirect("index.html");
                return;
            }

            //On va ajouter la commande au caddie + mettre à jour la BD
            
            //Aucun element dans le caddie
            if(session.getAttribute("caddie") ==  null)
            {
                HashMap caddie = new HashMap();
                caddie.put("entreeParc", nbrPlacesSouhaitees);
                caddie.put("dateParc", dateSouhaitee);
                session.setAttribute("caddie", caddie);
            }
            
            //Il y a déjà des éléments dans le caddie
            else
            {
                HashMap caddie = (HashMap) session.getAttribute("caddie");
                
                /*J'ai pas gerer le fait que plusieurs commandes de places
                pour des dates différentes puissent être faites donc il est
                indispensable que le truc fonctionne*/
                
                if(caddie.get("entreeParc") ==  null)
                {
                    caddie.put("entreeParc", nbrPlacesSouhaitees);
                    caddie.put("dateParc", dateSouhaitee);
                }
                //Si il y a deja des objets de ce type commande
                else
                {
                    //Cas que je ne gere pas
                    request.setAttribute("erreurCommande", "Vous devez valider votre commande avant d'en effectuer une autre");
                    request.setAttribute("dateReservation", dateSouhaitee);

                    //redirection
                    RequestDispatcher rd = request.getRequestDispatcher("parc.jsp");

                    try {
                        rd.forward(request, response);
                    } catch (ServletException | IOException ex) {
                        Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    return;
                }
                
                session.setAttribute("caddie", caddie);
            }
            
            
            //MAJ BD
            //MAJ
            if(entryExist)
            {
                String qttMAJ =  String.valueOf(qttDejaReservee + nbrPlacesSouhaitees);
                       
                HashMap mapMAJ = new HashMap();
                mapMAJ.put("ATTENTE_CONFIRMATION", qttMAJ);
                
                System.err.println("date : " + dateSouhaitee + "  qtt = " + qttMAJ);

                bd.miseAJour("RESERVATIONS_PARC", mapMAJ, "DATE_RESERVATION = '" + dateSouhaitee + "'");
                bd.commit();
            }
            
            //insertion
            else
            {
                HashMap mapMAJ = new HashMap();
                mapMAJ.put("ATTENTE_CONFIRMATION", String.valueOf(qttDejaReservee + nbrPlacesSouhaitees));
                mapMAJ.put("DATE_RESERVATION", dateSouhaitee);
                mapMAJ.put("NBR_RESERVATIONS", "0");
                
                bd.ecriture("RESERVATIONS_PARC", mapMAJ);
                bd.commit();
            }
            
            
            //On peut rediriger sur la page caddie
            RequestDispatcher rd = request.getRequestDispatcher("caddie.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (requeteException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    /*
    *Supprime un produit du caddie de l'utilisateur et met à jour la BD
    */
    private synchronized void suppressionRequest(HttpServletRequest request, HttpServletResponse response)
    {
        //Verification du log du client
        HttpSession session = request.getSession(true);
        
        if(!verifLogin(session, response))
            return;
        
        
        //Récupération des infos du produit à supprimer
        HashMap contenuCaddie = (HashMap) session.getAttribute("caddie");
        
        if(contenuCaddie ==  null)
            redirectErreur(request, response);
        
        BeanBDAccess bd = new BeanBDAccess();
        try {
            bd.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            
            //Si il faut supprimer une réservation de place dans le parc
            if(request.getParameter("idArticle").equals("entreeParc"))
            {
                int nbrPlace = (int)contenuCaddie.get("entreeParc");
                String date = (String) contenuCaddie.get("dateParc");

                contenuCaddie.remove("entreeParc");
                contenuCaddie.remove("dateParc");

                //recuperation du nombre de reservation
                ResultSet rs = bd.selection("*", "RESERVATIONS_PARC", "DATE_RESERVATION = '" + date + "'");
                
                if(!rs.next())
                    redirectErreur(request, response);
                
                int totalReserve = rs.getInt("ATTENTE_CONFIRMATION");
                int nouveauTotal = totalReserve - nbrPlace;
                
                HashMap champsMAJ = new HashMap();
                champsMAJ.put("ATTENTE_CONFIRMATION", nouveauTotal);
                
                bd.miseAJour("RESERVATIONS_PARC", champsMAJ, "DATE_RESERVATION = '" + date + "'");
                bd.commit();
            }

            //Suppression d'une réservation d'article
            else
            {
               int idArticle = Integer.parseInt(request.getParameter("idArticle"));
               int nbrReserve = (int)contenuCaddie.get(idArticle);
               
               //Suppression du caddie
               contenuCaddie.remove(idArticle);
               
               //recuperation dans la BD du nombre total d'objets réservé
               ResultSet rs = bd.selection("*", "produits", "ID_PRODUIT = " + idArticle);
               
               if(!rs.next())
                    redirectErreur(request, response);
               
               int nbrTotalReserve = rs.getInt("RESERVE");
               int nouveauNbrReservation = nbrTotalReserve - nbrReserve;
               
               HashMap champsMAJ = new HashMap();
                champsMAJ.put("RESERVE", nouveauNbrReservation);
               
               bd.miseAJour("produits", champsMAJ, "ID_PRODUIT = " + idArticle);
               bd.commit();
            }
            
            //Redirection vers le caddie
            
            //On peut rediriger sur la page caddie
            RequestDispatcher rd = request.getRequestDispatcher("caddie.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (requeteException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    private void logoutRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession sess = request.getSession();
        
        sess.invalidate();
        try {
            response.sendRedirect("index.html");
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //true si le client est log, redirection si ce n'est pas le cas
    private boolean verifLogin(HttpSession sess, HttpServletResponse r)
    {
        try {
            if(sess.getAttribute("login") == null)
            {
                sess.invalidate();
                r.sendRedirect("index.html");
                return false;
            }
                
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    //edirection page d'accueil
    private void retourAccueilRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession sess = request.getSession(true);
        
        if(!verifLogin(sess, response))
            return;
        
        //Redirection à l'accueil
        RequestDispatcher rd = request.getRequestDispatcher("accueil.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void redirectErreur(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher rd = request.getRequestDispatcher("erreur.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //On va initialiser l'ID commande ici
    @Override
    public void init (ServletConfig config)
    {
        BeanBDAccess bd = new BeanBDAccess();
        
        try {
            bd.connexionOracle("localhost", 1521, "SHOP", "SHOP", "XE");
            
            ResultSet rs = bd.selection("max(ID_COMMANDE)", "COMMANDES", null);
            
            if(!rs.next())
                idCommande = 1;
            
            else
                idCommande =  rs.getInt(1) + 1;
            
        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

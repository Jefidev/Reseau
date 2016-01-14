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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
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
     * On verifie que le client est bien log et si c'est le cas on va rediriger vers la page parc
     */
    private void validerAchatRequest(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession sess = request.getSession(true);
        
        if(!verifLogin(sess, response))
            return;
        
        RequestDispatcher rd = request.getRequestDispatcher("payemment.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
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
                } catch (ServletException ex) {
                    Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
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
                } catch (ServletException ex) {
                    Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                        
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
        } catch (SQLException | connexionException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
        } catch (requeteException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
        }
    }
    
    
    //true si le client est log, redirection si ce n'est pas le cas
    private boolean verifLogin(HttpSession sess, HttpServletResponse r)
    {
        try {
            if(sess.getAttribute("login") == null)
            {
                r.sendRedirect("index.html");
                return false;
            }
                
        } catch (IOException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    //Retour sur la page d'accueil apres une erreur
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

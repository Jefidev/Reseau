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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import newBean.BeanBDAccess;
import newBean.connexionException;

/**
 *
 * @author Jerome
 * 
 * Cette servlet controler va servir à rediriger l'utilisateur sur la bonne page
 * selon sa requête
 */
public class Controler extends HttpServlet {

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
            case "commande":
                    commandeRequest(request, response);
                break;
            case "retourAccueil":
                    retourAccueilRequest(request, response);
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
    
    //acces BD donc synchronized
    private synchronized void commandeRequest(HttpServletRequest request, HttpServletResponse response)
    {
        //Verification du log du client
        HttpSession session = request.getSession(true);
        
        if(!verifLogin(session, response))
            return;
        
        //Verification de la commande passee + qtt en stock   
        int qttSouhaitee = 0;
        try
        {
            qttSouhaitee = Integer.parseInt(request.getParameter("quantite"));
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
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
            return;
        } catch (SQLException | connexionException ex) {
            ex.printStackTrace();
            redirectErreur(request, response);
            return;
        }
        
        System.err.println(session.getAttribute("login"));
        redirectErreur(request, response);
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

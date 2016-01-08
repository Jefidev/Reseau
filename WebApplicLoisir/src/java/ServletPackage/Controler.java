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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            
            //Creation de la session qui servira à "tracer" les actions du client
            r.getSession().setAttribute("login", r.getParameter("login"));
            
            //Et on dit à l'applet que c'est ok 
            fluxApplet.println("ok");
            
        } catch (ClassNotFoundException | SQLException | connexionException ex) {
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
            return;
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

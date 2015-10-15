/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationsServlets;

import DBAcess.BeanDBAccessOracle;
import DBAcess.InterfaceBeansDBAccess;
import DBAcess.InterfaceRequestListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jerome
 */
@WebServlet(name = "servletConnexion", urlPatterns = {"/formulaire"})
public class servletConnexion extends HttpServlet implements InterfaceRequestListener{
    
    
    private ResultSet reponseBean;
    private InterfaceBeansDBAccess beanBD;
    private Thread curThread = null;
    private String errRequest = null;
    
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
        
        HttpSession session = request.getSession(true);
        
        beanBD = new BeanDBAccessOracle();
        
        beanBD.setIp("localhost");
        beanBD.setPort(1521);
        beanBD.setUser("TRAFIC");
        beanBD.setPassword("TRAFIC");
        beanBD.setBd("XE");
        beanBD.setClient(this);
        
        beanBD.connexion();
        
        if(session.getAttribute("erreurReservation") == null || session.getAttribute("login") != null)
        {
            if(request.getParameter("nouveau") != null)
            {   
                HashMap<String, String> ajoutU = new HashMap<>();
                ajoutU.put("PASSWORD", request.getParameter("mdp"));
                ajoutU.put("LOGIN", request.getParameter("login"));
                
                curThread = beanBD.ecriture("UTILISATEURS", ajoutU);
                try {
                    curThread.join();
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }

                if(errRequest != null)
                {
                    session.setAttribute("erreur", "L'utilisateur existe deja");
                    response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/reservation");
                }
            }
            else
            {
                curThread = beanBD.selection("PASSWORD", "UTILISATEURS", "LOGIN = \'"+request.getParameter("login")+"\'");

                try {
                    curThread.join();
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }

                try {
                    if(!reponseBean.next())
                    {
                        session.setAttribute("erreur", "Login ou mot de passe invalide");
                        response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/reservation");
                    }
                    else
                    {
                        if(0 != request.getParameter("mdp").compareTo(reponseBean.getString("PASSWORD")))
                        {
                            session.setAttribute("erreur", "Login ou mot de passe invalide");
                            response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/reservation");
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(servletConnexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            
        session.setAttribute("login", request.getParameter("login"));
        reponseBean = null;
        
        response.setContentType("text/html;charset=UTF-8");
    
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulaire de réservation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Demande de réservation</h1>");
            out.println("<form method = \"POST\" action=\"reponse\"></br>");
            out.println("<label for=\"destination\">Destination : </label></br>");
            out.println("<select name=\"destination\" id=\"destination\">");
            
            curThread = beanBD.selection("VILLE", "DESTINATIONS", null);
            
            try {
                curThread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(servletConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                while(reponseBean.next())
                {
                    out.println("<option value=\"" + reponseBean.getString("VILLE") + "\">"+ reponseBean.getString("VILLE")+"</option>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(servletConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            beanBD.finConnexion();
            out.println("</select></br>");
            out.println("<label for=\"arrivee\">Date d'arrivee du container : </label>");
            out.println("<input type=\"date\" name=\"arrivee\" id=\"arrivee\"/></br>");
            out.println("<input type = \"submit\" value = \"Réserver\"/>");
            out.println("</form>");
            if(session.getAttribute("erreurReservation") != null)
            {
                out.println("<p>"+session.getAttribute("erreurReservation")+"</p>");
            }
            out.println("</body>");
            out.println("</html>");
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

    @Override
    public void resultRequest(ResultSet rs) {
            reponseBean = rs;
    }

    @Override
    public void erreurRecue(String erreur) {
        errRequest = erreur;
    }

}

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@WebServlet(name = "rechercheEmplacement", urlPatterns = {"/reponse"})
public class rechercheEmplacement extends HttpServlet implements InterfaceRequestListener{
    
    private ResultSet reponseBean;
    private InterfaceBeansDBAccess beanBD;
    private Thread curThread = null;
    private String errRequest = null;
    private static int numID = 1;
    private static final Object lock = new Object();

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
        
        if(session.getAttribute("login") == null)
            response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/Site_reservation");
        
        
        beanBD = new BeanDBAccessOracle();
        
        beanBD.setIp("localhost");
        beanBD.setPort(1521);
        beanBD.setUser("TRAFIC");
        beanBD.setPassword("TRAFIC");
        beanBD.setBd("XE");
        beanBD.setClient(this);
        
        beanBD.connexion();
        
        curThread =  beanBD.selection("X, Y", "PARC", "ETAT = 0");
        
        try {
            curThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(rechercheEmplacement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            if(!reponseBean.next())
            {
                response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/Site_reservation/reponseReservation.jsp");
            }
            else
            {
                synchronized(lock)
                {
                   session.setAttribute("ID", reponseBean.getString("X")+reponseBean.getString("Y")+request.getParameter("arrivee")+numID); 
                   numID++;
                }
                
                session.setAttribute("X", reponseBean.getString("X"));
                session.setAttribute("Y", reponseBean.getString("Y"));
                
                HashMap<String, String> envois = new HashMap<>();
                envois.put("ID_RESERVATION", session.getAttribute("ID").toString());
                envois.put("X", reponseBean.getString("X"));
                envois.put("Y", reponseBean.getString("Y"));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                envois.put("DATE_RESERVATION", dateFormat.format(cal.getTime()));
                envois.put("DATE_ARRIVEE", request.getParameter("arrivee"));
                envois.put("DESTINATION", request.getParameter("destination"));
                              
                curThread =  beanBD.ecriture("RESERVATIONS", envois);
                try {
                curThread.join();
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
                
                if(errRequest != null)
                    response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ "/Site_reservation/reponseReservation.jsp");
                
                session.setAttribute("erreurReservation", "La réservation n'a pas pu aboutir.");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
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

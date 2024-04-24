/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.servlets;

import g2.commentTbl.commentDAO;
import g2.postTbl.postDAO;
import g2.voteTbl.VoteSubmitError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "InsertCommentServlet", urlPatterns = {"/InsertCommentServlet"})
public class InsertCommentServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false); // Get existing session, don't create a new one
        if (session == null || session.getAttribute("ACC") == null) { 
            // No session or user ID, redirect to login page
            request.setAttribute("REDIRECT", request.getParameter("txtViewPostId"));
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return; // Exit the method to avoid further processing
        }
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        int postId = Integer.parseInt(request.getParameter("txtViewPostId"));
        int userId = Integer.parseInt(request.getParameter("txtUserId"));
        String comment = request.getParameter("comment");
       
        String url = "MainController?btAction=View_Post&txtViewPostId="+postId;

        try {
               commentDAO dao = new commentDAO();
               dao.insertComment(userId, postId, comment);
            
        } catch (SQLException e){
            log("Error at VoteServlet _ SQL : " + e.toString());
        } catch (ClassNotFoundException e){
            log("Error at VoteServlet _ CNF : " + e.toString());
        } catch (Exception e) {
            log("Error at VoteServlet _ EX : " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request,response);
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

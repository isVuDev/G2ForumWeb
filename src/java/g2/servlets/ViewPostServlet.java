/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.servlets;

import g2.commentTbl.commentDAO;
import g2.commentTbl.commentDTO;
import g2.postTbl.postDAO;
import g2.postTbl.postDTO;
import g2.userTbl.userDAO;
import g2.userTbl.userDTO;
import g2.voteTbl.voteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "ViewPostServlet", urlPatterns = {"/ViewPostServlet"})
public class ViewPostServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "viewPost.jsp";
        
        int post_id = Integer.parseInt(request.getParameter("txtViewPostId"));
        
        try {
            //calc votes
         
           //find post
           postDAO dao = new postDAO();
           //calc votes
           voteDAO vDao = new voteDAO();
           int voteSum =vDao.calculateVotesByPostID(post_id);
           if (!dao.updateVoteSum(post_id, voteSum)) log("Updated vote log failed");
           
           postDTO dto = dao.getPostById(post_id);
           if (dto != null){        
               request.setAttribute("POST_INFO", dto);
               
               //find author
               userDAO uDao = new userDAO();
               userDTO uDto = uDao.getUserByID(dto.getUser_id());
               
               if (uDto!=null) {
                   request.setAttribute("AUTHOR", uDto);
                   commentDAO cdao = new commentDAO();
                   List<commentDTO> cdto = cdao.getCommentByPost(post_id);
                   
                   if (cdto != null){
                        request.setAttribute("POST_COMMENTS", cdto);
                        log("fetch sucess");
                   }
               }
           }
                     
        } catch (SQLException | ClassNotFoundException ex){
            log(ex + " ____ daoexception");
        } catch (Exception e) {
            log("exception ___" + e);
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
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

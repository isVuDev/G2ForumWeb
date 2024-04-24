/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.servlets;

import g2.postTbl.postDAO;
import g2.postTbl.postDTO;
import g2.userTbl.userDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author APC
 */
public class CreatePostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String HOME_PAGE = "home.jsp";
    private final String CREATE_POST_PAGE = "createPost.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = CREATE_POST_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                //post id, user_id, topic_id, title, content, isHidden, isDelete, image;
                //int post_id
                //int author_id = Integer.parseInt(request.getParameter("txtAuthor"));
                int author_id = 1;
                //int topic_id = Integer.parseInt(request.getParameter("txtTopic_id"));
                int topic_id = 3;
                String post_title = request.getParameter("txtPostTitle");
                String post_content = request.getParameter("txtPostContent");
                //image null;
                //LoginErrors errors = new LoginErrors();
                //constructor =-- post_id
                //postDTO insert_post = new postDTO(author_id, topic_id, post_title, post_content, false, false, 'null';
                //String image = ;
                userDTO acc = (userDTO) session.getAttribute("ACC");
                postDAO p_dao = new postDAO();
                postDTO insert_post = p_dao.insertPost(acc.getUser_id(), topic_id, post_title, post_content, null);
                if (insert_post != null) {
                    request.setAttribute("INSERT_POST", insert_post);
                    
                    if (acc.isIsMod()) {
                        url = "mod.jsp";
                    } else {
                        url = "user.jsp";
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(CreatePostServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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

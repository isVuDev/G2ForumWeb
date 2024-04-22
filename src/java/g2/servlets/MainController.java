/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nam
 */
public class MainController extends HttpServlet {

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
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String SESSION_SERVLET = "SessionServlet"; // check cookie truoc login
    private final String SIGNUP_SERVLET = "SignupServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SEARCH_USER_SERVLET = "SearchUserServlet";
    private final String SEARCH_TOPIC_SERVLET = "SearchTopicServlet";
    private final String SEARCH_POST_SERVLET = "SearchPostcServlet";
    private final String SEARCH_COMMENT_SERVLET = "SearchCommentServlet";
    private final String CREATE_POST_SERVLET = "CreatePostServlet";
    private final String CREATE_COMMENT_SERVLET = "CreateCommentServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = HOME_PAGE;
        try {
            String action = request.getParameter("btAction");
            if (action == null) {
                url = SESSION_SERVLET;
            } else if (action.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (action.equals("Signup")) {
                url = SIGNUP_SERVLET;
            } else if (action.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (action.equals("Search_User")) {
                url = SEARCH_USER_SERVLET;
            } else if (action.equals("Search_Topic")) {
                url = SEARCH_TOPIC_SERVLET;
            } else if (action.equals("Search_Post")) {
                url = SEARCH_POST_SERVLET;
            } else if (action.equals("Create_Post")) {
                url = CREATE_POST_SERVLET;
            } else if (action.equals("Search_Comment")) {
                //url = SEARCH_COMMENT_SERVLET;
            } else if (action.equals("Create_Comment")) {
                //url = CREATE_COMMENT_SERVLET;
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

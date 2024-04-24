/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.servlets;

import g2.userTbl.userDAO;
import g2.userTbl.userDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nam
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String USER_PAGE = "user.jsp";
    private String MOD_PAGE = "mod.jsp";
    private String LOGIN_PAGE = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try {
            String username = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            userDAO dao = new userDAO();
            userDTO acc = dao.checkLogin(username, password);
            if (acc == null) {
                request.setAttribute("ERROR", "Incorrect username or password");
            } else {
                if (!acc.isIsBanned()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("ACC", acc);
                    if (acc.isIsMod()) {//kiem tra neu la mod thi dang nhap vao trang mod
                        url = MOD_PAGE;
                    } else {
                        url = USER_PAGE;
                         //check for request para to redirect
                        String post_id = request.getParameter("txtRedirect");//this para is sent from the submitvoteservlet
                        if(post_id!=null){
                            url = "MainController?btAction=View_Post&txtViewPostId="+post_id;
                        }
                    }

                } else {
                    request.setAttribute("ERROR", "Your account has been banned.");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
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

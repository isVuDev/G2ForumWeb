/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.servlets;

import g2.postTbl.postDAO;
import g2.postTbl.postDTO;
import g2.userTbl.userDAO;
import g2.userTbl.userDTO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Nam
 */
public class ViewProfile extends HttpServlet {

    private String LOGIN_PAGE = "login.jsp";
    private String PROFILE_PAGE = "profile.jsp";
    private String OTHER_PAGE = "otherUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String oUsername = request.getParameter("txtUserName");
                userDTO otherAcc = null;
                postDAO pDao = new postDAO();
                if (oUsername != null && oUsername.length() > 0) {
                    userDAO uDao = new userDAO();
                    otherAcc = uDao.getUser(oUsername);
                    List<postDTO> otherPosts = pDao.getPostData(otherAcc.getUser_id());
                    request.setAttribute("POSTS", otherPosts);
                    request.setAttribute("OTHER_ACC", otherAcc);
                    url = OTHER_PAGE;
                } else {
                    userDTO acc = (userDTO) session.getAttribute("ACC");
                    List<postDTO> ownPosts = pDao.getPostData(acc.getUser_id());
                    request.setAttribute("POSTS", ownPosts);
                    url = PROFILE_PAGE;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    private byte[] extractImage(Part filePart) throws IOException {
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        try (InputStream iStream = filePart.getInputStream()) {
            while ((bytesRead = iStream.read(buffer)) != -1) {
                oStream.write(buffer, 0, bytesRead);
            }
        }
        return oStream.toByteArray();
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

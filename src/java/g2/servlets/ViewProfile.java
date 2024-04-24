/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.servlets;

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
public class UpdateUserServlet extends HttpServlet {

    private String UPDATE_PROFILE_PAGE = "updateProfile.jsp";
    private String LOGIN_PAGE = "login.jsp";
    private String LOGOUT_SERVLET = "LogoutServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                userDTO user = (userDTO) session.getAttribute("ACC");
                String oldPassword = user.getPassword();
                String userName = request.getParameter("txtUserName");
                String password = request.getParameter("txtPassword");
                String email = request.getParameter("txtEmail");
                String date_String = request.getParameter("txtBirthDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = dateFormat.parse(date_String);
                java.sql.Date birthdate = new java.sql.Date(utilDate.getTime());
                byte[] avatar = null;
                Part filePart = request.getPart("imgAvatar");
                if (filePart != null && filePart.getSize() > 0) {
                    avatar = extractImage(filePart);
                } else {
                    String base64Image = request.getParameter("oldAvatar");
                    if (base64Image != null) {
                        avatar = Base64.getDecoder().decode(base64Image);
                    }
                }
                userDAO uDao = new userDAO();
                boolean isUpdate = uDao.updateUser(userName, password, email, birthdate, avatar);
                if (!oldPassword.equals(password)) {
                    request.setAttribute("MSG", "Password has changed, please login again.");
                    url = LOGOUT_SERVLET;
                } else {
                    userDTO accUpdate = uDao.getUser(userName);
                    session.setAttribute("ACC", accUpdate);
                    url = UPDATE_PROFILE_PAGE;
                }
            }
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
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

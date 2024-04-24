<%-- 
    Document   : updateProfile
    Created on : Apr 23, 2024, 10:45:18 AM
    Author     : Nam
--%>
<%@page import="g2.userTbl.userDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="acc" value="${sessionScope.ACC}"/>
        <title>${acc.getUsername()}</title>
    </head>
    <body>
        <form action="MainController" method="post" enctype="multipart/form-data">
            <table border="1">
                <tbody>
                    <tr>
                        <th>User Name</th>
                        <td>
                            ${acc.getUsername()}
                            <input type="hidden" name="txtUserName" value="${acc.getUsername()}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td>
                            <input type="password" name="txtPassword" value="${acc.getPassword()}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>
                            <input type="text" name="txtEmail" value="${acc.getEmail()}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Birthdate</th>
                        <td>
                            <input type="date" name="txtBirthDate" value="${acc.getBirthDate()}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Avatar</th>
                        <td>
                            <%
                                userDTO a = (userDTO) session.getAttribute("ACC");
                            %>
                            <c:set var="base64Avatar" value="<%=(a.getAvatar() != null) ? Base64.getEncoder().encodeToString(a.getAvatar()) : a.getAvatar()%>" />
                            <img src="data:image/jpg;base64,${base64Avatar}" width="100" height="100"/><br/>
                            <input type="file" id="image" name="imgAvatar" accept="image/*"/>
                            <input type="hidden" name="oldAvatar" value="${base64Avatar}"/>
                            <div><a href="profile.jsp">Back to Profile</a></div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" name="btAction" value="Update"/>
        </form>

    </body>
</html>

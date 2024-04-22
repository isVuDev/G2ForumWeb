<%-- 
    Document   : userManage
    Created on : Apr 22, 2024, 10:36:35 AM
    Author     : Nam
--%>

<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management</title>
    </head>
    <body>
        <h1>User Management</h1>
        <form action="MainController" method="get">
            <input type="text" name="txtSearchUser" placeholder="Search user" value="${param.txtSearchUser}"/>
            <input type="submit" name="btAction" value="Search User"/>
        </form>
    <c:set var="accs" value="${requestScope.ACCS}"/>
    <c:if test="${not empty accs}">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th><th>User ID</th><th>User Name</th><th>Password</th><th>Email</th>
                    <th>Birthdate</th><th>isMod</th><th>isBanned</th><th>Avatar</th><th>Delete</th><th>Update</th>
                </tr>
            </thead> 
            <tbody>
            <c:forEach var="a" items="${accs}" varStatus="cnt">
                <form action="MainController" method="post">
                    <tr>
                        <td>${cnt.index+1}</td>
                        <td>${a.getUser_id()}</td>
                        <td>
                            ${a.getUsername()}
                            <input type="hidden" name="txtUserName" value="${a.getUsername()}"/>
                        </td>
                        <td><input type="text" name="txtPassword" value="${a.getPassword()}"/></td>
                        <td><input type="text" name="txtEmail" value="${a.getEmail()}"/></td>
                        <td>
                            ${a.getBirthDate()}
                            <input type="hidden" name="txtBirthDate" value="${a.getBirthDate()}"/>
                        </td>
                        <td><input type="checkbox" name="chkMod" ${a.isIsMod() ? "checked" : ""}/></td>
                        <td><input type="checkbox" name="chkBanned" ${a.isIsBanned() ? "checked" : ""}/></td>
                        <td>
                            <img src="data:image/jpg;base64,${a.getAvatar()}" width="100" height="100" /><br/>
                            <input type="hidden" name="imgAvatar" value="${a.getAvatar()}">
                        </td>
                        <td><a href="MainController?btAction=DeleteUser&pk=${a.getUsername()}&lastSearchUser=${requestScope.txtSearchUser}">Delete</a></td>
                    <input type="hidden" name="LastSearchUser" value="${requestScope.txtSearchUser}"/>
                    <td><input type="submit" name="btAction" value="Update"/></td>
                    </tr>
                </form>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
</body>
</html> 
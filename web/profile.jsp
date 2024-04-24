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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<link rel="stylesheet" href="myStyle.css">-->
        <link rel="stylesheet" href="myStyle.css">
        <script src="https://kit.fontawesome.com/aed3462b10.js" crossorigin="anonymous"></script>
        <title>Profile Page</title>
    </head>
    <header>
        <div id="header">
            <h1>G2 Forum Web</h1>
            <p></p>
        </div>
        <nav id="menu">
            <ul>
                <li><a href="home.jsp">Home</a></li>
                <li><a href=""></a></li>
                <li><a href=""></a></li>
                <li><a href="signup.jsp">Sign-up</a></li>
                <li><a href="login.jsp">Login</a>
                </li>
            </ul>
            <ul>
                <div id="createPost">
                    <li><a href="MainController?btAction=Create_Post">Create Post</a></li>
                </div>
                <div class="search-container">
                    <form action="MainController" method="GET">
                        <input type="text" placeholder="user"
                               name="txtSearchUser"/>
                        <button type="submit" name="btAction" value="Search_User"><i class="fa fa-search"></i></button>
                    </form>
                    <form action="MainController" method="GET">
                        <input type="text" placeholder="topic" 
                               name="txtSearchTopic"/>
                        <button type="submit" name="btAction" value="Search_Topic"><i class="fa fa-search"></i></button>
                    </form>
                </div>
            </ul>
        </nav>
    </header>
    <div id="body">
        <div id="centerBox">

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>User Management</title>
            </head>
            <body>
                <h1>Mod List</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th><th>User ID</th><th>User Name</th><th>Email</th>
                            <th>Birthdate</th>
                        </tr>
                    <tbody>
                        <c:set var="mods" value="${requestScope.MODS}"/>
                        <c:forEach var="m" items="${mods}" varStatus="cnt">
                            <tr>
                                <td>${cnt.index+1}</td>
                                <td>${m.getUser_id()}</td>
                                <td>${m.getUsername()}</td>
                                <td>${m.getEmail()}</td>
                                <td>${m.getBirthDate()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    </thead> 
                </table>
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
                                <th>No.</th><th>User ID</th><th>User Name</th><th>Email</th>
                                <th>Birthdate</th><th>isBanned</th><th>Delete</th><th>Modify</th>
                            </tr>
                        </thead> 
                        <tbody>
                            <c:forEach var="a" items="${accs}" varStatus="cnt">
                                <c:if test="${!a.isIsMod()}">
                                    <tr>
                                <form action="MainController" method="post">
                                    <td>${cnt.index+1}</td>
                                    <td>${a.getUser_id()}</td>
                                    <td>
                                        ${a.getUsername()}
                                        <input type="hidden" name="txtUserName" value="${a.getUsername()}"/>
                                    </td>
                                    <td>${a.getEmail()}</td>
                                    <td>${a.getBirthDate()}</td>
                                    <td><input type="checkbox" name="chkBanned" ${a.isIsBanned() ? "checked" : ""}/></td>
                                    <td><a href="MainController?btAction=Delete&pk=${a.getUsername()}&lastSearchUser=${param.txtSearchUser}">Delete</a></td>
                                    <input type="hidden" name="LastSearchUser" value="${param.txtSearchUser}"/>
                                    <td><input type="submit" name="btAction" value="Ban"/></td>
                                </form>
                                </tr> 
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
        </div>

    </div>
    <footer>
        <div id="footer">
            <h6>G2 Forum Web</h6>
            <h6><a href="home.jsp">Github</a></h6>
        </div>
    </footer>
</body>
</html> 

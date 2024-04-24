<%-- 
    Document   : userManage
    Created on : Apr 22, 2024, 10:36:35 AM
    Author     : Nam
--%>

<%@page import="g2.userTbl.userDTO"%>
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
        <c:set var="acc" value="${sessionScope.ACC}"/>
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
            <div>
                <div>
                    <%
                        userDTO a = (userDTO) session.getAttribute("ACC");
                    %>
                    <c:set var="base64Avatar" value="<%=(a.getAvatar() != null) ? Base64.getEncoder().encodeToString(a.getAvatar()) : a.getAvatar()%>" />
                    <img src="data:image/jpg;base64,${base64Avatar}" width="200" height="200"/><br/>
                    ${acc.getUsername()}
                </div>
                <div>
                    Post:
                    <c:set var="posts" value="${requestScope.POSTS}"/>
                    ${posts.size()}
                </div>
                <div><a href="updateProfile.jsp">Update Profile</a></div>
            </div>
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

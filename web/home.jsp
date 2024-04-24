<%-- 
    Document   : index
    Created on : Apr 11, 2024, 2:33:42 PM
    Author     : APC
--%>

<%@page import="g2.postTbl.postDAO"%>
<%@page import="g2.postTbl.postDTO"%>
<%@page import="g2.topicTbl.topicDAO"%>
<%@page import="g2.topicTbl.topicDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<link rel="stylesheet" href="myStyle.css">-->
        <link rel="stylesheet" href="myStyle.css">
        <script src="https://kit.fontawesome.com/aed3462b10.js" crossorigin="anonymous"></script>
        <title>Home Page</title>
    </head>
    <body>
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
                        <li><a href="createPost.jsp">Create Post</a></li>
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
            <div id="postTable">
                <%
                    //<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
                    //<c:if test="${not empty listLec}">
                    //<c:forEach items="${requestScope.listLec}" var="lec">
                    //${lec.course.courseName}
                    if (request.getAttribute("POSTS") != null) {
                        List<postDTO> list_post = (List<postDTO>) request.getAttribute("POSTS");
                        if (list_post != null && list_post.size() > 0) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Topic ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (postDTO p : list_post) {
                        %>
                        <tr>
                            <td><%= p.getPost_id()%></td>
                            <td><%= p.getTitle()%></td>
                            <td><%= p.getContent()%></td>
                            <td><%= p.getTopic_id()%></td>
                        </tr>
                        <%
                            };
                        %>
                    </tbody>

                </table>
                <%
                    }
                } else {
                    postDAO p_dao = new postDAO();
                    List<postDTO> list_post = (List<postDTO>) p_dao.getPostData();
                    if (list_post != null && list_post.size() > 0) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Content</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (postDTO p : list_post) {
                        %>
                        <!--                        //CSS #postTable a -->
                        <tr>
                            <td><a href=<%="MainController?btAction=View_Post&txtViewPostId=" + p.getPost_id()%> ><%= p.getTitle()%></a></td>
                            <td><%= p.getContent()%></td>
                        </tr>
                        <%
                            };
                        %>
                    </tbody>
                </table>
                <%
                        }
                    }
                %>
            </div>
            <div id="topicTable">
                <%
                    //<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
                    //<c:if test="${not empty listLec}">
                    //<c:forEach items="${requestScope.listLec}" var="lec">
                    //${lec.course.courseName}
                    if (request.getParameter("txtSearchTopic") != null) {
                        List<topicDTO> list_topic = (List<topicDTO>) request.getAttribute("TOPICS");
                        if (list_topic != null && list_topic.size() > 0) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Content</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (topicDTO t : list_topic) {
                        %>
                        <tr>
                            <td><%= t.getTopic_id()%></td>
                            <td><a href=<%="MainController?btAction=Search_Post&txtSearchPostTopicId=" + t.getTopic_id()%> ><%= t.getTitle()%></a></td>
                            <td><%= t.getContent()%></td>
                        </tr>
                        <%
                            };
                        %>
                    </tbody>

                </table>
                <%
                    }
                } else {
                    topicDAO t_dao = new topicDAO();
                    List<topicDTO> list_topic = (List<topicDTO>) t_dao.getTopicData();
                    if (list_topic != null && list_topic.size() > 0) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Content</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (topicDTO t : list_topic) {
                        %>
                        <tr>
                            <td><%= t.getTitle()%></td>
                            <td><%= t.getContent()%></td>
                        </tr>
                        <%
                            };
                        %>
                    </tbody>
                </table>
                <%
                        }
                    }
                %>
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

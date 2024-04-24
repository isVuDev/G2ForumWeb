<%-- 
    Document   : viewPost
    Created on : Apr 23, 2024, 12:04:06 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<link rel="stylesheet" href="myStyle.css">-->
        <link rel="stylesheet" href="myStyle.css">
        <script src="https://kit.fontawesome.com/aed3462b10.js" crossorigin="anonymous"></script>
        <title>View Post</title>
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

                <h1>View Post</h1>

                <c:set var="post" value="${requestScope.POST_INFO}"/>
                <c:set var="comments" value="${requestScope.POST_COMMENTS}" /> <!-- Replace with actual comments -->
                <c:set var="author" value="${requestScope.AUTHOR}"/>
                <c:set var="voteError" value="${requestScope.VOTE_ERROR}"/>

                <%-- Display Post --%>
                    <div id="post">
                        <h2>${post.title}</h2>
                        <p>${post.content}</p>
                        <p>Author: ${author.username}</p>

                        <%-- Vote Buttons --%>
                        <form action="MainController" method="post">
                            <input type="hidden" name="txtViewPostId" value="${post.post_id}">
                            <input type="hidden" name="txtUserID" value="${sessionScope.ACC.user_id}">
                            <input type="submit" name="btAction" value="Upvote">
                            <p style="margin-left: 20px;margin-right: 20px">${post.voteSum}</p>
                            <input type="submit" name="btAction" value="Downvote">
                        </form>


                        <c:if test="${not empty voteError}">
                            <c:url value="MainController" var="removeVoteUrl">
                                <c:param name="btAction" value="Remove Vote" />
                                <c:param name="txtViewPostID" value="${post.post_id}" />
                                <c:param name="txtUserID" value="${sessionScope.ACC.user_id}" />
                            </c:url>

                            <a href="${removeVoteUrl}">${voteError.voteHasBeenMade}</a>
                        </c:if>



                </div>

                <%-- Comment Section --%>
                <div id="comments">
                    <h2>Comments</h2>
                    <c:forEach var="comment" items="${comments}">
                        <div class="comment">
                            <p>${comment.content}</p>
                            <p>Commented by: ${comment.user_id}</p>
                        </div>
                    </c:forEach>

                    <%-- Comment Form --%>
                    <h3>Add a Comment</h3>
                    <form action="CommentController" method="post">
                        <input type="hidden" name="postId" value="${postId}">
                        <textarea name="comment" rows="4" cols="50"></textarea><br>
                        <input type="submit" value="Submit Comment">
                    </form>
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


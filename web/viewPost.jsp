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
        <style>            
            /* Container styling for the post */
            #post {
                border: 2px solid #ccc; /* Border for delineation */
                padding: 15px; /* Padding for inner content */
                margin: 10px 0; /* Margin to separate from other content */
                background-color: #f9f9f9; /* Light background for visibility */
                border-radius: 8px; /* Rounded corners for aesthetics */
            }

            /* Styling for the post title */
            #post h2 {
                font-size: 1.5em; /* Larger font size for the title */
                color: #333; /* Dark gray color for readability */
                margin-bottom: 10px; /* Margin to separate from other elements */
            }

            /* Styling for the post content */
            #post p {
                font-size: 1em; /* Base font size for readability */
                color: #666; /* Medium gray color for a softer look */
                line-height: 1.5; /* Increased line height for better readability */
            }

            /* Styling for the author information */
            #post p:last-of-type {
                font-style: italic; /* Italicize author information */
                color: #444; /* Slightly darker gray for emphasis */
            }

            /* Styling for the vote buttons */
            #post form {
                display: flex; /* Flex layout for inline elements */
                align-items: center; /* Align vertically */
                margin-top: 10px; /* Margin for separation from content */
            }

            /* Styling for the vote buttons themselves */
            #post input[type="submit"] {
                padding: 8px 12px; /* Padding for button size */
                border: none; /* No border for cleaner look */
                background-color: #007bff; /* Blue color for action buttons */
                color: white; /* White text for contrast */
                border-radius: 4px; /* Slightly rounded corners */
                cursor: pointer; /* Pointer cursor on hover */
                transition: background-color 0.3s; /* Smooth transition for hover effect */
            }

            /* Hover effect for buttons */
            #post input[type="submit"]:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }

            /* Styling for the vote count */
            #post p[style] {
                font-size: 1.2em; /* Larger font size for visibility */
                color: #000; /* Black color for emphasis */
            }

            /* Styling for the 'Remove Vote' link */
            #post a {
                color: #dc3545; /* Red color for remove action */
                text-decoration: none; /* No underline */
                margin-top: 10px; /* Margin for separation */
            }

            /* Hover effect for the 'Remove Vote' link */
            #post a:hover {
                text-decoration: underline; /* Underline on hover */
            } 
            
            h6 {
            font-size: 16px;
            color: white; /* Blue color (you can choose any color you like) */
            text-decoration: none;
            cursor: pointer;
            }

            /* Add hover effect */
            h6:hover {
                text-decoration: underline;
            }
            
            /* Container styling for the comments section */
            #comments {
                border-top: 2px solid #ccc; /* Separator at the top */
                padding: 20px; /* Padding for inner content */
                background-color: #f0f0f0; /* Light background for differentiation */
            }

            /* Styling for the comments header */
            #comments h2 {
                font-size: 1.5em; /* Larger font for section title */
                color: #333; /* Dark gray color for readability */
                margin-bottom: 15px; /* Margin for separation from content */
            }

            /* Styling for each individual comment */
            .comment {
                border-bottom: 1px solid #ddd; /* Divider between comments */
                padding: 10px 0; /* Padding for space within each comment */
            }

            /* Styling for the comment content */
            .comment p:first-of-type {
                font-size: 1em; /* Base font size */
                color: #555; /* Medium gray for readability */
                line-height: 1.5; /* Better spacing between lines */
            }

            /* Styling for the comment author information */
            .comment p:last-of-type {
                font-size: 0.9em; /* Slightly smaller font for author info */
                color: #888; /* Light gray for differentiation */
                font-style: italic; /* Italicize for style */
            }

            /* Styling for the 'Add a Comment' section */
            #comments h3 {
                font-size: 1.2em; /* Slightly larger than base font */
                color: #333; /* Dark gray for readability */
                margin-top: 20px; /* Space above the section */
            }

            /* Styling for the comment form */
            #comments form {
                display: flex; /* Flex layout for alignment */
                flex-direction: column; /* Align elements vertically */
                margin-top: 10px; /* Margin for separation from other content */
            }

            /* Styling for the textarea (comment input) */
            #comments textarea {
                font-size: 1em; /* Base font size */
                border: 1px solid #ccc; /* Border for definition */
                border-radius: 4px; /* Rounded corners */
                padding: 10px; /* Padding for inner content */
                resize: vertical; /* Allow vertical resizing only */
            }

            /* Styling for the submit button */
            #comments input[type="submit"] {
                font-size: 1em; /* Base font size */
                padding: 10px 15px; /* Padding for button size */
                background-color: #28a745; /* Green color for positive action */
                color: white; /* White text for contrast */
                border: none; /* No border for a clean look */
                border-radius: 4px; /* Rounded corners */
                cursor: pointer; /* Pointer cursor on hover */
                margin-top: 10px; /* Space between textarea and button */
            }

            /* Hover effect for the submit button */
            #comments input[type="submit"]:hover {
                background-color: #218838; /* Darker green on hover */
            }
            
            #container {
                width:80%;
            }
            


            
            
            
        </style>
        
        
        
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

                <a href="home.jsp"><h6>< Back </h6></a>

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
                            <input type="hidden" name="txtUserId" value="${sessionScope.ACC.user_id}">
                            <input type="submit" name="btAction" value="Upvote">
                            <p style="margin-left: 20px;margin-right: 20px">${post.voteSum}</p>
                            <input type="submit" name="btAction" value="Downvote">
                        </form>


                        <c:if test="${not empty voteError}">
                            <c:url value="MainController" var="removeVoteUrl">
                                <c:param name="btAction" value="Remove Vote" />
                                <c:param name="txtViewPostId" value="${post.post_id}" />
                                <c:param name="txtUserId" value="${sessionScope.ACC.user_id}" />
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
                            <p>Commenter: USERID${comment.user_id}</p>
                        </div>
                    </c:forEach>

                    <%-- Comment Form --%>
                    <h3>Add a Comment</h3>
                    <form action="MainController" method="post">
                        <input type="hidden" name="txtViewPostId" value="${post.post_id}"/>
                        <input type="hidden" name="txtUserId" value="${sessionScope.ACC.user_id}"/>
                        <textarea name="comment" rows="4" cols="50"></textarea><br>
                        <input type="submit" name="btAction" value="Submit Comment">
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

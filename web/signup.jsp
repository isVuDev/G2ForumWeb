<%-- 
    Document   : signup
    Created on : Apr 22, 2024, 3:07:12 PM
    Author     : Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<link rel="stylesheet" href="myStyle.css">-->
        <link rel="stylesheet" href="myStyle.css">
        <script src="https://kit.fontawesome.com/aed3462b10.js" crossorigin="anonymous"></script>
        <title>Signup Page</title>
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
            <div id="centerBox"
            <h1>Signup Page</h1></br>
            <form action="MainController" method ="post">
                Email<input type="text" name="txtEmail"/></br>
                Username<input type="text" name="txtUserName"/></br>
                <p><font color='red'>${requestScope.ERRORS.userNameLenError}</font></p>       
                <p><font color='red'>${requestScope.ERRORS.accountExisted}</font></p>
                Password(5 chars)<input type="password" name="txtPassword"/><br>
                <p><font color='red' >${requestScope.ERRORS.passwordLenError}</font></p>
                </br>
                Confirm Password<input type="password" name="txtConfirm"/></br>
                <p><font color='red' >${requestScope.ERRORS.confirmNotMatch}</font></p>
                <input type="submit" name="btAction" value="Signup"/>
                <input type="reset"/>
            </form>
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

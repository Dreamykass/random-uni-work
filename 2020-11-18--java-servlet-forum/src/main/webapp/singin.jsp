<%--
  Created by IntelliJ IDEA.
  User: grune
  Date: 2020-11-18
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: black;
            color: whitesmoke;
        }

        * {
            box-sizing: border-box;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
            background-color: #262121;
        }

        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #221212;
            color: whitesmoke;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #221212;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #221212;
            margin-bottom: 25px;
        }

        /* Set a style for the submit button */
        .registerbtn {
            background-color: #201547;
            color: whitesmoke;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .registerbtn:hover {
            opacity: 1;
        }


        /* Set a grey background color and center the text of the "sign in" section */
        .signin {
            background-color: #221212;
            text-align: center;
            color: wheat;
        }
    </style>
    <title>register</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login_processing.jsp" method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" id="login" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" required>

        <hr>

        <button type="submit" class="registerbtn">Singin</button>
    </div>

    <div class="container signin">
        <p>Need an account? <a href="register.jsp">Register</a>.</p>
    </div>
</form>

</body>
</html>


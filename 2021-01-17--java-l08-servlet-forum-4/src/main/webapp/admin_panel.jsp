<%@ page import="datatype.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%--
  Created by IntelliJ IDEA.
  User: grune
  Date: 2020-11-18
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            box-sizing: border-box;
        }

        /* Add a gray background color with some padding */
        body {
            font-family: Arial;
            padding: 20px;
        <%--background: <%= session.getAttribute("browser") %>;--%>;
            background: navajowhite;
        }

        /* Header/Blog Title */
        .header {
            padding: 30px;
            font-size: 40px;
            text-align: center;
            background: white;
        }

        /* Create two unequal columns that floats next to each other */
        /* Left column */
        .leftcolumn {
            float: left;
            width: 75%;
        }

        /* Right column */
        .rightcolumn {
            float: left;
            width: 25%;
            padding-left: 20px;
        }

        /* Fake image */
        .fakeimg {
            background-color: #aaa;
            width: 100%;
            padding: 20px;
        }

        /* Add a card effect for articles */
        .card {
            background-color: white;
            padding: 20px;
            margin-top: 20px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Footer */
        .footer {
            padding: 20px;
            text-align: center;
            background: #ddd;
            margin-top: 20px;
        }

        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 800px) {
            .leftcolumn, .rightcolumn {
                width: 100%;
                padding: 0;
            }
        }
    </style>
</head>
<body>

<div class="header">
    <h2>Questions and Answers</h2>
    <img src="res/logo.png" alt="logo">
    <h6><a href="index.jsp">Main Page</a></h6>
</div>

<div class="row">

    <div class="leftcolumn">
        "test"

        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/javaforum"
                           user="root" password=""/>

        <sql:query dataSource="${db}" var="rs">
            select * from users;
        </sql:query>

        <form method="get" action="admin_panel.jsp">
            <input type="text" name="search" value="">
            <input type="submit" value="search users">
        </form>

        <table border="2" width="100%">
            <tr>
                <th>login</th>
                <th>type</th>
                <th>password</th>
                <th>email</th>
                <th>blocked</th>
                <th>passwordChange</th>
            </tr>
            <c:forEach var="user" items="${rs.rows}">
                <c:if test="${param.search ne null and user.login.startsWith(param.search) eq true}">
                    <tr>
                        <td><c:out value="${user.login}"/></td>
                        <td>
                            <c:out value="${user.type}"/>
                            <form method="post" action="user_type_changing.jsp">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                <input type="submit" value="switch">
                            </form>
                        </td>
                        <td>
                            <form method="post" action="user_password_changing.jsp">
                                <input type="text" name="password" value="<c:out value="${user.password}"/>">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                <input type="submit" value="change">
                            </form>
                        </td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.blocked}"/></td>
                        <td><c:out value="${user.passwordChange}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>


    <div class="rightcolumn">
        <div class="card">
            <%= help.Session.getGreeting(request) %>
        </div>
        <div class="card">
            <h2>Current and total users</h2>
            <p>
                total: <%= request.getServletContext().getAttribute("totalusers") %><br>
                current: <%= request.getServletContext().getAttribute("currentusers") %><br>
            </p>
        </div>
        <div class="card">
            <h2>About The Page</h2>
            <div class="fakeimg" style="height:100px;">Image</div>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                deserunt mollit anim id est laborum.</p>
        </div>
    </div>

</div>

<div class="footer">
    <%= help.Session.getFooter(request) %>
</div>

</body>
</html>

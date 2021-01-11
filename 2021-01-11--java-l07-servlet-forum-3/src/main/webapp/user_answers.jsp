<%@ page import="datatype.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="datatype.Answer" %><%--
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
            background: #f1f1f1;
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
</div>

<div class="row">

    <div class="leftcolumn">
        <h2>Questions that you have answered:</h2>
        <hr>
        <%
            List<Question> qq = database.QuestionDatabase.getAllQuestions();
            for (Question q : qq) {
                for (Answer a : q.answers) {
                    if (a.authorLogin.equals(help.Session.getLoggedInUsernameOrGuest(request))) {
                        StringBuilder str = new StringBuilder();
                        str.append("<a href=\"details.jsp?hashcode=").append(q.hashCode()).append("\">").append(q.questionBody).append("</a><br>");
                        out.print(str.toString());
                    }
                }
            }
        %>
    </div>


    <div class="rightcolumn">
        <div class="card">
            <%= help.Session.getGreeting(request) %>
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
        <div class="card">
            <h3>Most Popular Questions</h3>
            <div class="fakeimg">a</div>
            <br>
            <div class="fakeimg">b</div>
            <br>
            <div class="fakeimg">c</div>
        </div>
    </div>

</div>

<div class="footer">
    <%= help.Session.getFooter(request) %>
</div>

</body>
</html>

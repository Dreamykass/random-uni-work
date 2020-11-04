<%--
  Created by IntelliJ IDEA.
  User: grune
  Date: 2020-10-30
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
    <style>
        div {
            margin: 70px;
            border: 1px solid #4CAF50;
        }

        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        div {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
    </style>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/response.jsp" method="post">
        <label for="name">name</label>
        <input type="text" id="name" name="name"><br><br>

        <label for="pesel">pesel</label>
        <input type="text" id="pesel" name="pesel"><br><br>

        <label for="birth">birth (YYYY-MM-DD)</label>
        <input type="text" id="birth" name="birth"><br><br>

        <label for="sex">sex (m/f)</label>
        <input type="text" id="sex" name="sex"><br><br>

        <label for="occupation">occupation</label>
        <input type="text" id="occupation" name="occupation"><br><br>

        <label for="email">email</label>
        <input type="text" id="email" name="email"><br><br>

        <label for="height">height (cm)</label>
        <input type="text" id="height" name="height"><br><br>

        <label for="hobby">hobby</label>
        <input type="text" id="hobby" name="hobby"><br><br>


        <input type="submit" value="submit">
    </form>
</div>

</body>
</html>

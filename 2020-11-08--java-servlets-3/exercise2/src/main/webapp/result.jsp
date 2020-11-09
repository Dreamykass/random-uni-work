<%--
  Created by IntelliJ IDEA.
  User: grune
  Date: 2020-11-09
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
result:
<h1>
    <%= request.getParameter("result") %>
</h1>
<br>current dir:<br>
<%= System.getProperty("user.dir")%>
</body>
</html>

<%@ page import="FilesystemHelper.DirectoryLister" %><%--
  Created by IntelliJ IDEA.
  User: grune
  Date: 2020-11-09
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-color: darkslategrey;
            background-image: url("https://www.nasa.gov/sites/default/files/thumbnails/image/potw2035a.jpg");
            background-blend-mode: darken;
            color: greenyellow;
            text-align: center;
        }

        .div_main {
            margin: 15% 25%;
            border-color: darkslategrey;
            background-color: #1d131faa;
        }

        .input_select {
            width: 90%;
            background-color: darkslategrey;
            border: none;
            border-radius: 8px;
            color: greenyellow;
            padding: 16px 32px;
            margin: 24px;
            text-decoration: none;
            cursor: pointer;
        }

        input[type="file"] {
            display: none;
        }

        .input_file {
            width: 90%;
            background-color: darkslategrey;
            border: none;
            border-radius: 8px;
            color: greenyellow;
            padding: 16px 32px;
            margin: 24px;
            text-decoration: none;
            cursor: pointer;

        }

        .input_submit {
            width: 90%;
            background-color: darkslategrey;
            border: none;
            border-radius: 8px;
            color: greenyellow;
            padding: 16px 32px;
            margin: 24px;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="div_main">
    <h1>Upload!</h1>
    <br>
    <br>
    <div>
        <form action="up.do" method="post" enctype="multipart/form-data">
            <br><br>
            <label for="directory">Choose a directory</label>
            <select id="directory" name="directory" class="input_select">
                <%= FilesystemHelper.DirectoryLister.directoriesToHtmlSelectOptionTags() %>
            </select>
            <br><br><br><br>
            <label for="file" class="input_file">Select a file</label>
            <input type="file" id="file" name="file"><br><br>
            <br><br><br>
            <input type="submit" class="input_submit">
            <br>
        </form>
    </div>
</div>
<div class="div_main">
    <h1>Download!</h1>
    <br>
    <br>
    <form action="down.do" method="get">
        <br><br>
        <label for="directory_file">Choose a directory</label>
        <select id="directory_file" name="directory_file" class="input_select">
            <%--        <%= FilesystemHelper.DirectoryLister.directoriesToHtmlSelectOptionTags() %>--%>
            <%= FilesystemHelper.DirectoryLister.filesInDirectoriesToHtmlSelectOptionTags() %>
        </select>
        <br><br><br>
        <input type="submit" class="input_submit">
    </form>
</div>
</body>
</html>

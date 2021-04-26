<!DOCTYPE html>

<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Laravel</title>

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">

    <!-- Styles -->
    <style>

    </style>

    <style>
        body {
            font-family: 'Nunito', sans-serif;
        }
    </style>
</head>
<body style="background-color:dimgray;">
<h1>quiz exam app</h1>
<form action="/login" method="post">
@csrf <!-- {{ csrf_field() }} -->
    <h2>student/teacher login</h2>
    <label for="login">login</label>
    <input type="text" id="login" name="login" placeholder="login"><br>
    <label for="password">password</label>
    <input type="text" id="password" name="password" placeholder="password"><br>
    <input type="submit" class="fadeIn fourth" value="enter">
</form>
</body>
</html>

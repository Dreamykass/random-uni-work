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
<script> {{-- ================================================================================================== --}}
    function studentRename(id) {
        var person = prompt("Please enter new login:", "Harry Potter");
        if (person == null || person == "") {
            // txt = "User cancelled the prompt.";
        } else {
            // txt = "Hello " + person + "! How are you today?";
            window.location.href = "/student_rename?id=" + id + "&login=" + person;
        }
    }

    function studentPassword(id) {
        var person = prompt("Please enter new password:", "**********");
        if (person == null || person == "") {
        } else {
            window.location.href = "/student_change_password?id=" + id + "&password=" + person;
        }
    }

    function addToGroup(id) {
        var person = prompt("Group name:", "english");
        if (person == null || person == "") {
        } else {
            window.location.href = "/student_add_to_group?id=" + id + "&name=" + person;
        }
    }

    function answerToggle(id) {
        window.location.href = "/answer_toggle?id=" + id;
    }

    function newQuestion() {
        var person = prompt("Question body:", "here");
        if (person == null || person == "") {
        } else {
            window.location.href = "/question_new?body=" + person;
        }
    }

    function newAnswer(id) {
        var person = prompt("Answer body", "english");
        if (person == null || person == "") {
        } else {
            window.location.href = "/answer_new?id=" + id + "&body=" + person;
        }
    }

</script> {{-- ================================================================================================= --}}
<body> {{-- ================================================================== --}}
<h1>admin panel</h1>

<h2>Logout</h2>
<a href="/logout">logout</a>

<h2>students:</h2>
<?php
$users = DB::table('users')->get();
foreach ($users as $user) {
    if ($user->admin) {
        echo "<strong>[admin] " . $user->login . " </strong>";
        echo "(" . $user->password . ") ";
        echo "<br> - ";
        echo "<br>";
    } else {
        echo "<strong>" . $user->login . " </strong>";
        echo "(" . $user->password . ") ";
        echo '<br> - <a href="/student_remove?id=' . $user->id . '">[remove]</a> ';
        echo '<a href="#" onclick="return studentRename(' . $user->id . ')">[change name]</a> ';
        echo '<a href="#" onclick="return studentPassword(' . $user->id . ')">[change password]</a> ';
        if (isset($user->group_id)) {
            echo "<br> - group: " . DB::table('groups')->where('id', '=', $user->group_id)->get()->first()->name;
            echo '<br> - <a href="/student_remove_from_group?id=' . $user->id . '">[remove]</a> ';
        } else {
            echo "<br> - group: none ";
            echo '<a href="#" onclick="return addToGroup(' . $user->id . ')">[add]</a> ';
        }
//        echo "<br> - ";
        echo "<br>";
    }
}
?>

<h2>new_student:</h2>
<form action="/student_new" method="get">
@csrf <!-- {{ csrf_field() }} -->
    <label for="login">login</label>
    <input type="text" id="login" name="login" placeholder="login"><br>
    <label for="password">password</label>
    <input type="text" id="password" name="password" placeholder="password"><br>
    <input type="submit" value="add">
</form>

<h2>groups</h2>
<?php
$groups = DB::table('groups')->get();
foreach ($groups as $group) {
    echo "<strong>" . $group->name . " </strong> ";
    echo '<a href="/group_remove?id=' . $group->id . '">[remove]</a> (';
    $uu = DB::table('users')->where('group_id', '=', $group->id)->get();
    foreach ($uu as $u)
        echo $u->login . ", ";
    echo ")";
    echo "<br>";
}
?>

<h2>new group:</h2>
<form action="/group_new" method="get">
@csrf <!-- {{ csrf_field() }} -->
    <label for="name">login</label>
    <input type="text" id="name" name="name" placeholder="name"><br>
    <input type="submit" value="add">
</form>

<h2>questions and answers</h2>
<?php
$questions = DB::table('questions')->get();
foreach ($questions as $question) {
    echo "<strong>" . $question->body . " </strong> ";
    echo '<a href="/question_remove?id=' . $question->id . '">[remove]</a>';
    $answers = DB::table('answers')->where('question_id', '=', $question->id)->get();
    foreach ($answers as $answer) {
        echo '<br> - ' . $answer->body . " " . '<a href="/answer_remove?id=' . $answer->id . '">[remove]</a> ';
        echo '[correct: ' . $answer->correct . ' <a href="#" onclick="return answerToggle(' . $answer->id . ')">toggle</a>' . ']';
    }
    echo "";
    echo '<br> - ' . '<a href="#" onclick="return newAnswer(' . $question->id . ')"> [new answer]</a > <br>';
}
echo '<strong>' . '<a href="#" onclick="return newQuestion()"> [new question]</a > </strong>';
?>

<h2>exams</h2>
<?php
$exams = DB::table('exams')->get();
foreach ($exams as $exam) {
    echo "<strong>" . $exam->title . "</strong>";
    echo "<br>";
}
?>

</body> {{-- =================================================================================================== --}}
</html>

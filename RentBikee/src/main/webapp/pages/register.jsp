<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 08.06.2022
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body{
            font-family: Calibri, Helvetica, sans-serif;
            background-color: pink;
        }
        .container {
            padding: 50px;
            background-color: lightblue;
        }

        input[type=text], input[type=password], textarea {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }
        input[type=text]:focus, input[type=password]:focus {
            background-color: orange;
            outline: none;
        }
        div {
            padding: 10px 0;
        }
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }
        .registerbtn {
            background-color: #4CAF50;
            color: white;
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
    </style>
</head>
<body>

<form action="controller" method="post">
    <input type="hidden" name="command" value="registration">
    <div class="container">
        <center>  <h1> Student Registeration Form</h1> </center>
        <hr>
        <label> Fullname </label>
        <input type="text" name="fullname" placeholder= "Firstname" size="15" required />
        <label> Username: </label>
        <input type="text" name="username" placeholder="username" size="15" required />
    </div>
    <label> Phone :</label>
    <input type="text" name="phoneNum" placeholder="Country Code"  value="+998" maxlength="13" minlength="13"/>

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="pass" required>

    <button type="submit">Register</button>
</form>
<hr>
 <p> ${register_msg}</p>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<%--<body>--%>

<%--<br/><form action="controller">--%>
<%--    <input type="hidden" name="command" value="login">--%>
<%--    LOGIN:     <input type="text" name="phoneNum" value=""/>--%>
<%--    <br/>--%>
<%--    PASSWORD: <input type="password" name="pass" value=""/>--%>
<%--    <br/>--%>
<%--    <input type="submit" name="sub" value="Push"/>--%>
<%--    <br/>--%>

<%--</form>--%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Login Page </title>
    <style>
        Body {
            font-family: Calibri, Helvetica, sans-serif;
            background-color: pink;
        }
        button {
            background-color: #4CAF50;
            width: 100%;
            color: orange;
            padding: 15px;
            margin: 10px 0px;
            border: none;
            cursor: pointer;
        }
        form {
            border: 3px solid #f1f1f1;
        }
        input[type=text], input[type=password] {
            width: 100%;
            margin: 8px 0;
            padding: 12px 20px;
            display: inline-block;
            border: 2px solid green;
            box-sizing: border-box;
        }
        button:hover {
            opacity: 0.7;
        }
        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            margin: 10px 5px;
        }


        .container {
            padding: 25px;
            background-color: lightblue;
        }
    </style>
</head>
<body>
<center> <h1> Student Login Form </h1> </center>
<form action="controller">
    <input type="hidden" name="command" value="login">
    <div class="container">
        <label for = "name">Phone Number : </label>
        <input id = "name" type="text" placeholder="Enter userName" name="username" required>
        <label>Password : </label>
        <input type="password" placeholder="Enter Password" name="pass" required>

        <button type="submit">Login</button>
    </div>
</form>
<br/>
    <a href="pages/register.jsp"><button type="submit">REGISTRATION</button></a>

</body>
</html>
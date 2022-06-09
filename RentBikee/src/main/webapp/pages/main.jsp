<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 01.06.2022
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
HELLO (forward)= ${user}
<h1>
    HI (redirect/forward) = ${user_name}
</h1>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logOut"/>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 29.06.2022
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden"  name="command" value="FIND_ALL_USERS">
    <button type="submit"> <h1>User category</h1></button>
</form>
<a href="userCategory.jsp"></a> <br><br>
<a href=""><h1> Bike category</h1></a> <br><br>
<a href=""><h1> Orders </h1></a>
</body>
</html>

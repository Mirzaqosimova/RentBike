<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 01.07.2022
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${requestScope.success}</h1>
<h1>${requestScope.error}</h1>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden"  name="command" value="FIND_ALL_USERS">
    <button type="submit"> <h1>back</h1></button>
</form>

</body>
</html>

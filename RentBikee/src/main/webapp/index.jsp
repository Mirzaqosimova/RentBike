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
</head>
<body>
<%--<br/>--%>
<%--    <a href="pages/register.jsp"><button type="submit">REGISTRATION</button></a>--%>
<%--<br>--%>
<%--    <a href="pages/login.jsp"><button type="submit">LOGIN</button></a>--%>
<%--<a href="/addBike.jsp"><button type="button" >Back</button></a>--%>
<form action="${pageContext.request.contextPath}/controller">
<input type="hidden" name="command" value="FIND_BIKE_FIELDS">
    <button type="submit" >DELETE</button>

</form>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 02.07.2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="ADD_DETAIL">
    <label for="det">Add category</label>
    <input id = "det" type="text" name="detailName">
    <button type="submit">Add</button>
</form>
<br>
<h1>${requestScope.message}</h1>
<br>
<table border="1">
    <thead>
    <tr>
        <td>Detail Name</td>
        <td>EDIT Name</td>
        <td>DELETE Name</td>
    </tr>
    <thead>
    <tbody>
    <c:forEach items="${requestScope.all_detail}" var="detail">
        <tr>
            <td>${detail.name}</td>
            <td><form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="UPDATE_DETAIL">
                <input type="text"  name="detailName" placeholder="Edit detail name" />
                <input type="hidden" name="detailId" value="${detail.id}">
                <button type="submit" >EDIT</button>
            </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="DELETE_DETAIL">
                    <button type="submit" name="detailId" value="${detail.id}" class="btn1">DELETE</button>
                </form>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<a href="/pages/adminPage.jsp"><button type="button" >Back</button></a>

</body>

</html>

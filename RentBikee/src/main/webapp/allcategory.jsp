<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 08.07.2022
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="ADD_CATEGORY">
    <label for="cat">Add category</label>
    <input id = "cat" type="text" name="categoryName">
    <button type="submit">Add</button>
</form>
<br>
<h1>${requestScope.message}</h1>
<br>
<table border="1">
    <thead>
    <tr>
        <td>Category Name</td>
        <td>EDIT Name</td>
        <td>DELETE Name</td>
    </tr>
    <thead>
<tbody>
    <c:forEach items="${requestScope.all_category}" var="category">
        <tr>
            <td>${category.name}</td>
            <td><form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="UPDATE_CATEGORY">
                <input type="text"  name="categoryName" placeholder="Edit categoryName" />
                <input type="hidden" name="categoryId" value="${category.id}">
                <button type="submit" >EDIT</button>
            </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="DELETE_CATEGORY">
                    <button type="submit" name="categoryId" value="${category.id}" class="btn1">DELETE</button>
                </form>
            </td>
        </tr>

    </c:forEach>
</tbody>
</table>
<a href="/pages/adminPage.jsp"><button type="button" >Back</button></a>

</body>
</html>

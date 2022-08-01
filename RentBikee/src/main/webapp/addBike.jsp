<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 02.07.2022
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<%--<%@ page language="java" contentType="multipart/form-data; charset=UTF-8"--%>
<%--    pageEncoding="UTF-8"%>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>



<form action="${pageContext.request.contextPath}/controller" method="post" focus="file" enctype="multipart/form-data">
    <input type="hidden" name="command" value="ADD_BIKE">
  <label>Brand name</label>
  <input name="brandName" type="text"><br><br>
    <label>description</label>
    <input name="description" type="text"> <br><br>
    <label>size</label>
    <input type="number" name="size"> <br><br>
    <label>time</label>
    <input type="number" name="time"> <br><br>
    <label>price</label>
    <input type="number" name="price"><br><br>
    <label> addres desc</label>
    <input type="text" name="addresDesc"><br><br>
    <label>address city</label>
    <input type="text" name="addresCity"><br><br>
    <label> addres street</label>
    <input type="text" name="addresStreet"><br><br>
    <label>category id</label>
    <select name="categoryId"><br><br>
        <c:forEach items="${requestScope.all_category}" var="category">
            <option name="categoryId" value="${category.id}">
                    ${category.name}
            </option>
        </c:forEach>
    </select> <br><br>
<c:forEach items="${requestScope.all_detail}" var="detail">
    <label>${detail.name}</label>
    <input type="checkbox" name="detailIds"  value="${detail.id}"><br><br>
</c:forEach>
    <label for="cat">Photo</label>
    <input id = "cat" type="file" name="photo">
    <button type="submit">Add</button>
</form>
</body>
</html>

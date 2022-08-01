<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 29.06.2022
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value='/pages/css/userlistcs.css' />"/>

    <title>Title</title>
</head>
<body>

<h3 class="card-header text-center font-weight-bold text-uppercase py-4">
    Edit users
</h3> <a href="pages/adminPage.jsp">
    <button id="bttn" type="button">
        Back</button>
</a><br>
<div class="card-body">
    <div id="table" class="table-editable">

        <table class="table table-bordered table-responsive-md table-striped text-center">
            <thead>

            <tr>
                <th class="text-center">User name</th>
                <th class="text-center">full name</th>
                <th class="text-center">phone number</th>
                <th class="text-center">balance</th>
                <th class="text-center">Status</th>
                <th class="text-center">edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.all_user}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.fullname}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.balance}</td>
                    <c:if test="${user.block == true}">
                        <td>DEACTIVE</td>
                    </c:if>
                    <c:if test="${user.block == false}">
                        <td>ACTIVE</td>
                    </c:if>

                    <td>
                        <div class="container">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="MANAGE_USER">
                                <input type="hidden" name="userId" value="${user.id}">

                                <c:if test="${user.block == true}">
                                    <button type="submit" class="btn">ACTIVE</button>
                                </c:if>
                                <c:if test="${user.block == false}">
                                    <button type="submit" class="btn">DEACTIVE</button>
                                </c:if>
                            </form>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="DELETE_USER">
                                <button type="submit" name="userId" value="${user.id}" class="btn1">DELETE</button>
                            </form>
                        </div>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>

</body>
</html>

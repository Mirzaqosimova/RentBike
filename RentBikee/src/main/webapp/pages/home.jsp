<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 28.06.2022
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>Home</title>
</head>
<body>
HI (redirect/forward) = ${requestScope.user.password}
HI (redirect/forward) = ${requestScope.name}
<c:if test="${sessionScope.sess.id != null}">
 <h1>${sessionScope.sess.phoneNumber}</h1>
</c:if>

<c:if test="${sessionScope.sess.id == null}">
   <h1>error</h1>
</c:if>
</body>
</html>

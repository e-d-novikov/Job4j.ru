<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${requestScope.user.name}"></c:out> <c:out value="${requestScope.user.sername}"></c:out></title>
</head>
<body>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout">
</form><br>
<form action="UserServlet" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="login" value="${requestScope.user.login}">
    <input type="submit" value="Edit profile">
</form><br>

<h2><c:out value="${requestScope.user.name}"></c:out> <c:out value="${requestScope.user.sername}"></c:out></h2>
<h3><c:out value="${requestScope.user.email}"></c:out></h3>

<c:if test="${sessionScope.role eq 'admin'}">
    <form action="AdminServlet" method="get">
        <input type="submit" value="Users List">
    </form>
</c:if>
</body>
</html>

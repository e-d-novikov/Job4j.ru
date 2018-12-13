<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="${requestScope.servlet}" method="post">
    <input type="hidden" name="action" value="apply">
    <input type="hidden" name="id" value="${requestScope.user.id}">
    <input type="hidden" name="oldLogin" value="${requestScope.user.login}">
    <input type="hidden" name="oldPassword" value="${requestScope.user.password}">
    <c:if test="${sessionScope.role eq 'admin'}">
        Login: <input type="text" name="login" value="${requestScope.user.login}"><br>
        Password: <input type="text" name="password" value="${requestScope.user.password}"><br>
        Role: <select name="role">
        <c:if test="${requestScope.user.role eq 'admin'}">
            <option value="admin">Администратор</option>
            <option value="user">Пользователь</option>
        </c:if>
        <c:if test="${requestScope.user.role eq 'user'}">
            <option value="user">Пользователь</option>
            <option value="admin">Администратор</option>
        </c:if>
    </select><br>
    </c:if>
    <c:if test="${sessionScope.role eq 'user'}">
        <input type="hidden" name="login" value="${requestScope.user.login}">
        <input type="hidden" name="password" value="${requestScope.user.password}">
        <input type="hidden" name="role" value="${requestScope.user.role}">
    </c:if>
    Name: <input type="text" name="name" value="${requestScope.user.name}"><br>
    Sername: <input type="text" name="sername" value="${requestScope.user.sername}"><br>
    Email: <input type="text" name="email" value="${requestScope.user.email}"><br>
    <input type="submit" value="Ok">
</form>
</body>
</html>

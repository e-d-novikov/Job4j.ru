<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${param.get("id")}">
        Name: <input type="text" name="name" value="${param.get("name")}"><br>
        Login: <input type="text" name="login" value="${param.get("login")}"><br>
        E-mail: <input type="text" name="email" value="${param.get("email")}"><br>
        Date: <input type="text" name="date" value="${param.get("date")}"><br>
        <input type="submit" value="Update" value="Обновить">
    </form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="AdminServlet" method="post">
    <input type="hidden" name="action" value="add_user">
    Login: <input type="text" name="login"><br>
    Password: <input type="text" name="password"><br>
    Role: <select name="role">
    <option value="admin">Администратор</option>
    <option value="user">Пользователь</option>
</select><br>
    Name: <input type="text" name="name"><br>
    Sername: <input type="text" name="sername"><br>
    Email: <input type="text" name="email"><br>
    <input type="submit" value="Add">
</form>
</body>
</html>

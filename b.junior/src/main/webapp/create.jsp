<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/user" method="post">
        <input type="hidden" name="action" value="add">
        Name: <input type="text" name="name"><br>
        Login: <input type="text" name="login"><br>
        E-mail: <input type="text" name="email"><br>
        Date: <input type="text" name="date"><br>
        <input type="submit" value="Создать">
    </form>
</body>
</html>

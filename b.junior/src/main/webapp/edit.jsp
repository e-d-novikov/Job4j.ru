<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/user" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
        Name: <input type="text" name="name" value="<%=request.getParameter("name")%>"><br>
        Login: <input type="text" name="login" value="<%=request.getParameter("login")%>"><br>
        E-mail: <input type="text" name="email" value="<%=request.getParameter("email")%>"><br>
        Date: <input type="text" name="date" value="<%=request.getParameter("date")%>"><br>
        <input type="submit" value="Update" value="Обновить">
    </form>
</body>
</html>

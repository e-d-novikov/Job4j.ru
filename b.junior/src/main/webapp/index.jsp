<%@ page import="d.servlet.http.User" %>
<%@ page import="d.servlet.http.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create.jsp" method="get">
    <input type="submit" name="Создать" value="Создать">
</form>
    <table>
        <% for (User user : ValidateService.getInstance().findAll()) {%>
        <tr>
            <td><%=user.toString()%></td>
            <td>
                <form action="<%=request.getContextPath()%>/edit.jsp" method="get">
                    <input type="hidden" name="id" value="<%=user.getId()%>">
                    <input type="hidden" name="name" value="<%=user.getName()%>">
                    <input type="hidden" name="login" value="<%=user.getLogin()%>">
                    <input type="hidden" name="email" value="<%=user.getEmail()%>">
                    <input type="hidden" name="date" value="<%=user.getDate()%>">
                    <input type="submit" name="Редактировать" value="Редактировать">
                </form>
            </td>
            <td>
                <form action="<%=request.getContextPath()%>/user" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%=user.getId()%>">
                    <input type="submit" name="Удалить" value="Удалить">
                </form>
            </td>
        </tr>
        <%}%>
    </table>
</body>
</html>

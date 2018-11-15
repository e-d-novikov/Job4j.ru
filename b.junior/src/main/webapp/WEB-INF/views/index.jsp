<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="action" value="create">
    <input type="submit" name="Создать" value="Создать">
</form>
    <table>
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.toString()}"></c:out></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                    <input type="hidden" name="name" value="<c:out value="${user.name}"></c:out>">
                    <input type="hidden" name="login" value="<c:out value="${user.login}"></c:out>">
                    <input type="hidden" name="email" value="<c:out value="${user.email}"></c:out>">
                    <input type="hidden" name="date" value="<c:out value="${user.date}"></c:out>">
                    <input type="submit" name="Редактировать" value="Редактировать">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                    <input type="submit" name="Удалить" value="Удалить">
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="AdminServlet" method="post">
    <input type="hidden" name="action" value="new_user">
    <input type="submit" value="Add">
</form>
<c:if test="${requestScope.users.size() > 0}">
    <h3>Users:</h3>
    <table>
        <tr>
            <td>ID</td>
            <td>Login</td>
            <td>Password</td>
            <td>Role</td>
            <td>Name</td>
            <td>Sername</td>
            <td>Email</td>
            <td></td><td></td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.role}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.sername}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td>
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="login" value="${user.login}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="login" value="${user.login}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<form action="AdminServlet" method="post">
    <input type="hidden" name="action" value="back">
    <input type="submit" value="Back">
</form>
</body>
</html>

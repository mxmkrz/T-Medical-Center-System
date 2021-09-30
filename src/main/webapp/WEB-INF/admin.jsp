<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<div>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>Password</th>
            <th>Roles</th>
        </tr>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
                </td>
                <td>
                        <%--                    <form:form action="${pageContext.request.contextPath}/admin" method="post">--%>
                        <%--                        <input type="hidden" name="userId" value="${user.id}"/>--%>
                        <%--                        <input type="hidden" name="action" value="delete"/>--%>
                        <%--                        <button type="submit">Delete</button>--%>
                        <%--                    </form:form>--%>
                <td><a href="deleteUser?id=<c:out value="${user.id}"/>">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
</div>
</body>
</html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medical App</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%--<div align="center">--%>
<%--<h1 class="text-center" style="color:black">My App Login page!</h1>--%>
<%--<sec:authorize access="!isAuthenticated()">--%>
<%--    <h2>Log in</h2>--%>
<%--    <form:form action="authenticating" method="post" modelAttribute="staff">--%>
<%--        <form:label path="name">Enter your name:</form:label>--%>
<%--        <form:input path="name" placeholder="name"/><br>--%>
<%--        <form:label path="password">Enter your password:</form:label>--%>
<%--        <form:password path="password" placeholder="password"/><br>--%>
<%--        <button type="submit" class="btn btn-primary">Enter</button>--%>
<%--    </form:form>--%>
<%--    <li><a class="nav-link" href="/registration">Registration as doctor</a></li>--%>
<%--    <li><a class="nav-link" href="/nurse/registration">Registration as nurse</a></li>--%>
<%--</sec:authorize>--%>
<%--<sec:authorize access="isAuthenticated()">--%>
<%--    Welcome , <sec:authentication property="name"/>--%>
<%--    <form:form action="/menu" method="post">--%>
<%--    <button type="submit" class="btn btn-primary">Menu</button>--%>
<%--    </form:form>--%>
<%--    <form:form action="/logout" method="post">--%>
<%--        <button type="submit" class="btn btn-primary">Logout</button>--%>
<%--    </form:form>--%>
<%--</sec:authorize>--%>
<form:form action="/login/process" method="post">
    <div>
        Email: <input name="email" type="email">
    </div>
    <div>
        Password: <input name="password" type="password">
    </div>
    <input type="submit">
</form:form>
<#if error??>
<p>Bad credentials</p>
</
#if>
</body>
</html>

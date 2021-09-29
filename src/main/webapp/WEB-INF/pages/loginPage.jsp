<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ula_u
  Date: 29.09.2021
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<div class="container" style="width: 300px;">

    <form:form action="${}" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label>
            <input type="text" class="form-control" name="username" placeholder="Email address" required autofocus value="colibri">
        </label>
        <label>
            <input type="password" class="form-control" name="password" placeholder="Password" required value="1234">
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Enter</button>
    </form:form>
</div>
</body>
</html>

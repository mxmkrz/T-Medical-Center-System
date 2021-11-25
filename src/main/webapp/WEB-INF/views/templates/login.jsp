<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Medical App</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/fonts/ionicons.min.css">
    <link rel="stylesheet" href="../static/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <style>
        .align-center {
            text-align: center;
        }
    </style>
</head>
<body style="background-image: url('../static/images/hero-bg.jpg');">
<section class="login-clean">
    <div class="illustration"><em class="icon ion-ios-medkit"></em></div>
    <form:form action="/login/process" method="post">
        <div class="mb-3"><input class="form-control" type="text" name="email" placeholder="Email"></div>
        <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password"></div>
        <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Sing In</button></div>
        <td><strong><a class="nav-link active"
                       href="<c:url value="/registration"/>">Sing Up</a></strong>
        </td>
        <div class="align-center">
            <td><a class="btn btn-primary btn-sm"
                   href="<c:url value="/login/reset"/>">Change password</a>
            </td>
        </div>


        <c:if test="${param.error != null}">
            <div class="mt-5" style="color: #f80030">Invalid username or password</div>
        </c:if>
    </form:form>
</section>
</body>
</html>

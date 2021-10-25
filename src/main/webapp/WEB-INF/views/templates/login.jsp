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
        body {
            background: url('../static/images/hero-bg.jpg');
        }
    </style>
</head>
<body>
<section class="login-clean">
    <p>
    <div class="illustration"><i class="icon ion-ios-medkit"></i></div>
    <form:form action="/login/process" method="post">
        <div class="mb-3"><input class="form-control" type="text" name="name" placeholder="Name"></div>
        <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password"></div>
        <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Sing In</button></div>
        <td><strong><a class="nav-link active"
                       href="<c:url value="/registrationMedicalStaff"/>">Sing Up</a></strong>
        </td>
        <c:if test="${param.error != null}">
            <div class="mt-5" style="color: #0610d4">Invalid username or password</div>
        </c:if>
    </form:form>
    </p>
</section>
</body>
</html>

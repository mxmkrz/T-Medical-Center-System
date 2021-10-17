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
</head>

<body>

<section class="login-clean">
    <h2 class="visually-hidden">Login Form</h2>
    <div class="illustration"><i class="icon ion-ios-medkit"></i></div>
    <form:form action="/login/process" method="post">
        <div class="mb-3"><input class="form-control" type="text" name="name" placeholder="Name"></div>
        <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password"></div>
        <div class="mb-3">
            <button class="btn btn-primary d-block w-100" type="submit">Sing In</button>
        </div>
        <div class="nav-item">
            <a class="btn btn-primary d-block w-100" href="<c:url value="/registrationMedicalStaff"/>">&nbspSign up</a>
        </div>
    </form:form>
</section>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
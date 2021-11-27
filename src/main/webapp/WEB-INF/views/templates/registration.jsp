<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Doctor</title>
    <meta charset="utf-8">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
</head>
<body style="background-image: url('../static/images/Fotolia_133334155_M-1.jpg')">
<div class="nav-item">
    <a class="navbar-brand">
        <a class="nav-link" href="/menu">Back</a>
    </a>
</div>
<div class="container register-form">
    <div class="form">
        <form:form action="/menu/registration" method="post" modelAttribute="staff">
        <h2 class="text"><strong>Create</strong> an account for medical staff</h2>
        <div class="col-md-4">
            <div class="form-group">
                <form:input type="email" class="form-control" placeholder="Email" path="email"/>
                <form:errors path="email" cssClass="error"/>
            </div>
            <div class="form-group">
                <form:input type="text" class="form-control" placeholder="Name" path="name"/>
                <form:errors path="name" cssClass="error"/>
            </div>
            <div class="form-group">
                <form:input type="text" class="form-control" placeholder="Surname" path="surname"/>
                <form:errors path="surname" cssClass="error"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:input type="password" class="form-control" placeholder="Password" path="password"/>
                <form:errors path="password" cssClass="error"/>
            </div>
            <div class="form-group">
                <form:input type="password" class="form-control" placeholder="Confirm Password" path="confirmPasswordStaff"/>
                <form:errors path="confirmPasswordStaff" cssClass="error"/>
            </div>
        </div>
        <div class="col-md-4">
            <form:select class="form-select" aria-label="Floating label select example" path="role">
                <form:option value="ROLE_DOCTOR">DOCTOR</form:option>
                <form:option value="ROLE_NURSE">NURSE</form:option>
            </form:select>
            <form:errors path="role" cssClass="error"/>
        </div>
    </div>
    <button class="btn btn-primary col-md-4" type="submit">Sign Up</button>
    </form:form>
</div>


<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>

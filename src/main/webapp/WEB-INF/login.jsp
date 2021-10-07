<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medical App</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>

<form:form action="/login/process" method="post">
    <div>
        <label>
            <input name="name" type="name" placeholder="Username"
                   autofocus="true"/>
        </label>
        <label>
            <input name="password" type="password" placeholder="Password"/>
        </label>
        <button type="submit">Log In</button>
        <h4><a href="/registrationDoctor">Register Doctor</a></h4>
        <h4><a href="/registrationNurse">Register Nurse</a></h4>
    </div>
</form:form>
</body>
</html>

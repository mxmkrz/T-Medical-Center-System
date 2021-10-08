<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All appointments</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<h1>All Appointment</h1>
<table class="table table-bordered">
    <tr>
        <th>doses</th>
        <th>description</th>

    </tr>
    <c:forEach var="appointment" items="${appointment}">
        <tr>
            <td>${appointment.dosage}</td>
            <td>${appointment.description}</td>

        </tr>
    </c:forEach>
</body>
</html>


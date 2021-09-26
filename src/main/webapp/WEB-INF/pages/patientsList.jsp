<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All patients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<h1>Patients</h1>

<table class="table table-dark table-hover">
    <tr>
        <th>firstName</th>
        <th>secondName</th>
        <th>diagnosis</th>
        <th>insuranceNumber</th>
        <th>doctorsName</th>
        <th>status</th>
    </tr>
    <c:forEach var="patient" items="${patients}">
        <tr>
            <td>${patient.firstName}</td>
            <td>${patient.secondName}</td>
            <td>${patient.diagnosis}</td>
            <td>${patient.insuranceNumber}</td>
            <td>${patient.doctorsName}</td>
            <td>${patient.status}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>


<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All patients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<h1>All Patients</h1>
<table class="table table-bordered">
    <tr>
        <th>Id</th>
        <th>FirstName</th>
        <th>SecondName</th>
        <th>Diagnosis</th>
        <th>InsuranceNumber</th>
        <th>Status</th>
        <th>Delete</th>
        <th>Update</th>
    </tr>
    <c:forEach var="patient" items="${patients}">
        <tr>
            <td>${patient.id}</td>
            <td>${patient.name}</td>
            <td>${patient.surname}</td>
            <td>${patient.diagnosis}</td>
            <td>${patient.insuranceNumber}</td>
            <td>${patient.status}</td>
            <td><a href="deletePatient?id=<c:out value="${patient.id}"/>">Delete</a></td>
            <td><a href="editPatient?id=<c:out value="${patient.id}"/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
<ul>
    <li>
        <a href="/addPatient">Add new Patient</a>
    </li>
</ul>

</body>
</html>


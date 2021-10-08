<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All doctors</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<h1>All Doctors</h1>
<table class="table table-bordered">
    <tr>
        <th>Id</th>
        <th>FirstName</th>
        <th>SecondName</th>
        <th>Position</th>
        <th>Specialization</th>
        <th>Delete</th>
        <th>Update</th>
    </tr>
    <c:forEach var="doctors" items="${doctors}">
        <tr>
            <td>${doctors.id}</td>
            <td>${doctors.name}</td>
            <td>${doctors.surname}</td>
            <td>${doctors.specialization}</td>
            <td>${doctors.position}</td>
            <td><a href="deleteDoctor?id=<c:out value="${doctors.id}"/>">Delete</a></td>
            <td><a href="editDoctor?id=<c:out value="${doctors.id}"/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
<ul>
    <li>
        <a href="/addDoctor">Add new Doctor</a>
    </li>
</ul>

</body>
</html>


<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All patients</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
</head>
<body>
<h1>All Patients</h1>
<table class="table">
    <tr>
        <th>FirstName</th>
        <th>SecondName</th>
        <th>Profile Patient</th>
    </tr>
    <c:forEach var="patient" items="${patients}">
        <tr>
            <td>${patient.name}</td>
            <td>${patient.surname}</td>
            <td><a href="<c:url value="/patient/profile/${patient.id}"/>">Profile Patient</a></td>
        </tr>
    </c:forEach>
</table>
<ul>
    <li>
        <a href="/patient/add">Add new Patient</a>
    </li>
</ul>
<ul>
    <li>

    </li>
</ul>
</body>
</html>


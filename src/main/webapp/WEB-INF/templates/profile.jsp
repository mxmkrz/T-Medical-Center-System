<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile Patient</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
</head>
<body>
<h1>Profile Patient</h1>
<td>Name:</td>
<td>${profile.name}</td>
<td>Surname:</td>
<td>${profile.surname}</td>
<td>InsuranceNumber:</td>
<td>${profile.insuranceNumber}</td>
<td>Diagnosis:</td>
<td>${profile.diagnosis}</td>

<ul>
    <li>
        <a href="/patient/edit?id=<c:out value="${profile.id}"/>">Update</a>
    </li>
</ul>
<ul>
    <li>
        <a href="/patient/delete?id=<c:out value="${profile.id}"/>">Delete Patient</a>
    </li>
</ul>
<ul>
    <li>
        <a href="/appointment/appointment?id=<c:out value="${profile.id}"/>">Delete Patient</a>
    </li>
</ul>
</body>
</html>



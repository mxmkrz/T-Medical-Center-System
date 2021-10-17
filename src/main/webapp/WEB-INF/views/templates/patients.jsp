<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="../static/css/styles.css">

</head>

<body>
<div class="btn-group" role="group"></div>
<nav class="navbar navbar-light navbar-expand-md navigation-clean-button">
    <div class="container"><a class="navbar-brand" href="/doctor/patients">Patients</a>
        <a data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span
                class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></a>
        <div class="navbar-nav me-auto">
            <a class="nav-link" href="/doctor/add"><strong>Add patient</strong></a>
            <a class="nav-item">
                <form action="/logout" method="post">
                    <input type="submit" class="btn btn-danger" value="Logout"/>
                </form>
            </a>
        </div>
    </div>
</nav>
<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>Name / Surname</th>
            <th>Insurance number</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <td><a class="nav-link active"
                       href="<c:url value="/doctor/profile/${patient.id}"/>">${patient.name} ${patient.surname}</a>
                </td>
                <td><a class="nav-link active"
                       href="<c:url value="doctor/profile/${patient.id}"/>">${patient.insuranceNumber}</a></td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>


</html>

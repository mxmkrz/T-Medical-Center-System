<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="\">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Bootstrap-4---Table-Fixed-Header.css">
    <link rel="stylesheet" href="../static/css/styles.css">
</head>

<body>

<div class="table-responsive container">
    <table class="table table-bordered table-hover">
        <tr class="table-active">
            <th>Name</th>
            <th>Surname</th>
            <th>Date of Start</th>
            <th>Date of End</th>
            <th>Type</th>
            <th>Info Procedure</th>
            <th>Info Drugs</th>
            <th>Info Dose</th>
            <th>Week Day</th>
            <th>Event Times</th>
            <th>Edit Appointment</th>

        </tr>

        <c:forEach var="appointments" items="${pageAppointment}">
            <tr>
                <td><c:out value="${patient.name}" /></td>
                <td><c:out value="${patient.surname}" /></td>
                <td><c:out value="${appointments.startOfData}" /></td>
                <td><c:out value="${appointments.endOfData}" /></td>
                <td><c:out value="${appointments.type}" /></td>
                <td><c:out value="${appointments.info}" /></td>
                <td><c:out value="${appointments.infoDrugs}" /></td>
                <td><c:out value="${appointments.dose}" /></td>
                <td><c:out value="${appointments.weekDayString}" /></td>
                <td><c:out value="${appointments.eventTimes}" /></td>
                <td><a class="nav-link active"
                       href="<c:url value="/patient/profile/${patient.id}/edit/${appointments.id}"/>">Edit Appointment</a></td>
            </tr>
        </c:forEach>
    </table>

</div>






<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>
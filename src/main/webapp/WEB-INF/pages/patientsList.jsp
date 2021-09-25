
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Read Contacts</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>id</th>
        <th>firstName</th>
        <th>secondName</th>
        <th>diagnosis</th>
        <th>insuranceNumber</th>
        <th>doctorsName</th>
        <th>status</th>
    </tr>
    <c:forEach var="patient" items="${patients}">
        <tr>
            <td>${patient.id}</td>
            <td>${patient.firstName}</td>
            <td>${patient.secondName}</td>
            <td>${patient.diagnosis}</td>
            <td>${patient.insuranceNumber}</td>
            <td>${patient.doctorsName}</td>
            <td>${patient.status}</td>
        </tr>
    </c:forEach>
</table>
<br/>

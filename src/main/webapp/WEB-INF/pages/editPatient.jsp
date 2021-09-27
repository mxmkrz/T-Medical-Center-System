<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ula_u
  Date: 27.09.2021
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Patient</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Update Patient</h1>
<form:form action="edit" method="post" modelAttribute="patient">
    <form:hidden path="id"/>
    <tr>
        <td>Name:</td>
        <td><form:input path="firstName"/></td>
    </tr>
    <tr>
        <td>Surname:</td>
        <td><form:input path="secondName" readonly="true"/></td>
    </tr>
    <tr>
        <td>Diagnosis:</td>
        <td><form:input path="diagnosis" readonly="true"/></td>
    </tr>
    <tr>
        <td>Insurance Number:</td>
        <td><form:input path="insuranceNumber" readonly="true"/></td>
    </tr>
    <tr>
        <td>Name doctor:</td>
        <td><form:input path="doctorsName" readonly="true"/></td>
    </tr>
    <tr>
        <td>Status:</td>
        <td><form:input path="status"/></td>
    </tr>

    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</body>
</html>

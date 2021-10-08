<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Patient</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<div align="center">
    <h1 class="text-center">Add new Patient</h1>
    <form:form action="/patient/add" method="post" modelAttribute="patient">
        <table border="0" cellpadding="5">
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td><form:input path="surname"/></td>
            </tr>
                <td>Diagnosis:</td>
                <td><form:input path="diagnosis"/></td>
            </tr>
            <tr>
                <td>Insurance Number:</td>
                <td><form:input path="insuranceNumber"/></td>
            </tr>
        </table>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form:form>
</div>
</body>
</html>

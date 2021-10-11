<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Doctor</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Update Doctor</h1>
<form:form action="editDoctor" method="post" modelAttribute="doctor">
    <form:hidden path="id"/>
    <tr>
        <td>Name:</td>
        <td><form:input path="name"/></td>
    </tr>
    <tr>
        <td>Surname:</td>
        <td><form:input path="surname"/></td>
    </tr>
    <tr>
        <td>Position:</td>
        <td><form:input path="position"/></td>
    </tr>
    <tr>
        <td>Specialization:</td>
        <td><form:input path="specialization"/></td>
    </tr>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</body>
</html>

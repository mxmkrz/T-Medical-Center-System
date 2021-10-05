<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Doctor</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<div align="center">
  <h1 class="text-center">Add new Doctor</h1>
  <form:form action="/addDoctor" method="post" modelAttribute="doctor">
    <table border="0" cellpadding="5">
      <tr>
        <td>Name:</td>
        <td><form:input path="name"/></td>
      </tr>
      <tr>
        <td>Surname:</td>
        <td><form:input path="surname"/></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><form:input path="password"/></td>
      </tr>
      <tr>
        <td>Confirm Password:</td>
        <td><form:input path="passwordConfirm"/></td>
      </tr>
      <tr>
        <td>Position:</td>
        <td><form:input path="position"/></td>
      </tr>
      <tr>
        <td>Specialization:</td>
        <td><form:input path="specialization"/></td>
      </tr>
    </table>
    <div class="col-auto">
      <button type="submit" class="btn btn-primary">Save</button>
    </div>
  </form:form>
</div>
</body>
</html>

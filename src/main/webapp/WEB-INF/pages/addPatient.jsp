<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ula_u
  Date: 27.09.2021
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Patient</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<div align="center">
    <h1 class="text-center">Add new Employee</h1>
    <form:form action="add" method="post" modelAttribute="patient">
        <table border="0" cellpadding="5">
            <tr>
                <td>Name:</td>
                <td><form:input path="firstName"/></td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td><form:input path="secondName"/></td>
            </tr>
<%--            <tr>--%>
<%--                <td>Diagnosis:</td>--%>
<%--                <td><form:input path="diagnosis"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Insurance Number:</td>--%>
<%--                <td><form:input path="insuranceNumber"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Name doctor:</td>--%>
<%--                <td><form:input path="doctorsName"/></td>--%>
<%--            </tr>--%>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>

    </form:form>
</div>
</body>
</html>

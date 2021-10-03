<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/sign_up" method="post" modelAttribute="user">
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
            <td>Email:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input path="password"/></td>
        </tr>

    </table>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Save</button>
    </div>
</form:form>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form:form action="/login/process" method="post">
    <div>
        Email: <label>
        <input name="email" type="email">
    </label>
    </div>
    <div>
        Password: <label>
        <input name="password" type="password">
    </label>
    </div>
    <input type="submit">
</form:form>

</body>
</html>

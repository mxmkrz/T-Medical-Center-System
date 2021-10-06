<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/login/process" method="post">
    <div>
        <label>
            <input name="name" type="name" placeholder="Username"
                   autofocus="true"/>
        </label>
        <label>
            <input name="password" type="password" placeholder="Password"/>
        </label>
        <button type="submit">Log In</button>
        <h4><a href="/sig_up">Register</a></h4>
    </div>
</form:form>
</body>
</html>

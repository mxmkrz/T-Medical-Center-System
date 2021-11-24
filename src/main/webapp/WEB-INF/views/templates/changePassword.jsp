<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/fonts/ionicons.min.css">
    <link rel="stylesheet" href="../static/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <style>
        body {
            background: url('../static/images/hero-bg.jpg');
        }
    </style>
    <style>
        body {
            margin-top: 20px;
            background-color: #f2f3f8;
        }

        .card {
            margin-bottom: 1.5rem;
            box-shadow: 0 1px 15px 1px rgba(52, 40, 104, .08);
        }

        .card {
            position: relative;
            display: -ms-flexbox;
            display: flex;
            -ms-flex-direction: column;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 1px solid #e5e9f2;
            border-radius: .2rem;
        }

        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }

    </style>
</head>
<body>
<nav class="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" style="color: #0610d4" href="/logout">Back</a>
    </div>
</nav>
<div class="container h-100">
    <div class="row h-100">
        <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
            <div class="d-table-cell align-middle">
                <form:form action="/login/change" method="post" modelAttribute="changePassword">
                    <div class="card">
                        <div class="card-body">
                            <div class="m-sm-4">
                                    <div class="form-group">
                                        <input class="form-control form-control-lg" type="password" name="oldPassword"
                                               placeholder="Old password" autocomplete="numeric">
                                        <form:errors path="oldPassword" cssClass="error"/>
                                        <br>
                                        <input class="form-control form-control-lg" type="password" name="newPassword"
                                               placeholder="New password" autocomplete="numeric">
                                        <form:errors path="newPassword" cssClass="error"/>
                                        <br>
                                        <input class="form-control form-control-lg" type="password"
                                               name="confirmPassword"
                                               placeholder="Confirm password" autocomplete="numeric">
                                        <form:errors path="confirmPassword" cssClass="error"/>
                                    </div>
                                    <div class="text-center mt-3">
                                        <button type="submit" class="btn btn-lg btn-primary">Change password</button>
                                    </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

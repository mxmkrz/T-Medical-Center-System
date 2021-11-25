<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="\">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="../static/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="../static/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="../static/css/Ludens-Users---3-Profile.css">
    <link rel="stylesheet" href="../static/css/styles.css">
</head>
<body style="background-image: url('../static/images/Fotolia_133334155_M-1.jpg')">
    <form:form action="/doctor/profile/${profile.id}/edit" method="post" modelAttribute="profile"
                   class="formWithValidation3">
            <form:hidden path="id"/>
    <main class="page" style="min-height: 100%;">
        <section class="clean-block about-us">
            <div class="row" style="margin-right: 0px;margin-left: 0px;">
                <div class="col-md-12" style="margin-bottom: 25px;padding-left: 75px;font-size: 21px;margin-top: 73px;">
                    <a class="anone" href="/doctor/profile/${profile.id}"><em
                            class="fa fa-long-arrow-left"></em><span>&nbsp;Back</span></a>
                </div>
                <div class="col-md-12" style="margin-bottom: 25px;padding-left: 75px;font-size: 21px;margin-top: 73px;">
                    <a class="anone" href="/logout"><em class="fa fa-long-arrow-left"></em><strong>&nbsp;
                        Logout</strong></a>
                </div>
            </div>
            <div class="row" style="margin-right: 0px;margin-left: 0px;">
            </div>
            <div class="row justify-content-center" style="margin-right: 0px;margin-left: 0px;">
                <div class="col-sm-6 col-lg-4" style="padding-right: 0px;padding-left: 0px;">
                    <div class="card clean-card text-center">
                        <div class="card-body info">
                            <div class="row" style="margin-top: -24px;">
                                <div class="col-md-12" style="margin-top: 22px;">
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Name</strong></p>
                                        </div>
                                        <div class="col">
                                            <label>
                                                <input class="form-control" type="text" name="name"
                                                       placeholder="${profile.name}">
                                                <form:errors path="name"/>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Surname</strong></p>
                                        </div>
                                        <div class="col">
                                            <label>
                                                <input class="form-control" type="text" name="surname"
                                                       placeholder="${profile.surname}">
                                                <form:errors path="surname"/>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Diagnosis</strong></p>
                                        </div>
                                        <div class="col">
                                            <label>
                                                <input class="form-control" type="text" name="diagnosis"
                                                       placeholder="${profile.diagnosis}">
                                                <form:errors path="diagnosis"/>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Insurance Number</strong></p>
                                        </div>
                                        <div class="col">
                                            <label>
                                                <input class="form-control" type="number" name="insuranceNumber"
                                                       placeholder="${profile.insuranceNumber}">
                                                <form:errors path="insuranceNumber"/>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Status</strong></p>
                                        </div>
                                        <div class="col">
                                            <label>
                                                <form:select class="form-selected" path="status">
                                                    <form:option value="PATIENT">PATIENT</form:option>
                                                    <form:option value="DISCHARGED">DISCHARGED</form:option>
                                                </form:select>
                                                <form:errors path="status"/>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 content-right">
                                            <button class="btn btn-success">SAVE</button>
                                            <button class="btn btn-danger form-btn" type="reset">CANCEL</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
</form:form>
</body>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</html>
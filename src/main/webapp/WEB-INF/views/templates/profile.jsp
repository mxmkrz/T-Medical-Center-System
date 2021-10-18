<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <base href="\">
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/fonts/material-icons.min.css">
    <link rel="stylesheet" href="../static/css/Profile-with-data-and-skills.css">
    <link rel="stylesheet" href="../static/css/styles.css">

</head>
<body>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin"
                                 class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${profile.name} ${profile.surname}</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-3">

                    <%--Кнопки назначений--%>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Full Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${profile.name} ${profile.surname}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Diagnosis</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${profile.diagnosis}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Insurance Number</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${profile.insuranceNumber}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Status</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${profile.status}
                            </div>
                        </div>
                        <hr>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Doctor's name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${profile.doctorsName}
                            </div>
                        </div>
                        <hr>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info"
                                   href="/doctor/profile/${profile.id}/edit">Edit</a>
                            </div>
                        </div>
                        <hr>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="col-sm-12">
                                    <a class="btn btn-info"
                                       href="/doctor/delete/${profile.id}">Delete</a>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="col-sm-12">
                                    <a class="btn btn-info"
                                       href="/doctor/profile/${profile.id}/appointment">Create Appointment</a>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="col-sm-12">
                                    <a class="btn btn-info"
                                       href="/doctor/profile/${profile.id}/pageAppointment">Appointments</a>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </div>
                </div>


                <div class="row gutters-sm">
                    <%--Список назначений--%>
                </div>
            </div>


        </div>
    </div>
</div>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>


<script>
    <%--var url = '/patient/profile?id=<c:out value="${profile.id}"/>';--%>
    <%--var id = location.pathname.split('/')[3];--%>
    <%--alert(id)--%>
    <%--sessionStorage.setItem('key', id);--%>
</script>


</body>
</html>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <link rel="stylesheet" href="../static/css/gradient-navbar-1.css">
    <link rel="stylesheet" href="../static/css/gradient-navbar.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand&display=swap" rel="stylesheet">

    <style>
        p.exserif { font-family: "Quicksand", "Book Antiqua", serif; }

    </style>
    <style>
        li {
            list-style-type: none;
        }
    </style>

    <style>
    body {
    background: url('../static/images/hero-bg.jpg');
    }</style>

</head>
<body>
<p>
<p class="exserif">
<nav class="navbar navbar-dark navbar-expand-md" id="app-navbar">
    <div class="container-fluid"><a class="navbar-brand" href="#"><i class="icon ion-ios-infinite" id="brand-logo"></i></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link active" href=/doctor/add>Add Patient</a></li>
                <li class="nav-item"><a class="nav-link" href="<c:url value="/logout"/>">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container py-5">


    <div class="container-fluid">
        <div class="card shadow">
            <div class="card-header py-3">
                <p class="text-primary m-0 fw-bold" align="center" >Patient Information </p>
            </div>
            <div class="card-body">
                <div class="patients_input form-group">
                    <div class="table-responsive table mt-2"  role="grid" aria-describedby="dataTable_info">
                        <table class="table my-0">
                            <thead>
                            <tr align="center">
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Insurance number</th>
                                <th>Diagnosis</th>
                                <th>Create Data Time</th>
                                <th>Update Data Time</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <c:forEach var="patient" items="${patients}">
                            <tr align="center">
                                <td><strong><a class="nav-link active"
                                               href="/doctor/profile/${patient.id}">${patient.name}</a></strong>
                                </td>
                                <td><strong><a class="nav-link active"
                                               href="/doctor/profile/${patient.id}">${patient.surname}</a></strong>
                                </td>
                                <td><strong><a class="nav-link active"
                                               href="/doctor/profile/${patient.id}">${patient.insuranceNumber}</a></strong>
                                </td>
                                <td><strong><a class="nav-link active"
                                               href="/doctor/profile/${patient.id}">${patient.diagnosis}</a></strong>
                                </td>
                                <td><strong><a class="nav-link active"
                                               href="/doctor/profile/${patient.id}">${patient.createDataTime}</a></strong>
                                </td>
                                <td><strong><a class="nav-link active"
                                               href="/doctor/profile/${patient.id}">${patient.updateDataTime}</a></strong>
                                </td>
                                <td><strong><a class="nav-link active"
                                               href="/doctor/profile/${patient.id}">${patient.status}</a></strong>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr align="center">
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Insurance number</th>
                                <th>Diagnosis</th>
                                <th>Create Data Time</th>
                                <th>Update Data Time</th>
                                <th>Status</th>
                            </tr>
                            </tfoot>
                        </table>
                        <br>
                        <c:if test="${totalElements > 4 }">
                        <h6 align="center">Showing ${number+1} page of ${totalPages} pages of ${totalElements} events</h6>
                        <ul class="pagination justify-content-center">
                            <li class="page-item">
                                <c:if test="${number != 0}">
                                    <a class="page-link" tabindex="-1"
                                       href="/doctor/patients?page=${number-1}&size=${size}">Previous</a>
                                </c:if>
                            </li>
                            <c:forEach begin="0" end="${totalPages-1}" var="page">
                                <li class="page-item">
                                    <a href="/doctor/patients?page=${page}&size=${size}"
                                       class="page-link"> ${page+1} </a>
                                </li>
                            </c:forEach>
                            </c:if>
                            <li>
                                <c:if test="${number lt totalPages - 1}">
                                    <a class="page-link"
                                       href="/doctor/patients?page=${number+1}&size=${size}">Next</a>
                                </c:if>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</p>
</p>
</body>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</html>

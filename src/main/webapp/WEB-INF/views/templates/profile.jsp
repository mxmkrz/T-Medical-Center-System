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
    <style>
        body {
            background: url('../static/images/Fotolia_133334155_M-1.jpg');
        }
    </style>
</head>
<body>
<div>
    <p>
    <main class="page" style="min-height: 100%;">
        <section class="clean-block about-us">
            <div class="row" style="margin-right: 0px;margin-left: 0px;">
                <div class="col-md-12" style="margin-bottom: 25px;padding-left: 75px;font-size: 21px;margin-top: 73px;">
                    <a class="anone" href="/doctor/patients"><i
                            class="fa fa-long-arrow-left"></i><span>&nbsp;Back</span></a>
                </div>
                <div class="col-md-12" style="margin-bottom: 25px;padding-left: 75px;font-size: 21px;margin-top: 73px;">
                    <a class="anone" href="/logout"><i class="fa fa-long-arrow-left"></i><strong>&nbsp; Logout</strong></a>
                </div>
            </div>
            <div class="row" style="margin-right: 0px;margin-left: 0px;">
            </div>
            <h3 align="center">Patient profile</h3>
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
                                            <p class="labels">${profile.name}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Surname</strong></p>
                                        </div>
                                        <div class="col">
                                            <p class="labels">${profile.surname}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Email</strong></p>
                                        </div>
                                        <div class="col">
                                            <p class="labels">${profile.email}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Diagnosis</strong></p>
                                        </div>
                                        <div class="col">
                                            <p class="labels">${profile.diagnosis}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Insurance Number</strong></p>
                                        </div>
                                        <div class="col">
                                            <p class="labels">${profile.insuranceNumber}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Status</strong></p>
                                        </div>
                                        <div class="col">
                                            <p class="labels">${profile.status}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <p class="labels"><strong>Doctor's name</strong></p>
                                        </div>
                                        <div class="col">
                                            <p class="labels">${profile.doctorsName}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                         <span class="py-2 px-3 bg-transparent text-white rounded mybtn">
                                        <div class="col-md-12"><a class="btn btn-outline-secondary" type="button"
                                                                  href="/doctor/profile/${profile.id}/edit"><i
                                                class="fas fa-pencil-alt"></i>&nbsp;Edit</a></div>
                                                   </span>
                                    </div>
                                    <div class="row">
                                         <span class="py-2 px-3 bg-transparent text-white rounded mybtn">
                                        <div class="col-md-12"><a class="btn btn-outline-danger" role="button"
                                                                  href="/doctor/delete/${profile.id}"><i
                                                class="fas fa-trash"></i>&nbsp;Delete</a></div>
                                               </span>
                                    </div>
                                    <div class="row">
                                        <span class="py-2 px-3 bg-transparent text-white rounded mybtn">
                                        <div class="col-md-12"><a class="btn btn-outline-primary" role="button"
                                                                  href="/doctor/profile/${profile.id}/appointments"><i
                                                class="fas fa-pager"></i>&nbsp;Appointments</a></div>
                                             </span>
                                    </div>
                                    <div class="row">
                                        <c:if test="${profile.status == 'DISCHARGED'}">
                                        <span hidden class="py-2 px-3 bg-transparent text-white rounded mybtn">
                                                                <div hidden class="col-md-12"><a
                                                                        class="btn btn-outline-success"
                                                                        role="button"
                                                                        href="/doctor/profile/${profile.id}/appointment"><i
                                                                        class="fas fa-newspaper"></i>&nbsp;Make an appointment</a></div>
                                        </span>
                                        </c:if>
                                        <c:if test="${profile.status == 'PATIENT'}">
                                        <span  class="py-2 px-3 bg-transparent text-white rounded mybtn">
                                                                <div class="col-md-12"><a
                                                                        class="btn btn-outline-success"
                                                                        role="button"
                                                                        href="/doctor/profile/${profile.id}/appointment"><i
                                                                        class="fas fa-newspaper"></i>&nbsp;Make an appointment</a></div>
                                        </span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
    </main>
</div>


<script>
    $('#delete-patient').modal({
        keyboard: true,
        // backdrop:"static"
    })
</script>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</body>
</p>
</html>
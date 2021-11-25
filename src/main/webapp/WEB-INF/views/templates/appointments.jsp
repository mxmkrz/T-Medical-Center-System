<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="visibility" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="\">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Bootstrap-4---Table-Fixed-Header.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <script src="../static/bootstrap/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <style>
        .align-center {
            text-align: center;
        }
        li {
            list-style-type: none;
        }
    </style>
</head>

<body style="background-image: url('../static/images/1612683701_129-p-zelenii-meditsinskii-fon-170.jpg')">
<nav class="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" style="color: #e8f3ff" href="/doctor/profile/${patient.id}">Back</a>
        <a class="navbar-brand fa-align-left" style="color: #e8f3ff" href="/logout"><strong>Logout</strong></a>
    </div>
</nav>
<div class="container-fluid">
    <h3 class="text-white mb-4">List appointments</h3>
    <div class="card shadow">
        <div class="card-header py-3">
            <p class="text-black m-0 fw-bold">Important Information </p>
        </div>
        <div class="card-body">
            <div class="patients_input form-group">
                <div class="table-responsive table mt-2" role="grid" aria-describedby="dataTable_info">
                    <table class="table table my-0">
                        <thead>
                        <tr>
                            <th scope="row">Name/Surname</th>
                            <th scope="row">Date of Start</th>
                            <th scope="row">Date of End</th>
                            <th scope="row">Type</th>
                            <th scope="row">Info Procedure</th>
                            <th scope="row">Info Drugs</th>
                            <th scope="row">Info Dose</th>
                            <th scope="row">Week Day</th>
                            <th scope="row">Event Times</th>
                            <th scope="row">Status Appointment</th>
                            <th scope="row">Cancel Appointment</th>
                            <th scope="row">Done Appointment</th>
                            <th scope="row">Edit Appointment</th>
                            <th scope="row">Send an email</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach var="appointments" items="${pageAppointment}">
                        <tr id="done-${appointments.id}">
                            <td><c:out value="${patient.name} ${patient.surname}"/></td>
                            <td><c:out value="${appointments.startOfData}"/></td>
                            <td><c:out value="${appointments.endOfData}"/></td>
                            <td><c:out value="${appointments.type}"/></td>
                            <td><c:out value="${appointments.info}"/></td>
                            <td><c:out value="${appointments.infoDrugs}"/></td>
                            <td><c:out value="${appointments.dose}"/></td>
                            <td><c:out value="${appointments.weekDayString}"/></td>
                            <td><c:out value="${appointments.eventTimes}"/></td>
                            <td style="color: ${appointments.status.name() == 'FINISHED' ? '#dc3545' : appointments.status.name() == 'DONE' ? '#198754' : 'blue' }">
                                <c:out value="${appointments.status}"/></td>
                            <c:choose>
                                <c:when test="${appointments.status == 'FINISHED' || appointments.status == 'DONE'}">
                                    <td>
                                        <button type="button" disabled class="btn btn-outline-danger "
                                                data-toggle="modal"
                                                data-target="#cancel" data-done-id="${appointments.id}">Finish
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" disabled class="btn btn-outline-success " data-toggle="modal"
                                                data-target="#done" data-done-id="${appointments.id}">Done
                                        </button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <button type="button" class="btn btn-outline-danger " data-toggle="modal"
                                                data-target="#cancel" data-done-id="${appointments.id}">Finish
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button"  class="btn btn-outline-success " data-toggle="modal"
                                                data-target="#done" data-done-id="${appointments.id}">Done
                                        </button>
                                    </td>

                                </c:otherwise>
                            </c:choose>
                            <td><a class="btn btn-outline-secondary"
                                   href="<c:url value="/doctor/profile/${patient.id}/edit/${appointments.id}"/>">Edit Appointment</a>
                            </td>
                            <td>
                                <button type="button" class="btn btn-outline-info " data-toggle="modal"
                                        data-target="#email" data-done-id="${appointments.id}">Send An Email
                                </button>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <br>
                    <c:if test="${totalElements > 8 }">
                    <h6 class="align-center"><strong>Showing ${number+1} page of ${totalPages} pages of ${totalElements}
                        events</strong></h6>
                    <ul class="pagination justify-content-center">
                        <li class="page-item">
                            <c:if test="${number != 0}">
                                <a class="page-link" style="color: black" tabindex="-1"
                                   href="/doctor/profile/${patient.id}/appointments?page=${number-1}&size=${size}">Previous</a>
                            </c:if>
                        </li>
                        <c:forEach begin="0" end="${totalPages-1}" var="page">
                            <li class="page-item ">
                                <a href="/doctor/profile/${patient.id}/appointments?page=${page}&size=${size}"
                                   class="page-link" style="color: black"> ${page+1} </a>
                            </li>
                        </c:forEach>
                        </c:if>
                        <li>
                            <c:if test="${number lt totalPages - 1}">
                                <a class="page-link" style="color: black"
                                   href="/doctor/profile/${patient.id}/appointments?page=${number+1}&size=${size}">Next</a>
                            </c:if>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="email" tabindex="-1" role="dialog"
     aria-labelledby="allertCompleteEmailModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="allertCompleteEmailModal">Complete Send An Email</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form:form action="/doctor/profile/${patient.id}/email" method="post"
                           class="formWithValidation_email" role="form">
                    Are you sure you want to send the appointment by mail?
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idInputEmail">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden class="id field"
                                                     name="id" id="idInputEmail"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> No</button>
                        <button type="submit" class="btn btn-primary"> Yes</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="cancel" tabindex="-1" role="dialog"
     aria-labelledby="allertCompleteCancelModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="allertCompleteCancelModal">Complete Cancel</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form:form action="/doctor/profile/${patient.id}/pageAppointment" method="post"
                           class="formWithValidation_cancel" role="form">
                    Are you sure the appointment has been canceled?
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idInputCancel">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden class="id field"
                                                     name="id" id="idInputCancel"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> No</button>
                        <button type="submit" class="btn btn-primary"> Yes</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="done" tabindex="-1" role="dialog"
     aria-labelledby="allertCompleteDoneModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="allertCompleteDoneModal">Complete Done</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form:form action="/doctor/profile/${patient.id}/pageAppointment" method="post"
                           class="formWithValidation_done" role="form">
                    Are you sure the appointment has been canceled?
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idInputDone">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden class="id field"
                                                     name="id" id="idInputDone"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> No</button>
                        <button type="submit" class="btn btn-primary"> Yes</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</p>
<script>
    $("#email").on('show.bs.modal', function (e) {
        var id0 = $(e.relatedTarget).data('done-id');
        $('#idInputEmail').val(id0);

    });
    $("#cancel").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();
    });

    var form = document.querySelector('.formWithValidation_email')
    var id0 = form.querySelector('.id')

    form.addEventListener("submit", function (event) {
        event.preventDefault()

        console.log(form)
        $.ajax({
            url: '/doctor/profile/${patient.id}/email',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: id0.value,
            }),
            success: function (data) {
                window.location.reload();
            },
            error: function (result) {
                alert(result.responseText);
            },

        });
    });
</script>
<script>
    $("#cancel").on('show.bs.modal', function (e) {
        var id = $(e.relatedTarget).data('done-id');
        $('#idInputCancel').val(id);
    });
    $("#cancel").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();
    });

    var form = document.querySelector('.formWithValidation_cancel')
    var id = form.querySelector('.id')

    form.addEventListener("submit", function (event) {
        event.preventDefault()
        console.log(form)
        $.ajax({
            url: '/doctor/profile/${patient.id}/pageAppointment',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: id.value,
                status: 'FINISHED',
            }),
            success: function (data) {
                window.location.reload();
            },
            error: function (result) {
                alert(result.responseText);
            },

        });
    });
</script>
<script>
    $("#done").on('show.bs.modal', function (e) {
        var id = $(e.relatedTarget).data('done-id');
        $('#idInputDone').val(id);
    });
    $("#done").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();
    });

    var form2 = document.querySelector('.formWithValidation_done')
    var id2 = form2.querySelector('.id')

    form2.addEventListener("submit", function (event) {
        event.preventDefault()
        console.log(form2)
        $.ajax({
            url: '/doctor/profile/${patient.id}/pageAppointment',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: id2.value,
                status: 'DONE',
            }),
            success: function (data) {
                window.location.reload();
            },
            error: function (result) {
                alert(result.responseText);
            }
        });
    });
</script>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>
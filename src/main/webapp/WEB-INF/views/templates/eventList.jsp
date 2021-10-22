<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="visibility" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="\">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Table - Medical App</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Bootstrap-4---Table-Fixed-Header.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <script src="../static/bootstrap/js/jquery-3.2.1.min.js" type="text/javascript"></script>

    <script src="../static/bootstrap/js/jquery.tablesorter.js" type="text/javascript"></script>
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>

<ul class="nav justify-content-end">
    <li class="nav-item">
        <a class="navbar-brand">
            <span id="currentTime"></span>
        </a>
    </li>
    <li class="nav-item">
        <a class="navbar-brand">
            <form action="/logout" method="post">
                <input type="submit" class="btn btn-outline-danger" value="Logout"/>
            </form>
        </a>
    </li>
</ul>

<div class="container-fluid">
    <h3 class="text-dark mb-4">For clinic nurses</h3>
    <div class="card shadow">
        <div class="card-header py-3">
            <p class="text-primary m-0 fw-bold">Event Information </p>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6 text-nowrap">
                    <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable"><label class="form-label">Show&nbsp;
                        <select class="d-inline-block form-select form-select-sm" id="selectType">
                            <option value="----" >Please select a Filter Type</option>
                            <option value="AllPatientsFilter" >Patients Filter</option>
                            <option value ="AllPatients" >Patients</option>
                            <option value="ByDay" >Day</option>
                            <option value="ByHour" >Hour</option>
                        </select>&nbsp;</label></div>
                </div>
            </div>
            <div class="patients_input form-group" style="display:none;" >
                    <div class="table-responsive table mt-2"  role="grid" aria-describedby="dataTable_info">
                        <table class="table my-0">
                            <thead>
                            <tr>
                                <th>Patient Information</th>
                                <th>Date</th>
                                <th>Time</th>
                                <th>Event status</th>
                                <th>Type of therapy</th>
                                <th>Reason for cancellation</th>
                                <th>Cancel</th>
                                <th>Done</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <c:forEach var="event" items="${events}">
                                    <tr id="done-${event.id}" >
                                        <td>${event.patient.name} ${event.patient.surname}</td>
                                        <td>${event.eventDateTime}</td>
                                        <td>${event.time}</td>
                                        <td style="background-color: ${event.status.name() == "CANCELED" ? '#dc3545' : event.status.name() == "PLANNED" ? '#0d6efd' : event.status.name() == "DONE" ? '#198754' : 'white'}">${event.status.name()}</td>
                                        <td>${event.therapyType.name()}</td>
                                        <td>${event.reasonToCancel}</td>
                                        <td hidden>${event.patient.id}</td>
                                        <td hidden>${event.appointment.id}</td>
                                        <td><button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#status_cancel" data-done-id="${event.id}">Cancel</button></td>
                                        <td><button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#status_done" data-done-id="${event.id}">Done</button></td>
                                     </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td><strong>Patient Information</strong></td>
                                <td><strong>Date</strong></td>
                                <td><strong>Time</strong></td>
                                <td><strong>Event status</strong></td>
                                <td><strong>Type of therapy</strong></td>
                                <td><strong>Reason for cancellation</strong></td>
                                <td><strong>Cancel</strong></td>
                                <td><strong>Done</strong></td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
            </div>
            <div class="patientsFilter_input form-group"  >
                <div class="table-responsive table mt-2"  role="grid" aria-describedby="dataTable_info">
                    <form action="/nurse/eventList" modelAttribute="eventFilterPatient" method="get">
                        <div class="col-md-12">
                            <div class="text-md-end dataTables_filter">
                                <label class="form-label">
                                    <input type="search"  name="keyword" class="form-control form-control-sm" aria-controls="dataTable" placeholder="Search"  id="patientsFilter">
                                    <input type="button"  value="Cancel" class="form-control btn btn-outline-primary" aria-controls="dataTable" onclick="clearFilter()">
                                </label>
                            </div>
                        </div>
                                <table class="table my-0">
                                    <thead>
                                    <tr>
                                        <th>Patient Information</th>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>Event status</th>
                                        <th>Type of therapy</th>
                                        <th>Reason for cancellation</th>
                                        <th>Cancel</th>
                                        <th>Done</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <c:forEach var="event" items="${eventFilterPatient}">
                                    <tr id="done-${event.id}">
                                        <td>${event.patient.name} ${event.patient.surname}</td>
                                        <td>${event.eventDateTime}</td>
                                        <td>${event.time}</td>
                                        <td style="background-color: ${event.status.name() == "CANCELED" ? '#dc3545' : event.status.name() == "PLANNED" ? '#0d6efd' : event.status.name() == "DONE" ? '#198754' : 'white'}">${event.status.name()}</td>
                                        <td>${event.therapyType.name()}</td>
                                        <td>${event.reasonToCancel}</td>
                                        <td hidden>${event.patient.id}</td>
                                        <td hidden>${event.appointment.id}</td>
                                        <td><button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#status_cancel" data-done-id="${event.id}">Cancel</button></td>
                                        <td><button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#status_done" data-done-id="${event.id}">Done</button></td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td><strong>Patient Information</strong></td>
                                        <td><strong>Date</strong></td>
                                        <td><strong>Time</strong></td>
                                        <td><strong>Event status</strong></td>
                                        <td><strong>Type of therapy</strong></td>
                                        <td><strong>Reason for cancellation</strong></td>
                                        <td><strong>Cancel</strong></td>
                                        <td><strong>Done</strong></td>
                                    </tr>
                                    </tfoot>
                                </table>
                    </form>
                </div>
            </div>
            <div class="day_input form-group" style="display:none;">
                <div class="table-responsive table mt-2"  role="grid" aria-describedby="dataTable_info">
                    <table class="table my-0">
                        <thead>
                        <tr>
                            <th>Patient Information</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Event status</th>
                            <th>Type of therapy</th>
                            <th>Reason for cancellation</th>
                            <th>Cancel</th>
                            <th>Done</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach var="event" items="${eventsForDay}">
                        <tr id="done-${event.id}">
                            <td>${event.patient.name} ${event.patient.surname}</td>
                            <td>${event.eventDateTime}</td>
                            <td>${event.time}</td>
                            <td style="background-color: ${event.status.name() == "CANCELED" ? '#dc3545' : event.status.name() == "PLANNED" ? '#0d6efd' : event.status.name() == "DONE" ? '#198754' : 'white'}">${event.status.name()}</td>
                            <td>${event.therapyType.name()}</td>
                            <td>${event.reasonToCancel}</td>
                            <td hidden>${event.patient.id}</td>
                            <td hidden>${event.appointment.id}</td>
                            <td><button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#status_cancel" data-done-id="${event.id}">Cancel</button></td>
                            <td><button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#status_done" data-done-id="${event.id}">Done</button></td>
                        </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td><strong>Patient Information</strong></td>
                            <td><strong>Date</strong></td>
                            <td><strong>Time</strong></td>
                            <td><strong>Event status</strong></td>
                            <td><strong>Type of therapy</strong></td>
                            <td><strong>Reason for cancellation</strong></td>
                            <td><strong>Cancel</strong></td>
                            <td><strong>Done</strong></td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
            <div class="hour_input form-group" style="display:none;">
                <div class="table-responsive table mt-2"  role="grid" aria-describedby="dataTable_info" >
                    <table class="table my-0">
                        <thead>
                        <tr>
                            <th>Patient Information</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Event status</th>
                            <th>Type of therapy</th>
                            <th>Reason for cancellation</th>
                            <th>Cancel</th>
                            <th>Done</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach var="event" items="${eventsForHour}">
                        <tr id="done-${event.id}">
                            <td>${event.patient.name} ${event.patient.surname}</td>
                            <td>${event.eventDateTime}</td>
                            <td>${event.time}</td>
                            <td style="background-color: ${event.status.name() == "CANCELED" ? '#dc3545' : event.status.name() == "PLANNED" ? '#0d6efd' : event.status.name() == "DONE" ? '#198754' : 'white'}">${event.status.name()}</td>
                            <td>${event.therapyType.name()}</td>
                            <td>${event.reasonToCancel}</td>
                            <td hidden>${event.patient.id}</td>
                            <td hidden>${event.appointment.id}</td>
                            <td><button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#status_cancel" data-done-id="${event.id}">Cancel</button></td>
                            <td><button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#status_done" data-done-id="${event.id}">Done</button></td>
                        </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td><strong>Patient Information</strong></td>
                            <td><strong>Date</strong></td>
                            <td><strong>Time</strong></td>
                            <td><strong>Event status</strong></td>
                            <td><strong>Type of therapy</strong></td>
                            <td><strong>Reason for cancellation</strong></td>
                            <td><strong>Cancel</strong></td>
                            <td><strong>Done</strong></td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

<%--                <div class="col-md-6">--%>
<%--                    <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">--%>
<%--                        <ul class="pagination">--%>
<%--                            <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>--%>
<%--                            <li class="page-item active"><a class="page-link" href="#">1</a></li>--%>
<%--                            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--                            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--                            <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>--%>
<%--                        </ul>--%>
<%--                    </nav>--%>
<%--                </div>--%>
        </div>
    </div>
</div>

<div class="modal fade" id="status_done" tabindex="-1" role="dialog"
     aria-labelledby="allertCompleteDoneModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="allertCompleteDoneModal">Complete Done</h5>
                <button id="closeButton" type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form action="/nurse/eventList" method="post"
                      class="formWithValidation_waypoint" role="form">
                    Are you sure that the event has been executed??
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idInputDone">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden class="id field"
                                                     name="id" id="idInputDone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="patientDone">Patient</label>
                        <div class="col-sm-9"><input hidden type="text" readonly class="patient field" name="patient"
                                                     id="patientDone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="dateDone">Date</label>
                        <div class="col-sm-9"><input type="text" readonly class="date field" name="eventDateTime"
                                                     visibility: hidden id="dateDone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="timeDone">TimeDone</label>
                        <div class="col-sm-9"><input type="text" readonly class="time field" name="time" visibility:
                                                     hidden id="timeDone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="statusDone">Status</label>
                        <div class="col-sm-9"><input hidden type="text" readonly class="status field" name="status"
                                                     id="statusDone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"visibility: hidden for="therapyTypeDone">TherapyType</label>
                        <div class="col-sm-9"><input hidden type="text" readonly class="type field" name="therapyType"
                                                     id="therapyTypeDone"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="reasonToCancelDone">ReasonToCancel</label>
                        <div class="col-sm-9"><input hidden readonly class="reason field" type="text" name="reasonToCancel"
                                                     id="reasonToCancelDone"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> No</button>
                        <button type="submit" class="btn btn-primary"> Yes</button>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idPatientDone">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden class="idPatient field"
                                                     name="idPatient" id="idPatientDone"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idAppointmentDone">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden
                                                     class="idAppointment field" name="idAppointment"
                                                     id="idAppointmentDone"/></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="status_cancel" tabindex="-1" role="dialog"
     aria-labelledby="allertCompleteCancelModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="allertCompleteCancelModal">Complete Cancel</h5>
                <button id="closeButtonCancel" type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form action="/nurse/eventList" method="post"
                      class="formWithValidation_cancel" role="form">
                    Are you sure the event has been canceled?
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idInputCancel">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden class="id field"
                                                     name="id" id="idInputCancel"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"  visibility: hidden for="patientCancel">Patient</label>
                        <div class="col-sm-9"><input hidden type="text" readonly class="patient field" name="patient"
                                                     id="patientCancel"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="dateCancel">Date</label>
                        <div class="col-sm-9"><input  type="text" readonly class="date field" name="eventDateTime"
                                                     visibility: hidden id="dateCancel"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="timeCancel">TimeDone</label>
                        <div class="col-sm-9"><input type="text" readonly class="time field" name="time" visibility:
                                                     hidden id="timeCancel"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="statusCancel">Status</label>
                        <div class="col-sm-9"><input hidden type="text" readonly class="status field" name="status"
                                                     id="statusCancel"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"  visibility: hidden for="therapyTypeCancel">TherapyType</label>
                        <div class="col-sm-9"><input hidden type="text" readonly class="type field" name="therapyType"
                                                     id="therapyTypeCancel"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="reasonToCancelCancel">Reason To Cancel</label>
                        <div class="col-sm-9"><input  class="reason field" type="text" name="reasonToCancel"
                                                     id="reasonToCancelCancel" required placeholder="Reason"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"> No</button>
                        <button type="submit" class="btn btn-primary"> Yes</button>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idPatientCancel">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden class="idPatient field"
                                                     name="idPatient" id="idPatientCancel"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" visibility: hidden for="idAppointmentCancel">Id</label>
                        <div class="col-sm-9"><input type="number" readonly visibility: hidden
                                                     class="idAppointment field" name="idAppointment"
                                                     id="idAppointmentCancel"/></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    $('#selectType').change(function () {
        var selectval = $(this).val();
        if (selectval === 'AllPatientsFilter') {
            $('.patientsFilter_input').visibility.show();
            $('.patients_input').visibility.hide();
            $('.day_input').visibility.hide();
            $('.hour_input').visibility.show();
        } else if (selectval === 'AllPatients') {
            $('.patientsFilter_input').hide();
            $('.patients_input').show();
            $('.day_input').hide();
            $('.hour_input').hide();
        } else if (selectval === 'ByDay'){
            $('.patientsFilter_input').hide();
            $('.patients_input').hide();
            $('.day_input').show();
            $('.hour_input').hide();
        } else if (selectval === 'ByHour'){
            $('.patientsFilter_input').hide();
            $('.patients_input').hide();
            $('.day_input').hide();
            $('.hour_input').show();
        }
    });
</script>


<script>
    function clearFilter() {
        window.location='/nurse/eventList'
    }
</script>
<script type="text/javascript">
    var myVar=setInterval(function () {myTimer()}, 1000);
    var counter = 0;
    function myTimer() {
        var date = new Date();
        document.getElementById("currentTime").innerHTML = date.toLocaleTimeString()
    }
</script>



<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $("#patientsFilterTable").tablesorter();--%>
<%--        alert(1)--%>
<%--    });--%>
<%--    $(document).ready(function () {--%>
<%--        $("#patients").tablesorter();--%>
<%--        alert(2)--%>

<%--    });--%>
<%--    $(document).ready(function () {--%>
<%--        $("#day").tablesorter();--%>
<%--        alert(3)--%>

<%--    });--%>
<%--    $(document).ready(function () {--%>
<%--        $("#hour").tablesorter();--%>
<%--        alert(4)--%>
<%--    });--%>
<%--</script>--%>

<script>
    $("#status_done").on('show.bs.modal', function (e) {
        var eventId = $(e.relatedTarget).data('done-id');
        var cols = $('#done-' + eventId + ' td');
        var id = eventId;
        var patient = $(cols[0]).text();
        var date = $(cols[1]).text();
        var time = $(cols[2]).text();
        var status = $(cols[3]).text();
        var therapyType = $(cols[4]).text();
        var ReasonToCancel = $(cols[5]).text();
        var idPatient = $(cols[6]).text();
        var idAppointment = $(cols[7]).text();

        $('#idInputDone').val(id);
        $('#patientDone').val(patient);
        $('#dateDone').val(date);
        $('#timeDone').val(time);
        $('#statusDone').val(status);
        $('#therapyTypeDone').val(therapyType);
        $('#reasonToCancelDone').val(ReasonToCancel);
        $('#idPatientDone').val(idPatient);
        $('#idAppointmentDone').val(idAppointment);

    });
    $("#status_done").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();
    });

    var form = document.querySelector('.formWithValidation_waypoint')
    var id = form.querySelector('.id')
    var patient = form.querySelector('.patient')
    var date = form.querySelector('.date')
    var time = form.querySelector('.time')
    var status = form.querySelector('.status')
    var therapyType = form.querySelector('.type')
    var ReasonToCancel = form.querySelector('.reason')
    var idPatient = form.querySelector('.idPatient')
    var idAppointment = form.querySelector('.idAppointment')



    form.addEventListener("submit", function (event) {
        event.preventDefault()
        console.log(form)
        $.ajax({
            url: 'nurse/eventList',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: id.value,
                patient: null,
                eventDateTime: date.value,
                time: time.value,
                status: 'DONE',
                therapyType: therapyType.value,
                reasonToCancel: ReasonToCancel.value,
                idPatient: idPatient.value,
                idAppointment: idAppointment.value,
            }),
            success: function (data) {
                success(id.value);
            },
            error: function (result) {
                alert(result.responseText);
            }
        });
    })
</script>
<script>
    function success(a) {
        $("#closeButton").click()
         var i = document.getElementById(`done-`+a);
        i["children"][3].innerHTML = 'DONE'
        i["children"][3].style.backgroundColor = '#198754';
    }
</script>
<script>
    $("#status_cancel").on('show.bs.modal', function (e) {
        var eventId = $(e.relatedTarget).data('done-id');
        var cols = $('#done-' + eventId + ' td');
        var id = eventId;
        var patient = $(cols[0]).text();
        var date = $(cols[1]).text();
        var time = $(cols[2]).text();
        var status = $(cols[3]).text();
        var therapyType = $(cols[4]).text();
        var ReasonToCancel = $(cols[5]).text();
        var idPatient = $(cols[6]).text();
        var idAppointment = $(cols[7]).text();

        $('#idInputCancel').val(id);
        $('#patientCancel').val(patient);
        $('#dateCancel').val(date);
        $('#timeCancel').val(time);
        $('#statusCancel').val(status);
        $('#therapyTypeCancel').val(therapyType);
        $('#reasonToCancelCancel').val(ReasonToCancel);
        $('#idPatientCancel').val(idPatient);
        $('#idAppointmentCancel').val(idAppointment);


    });
    $("#status_cancel").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();
    });

    var form2 = document.querySelector('.formWithValidation_cancel')
    var id2 = form2.querySelector('.id')
    var patient2 = form2.querySelector('.patient')
    var date2 = form2.querySelector('.date')
    var time2 = form2.querySelector('.time')
    var status2 = form2.querySelector('.status')
    var therapyType2 = form2.querySelector('.type')
    var ReasonToCancel2 = form2.querySelector('.reason')
    var idPatient2= form2.querySelector('.idPatient')
    var idAppointment2 = form2.querySelector('.idAppointment')

    form2.addEventListener("submit", function (event) {
        event.preventDefault()
        console.log(form2)
        $.ajax({
            url: 'nurse/eventList',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: id2.value,
                patient: null,
                eventDateTime: date2.value,
                time: time2.value,
                status: 'CANCELED',
                therapyType: therapyType2.value,
                reasonToCancel: ReasonToCancel2.value,
                idPatient: idPatient2.value,
                idAppointment: idAppointment2.value,
            }),
            success: function (data) {
                successCancel(id2.value);

            },
            error: function (result) {
                alert(result.responseText);
            }
        });
    });
</script>
<script>
    function successCancel(a) {
        $("#closeButtonCancel").click()
        var i = document.getElementById(`done-`+a);
        i["children"][3].innerHTML = 'CANCELED'
        i["children"][3].style.backgroundColor = '#dc3545';
    }
</script>
</body>
</html>
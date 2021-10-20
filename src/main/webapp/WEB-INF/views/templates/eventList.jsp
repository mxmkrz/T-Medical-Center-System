<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="../static/bootstrap/js/jquery.tablesorter.js" type="text/javascript"></script>
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>


<label for="selectType"></label>

<select id="selectType">
    <option value="----">Please select a Filter Type</option>
    <option value="AllPatientsFilter">Patients Filter</option>
    <option value="AllPatients">Patients</option>
    <option value="ByDay">Day</option>
    <option value="ByHour">Hour</option>
</select>
<div class="patientsFilter_input form-group">
    <form action="/nurse/eventList" modelAttribute="eventFilterPatient" method="get">
        Filter: <input type="text" name="keyword" required>
        <input type="submit" value="Search" id="patientsFilter">

        <table class="table table-bordered table-hover">
            <thead>
            <tr class="table-active">
                <th>Date</th>
                <th>Time</th>
                <th>Patient</th>
                <th>Status</th>
                <th>Type</th>
                <th>Cancel</th>
                <th>Done</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="event" items="${eventFilterPatient}">
                <tr>
                    <td><c:out value="${event.eventDateTime}"/></td>
                    <td><c:out value="${event.time}"/></td>
                    <td><c:out value="${event.patient.name} ${event.patient.surname} "/></td>
                    <td><c:out value="${event.therapyType.name()}  "/></td>
                    <td><c:out value="${event.status.name()}  "/></td>
                    <td><c:out value="${event.reasonToCancel}  "/></td>
                    <td>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info"
                                   href="/nurse/eventList/${event.id}/changeToCancel ">Cancel</a>
                            </div>
                        </div>

                    </td>
                    <td>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info"
                                   href="/nurse/eventList/${event.id}/changeToDone ">Done</a>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>

<div class="patients_input form-group" style="display:none;">
    <form:form action="/nurse/eventList" method="post"
               class="formWithValidation3" role="form" modelattribute="events">
        <table class="table table-bordered table-hover">
            <thead>
            <tr class="table-active">
                <th hidden>Id</th>
                <th>Date</th>
                <th>Time</th>
                <th>Patient</th>
                <th>Status</th>
                <th>Type</th>
                <th>Cancel</th>
                <th>Done</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="event" items="${events}">

                <tr>
                    <td><hidden value="${event.id}"></hidden></td>
                    <td><c:out value="${event.eventDateTime}"/></td>
                    <td><c:out value="${event.time}"/></td>
                    <td><c:out value="${event.patient.name} ${event.patient.surname} "/></td>
                    <td><c:out value="${event.therapyType.name()}  "/></td>
                    <td><c:out value="${event.status.name()}  "/></td>
                    <td><c:out value="${event.reasonToCancel}  "/></td>
                    <td><c:out value="${event.reasonToCancel}  "/></td>
                    <td>
                    </td>
                    <td>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info"
                                   href="/nurse/eventList/${event.id}/changeToCancel ">Cancel</a>
                            </div>
                        </div>
                    </td>
                    <td>
                        <button type="button"  class="btn btn-success" id="doneInput"
                                data-toggle="modal" data-target="#edit_status"
                                data-waypoint-id="${event.id}"> Done
                        </button>
                        <script>
                            $("#edit_status").on('show.bs.modal', function (e) {
                                var status = $(e.relatedTarget).data('modal');
                                $('#doneInput').val(status);
                            });
                            $("#edit_status").on('hidden.bs.modal', function () {
                                var form = $(this).find('form');
                                form[0].reset();
                            });

                            var form = document.querySelector('.formWithValidation3')
                            form.addEventListener("submit", function (event) {
                                event.preventDefault()

                                $.ajax({
                                    url: '/nurse/eventList',
                                    datatype: 'json',
                                    type: "POST",
                                    dataType: 'JSON',
                                    data: JSON.stringify({
                                        id: ${event.id},
                                        patient: '${event.patient}',
                                        eventDateTime: '${event.eventDateTime}',
                                        status: 'DONE',
                                        time: '${event.time}',
                                        therapyType: '${event.therapyType}',
                                        reasonToCancel: '${event.reasonToCancel}',
                                    }),
                                    success: function (data) {
                                        window.location.reload();
                                    },
                                    error: function (result) {
                                        alert("error" + result.responseText);
                                    }
                                });
                            });
                        </script>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form:form>
</div>

<div class="day_input form-group" style="display:none;">
    <table class="table table-bordered table-hover" id="day">
        <thead>
        <tr class="table-active">
            <th>Date</th>
            <th>Time</th>
            <th>Patient</th>
            <th>Status</th>
            <th>Type</th>
            <th>Cancel</th>
            <th>Done</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="event" items="${eventsForDay}">
            <tr>
                <td><c:out value="${event.eventDateTime}"/></td>
                <td><c:out value="${event.time}"/></td>
                <td><c:out value="${event.patient.name} ${event.patient.surname} "/></td>
                <td><c:out value="${event.therapyType.name()}  "/></td>
                <td><c:out value="${event.status.name()}  "/></td>
                <td><c:out value="${event.reasonToCancel}  "/></td>
                <td>
                    <div class="row">
                        <div class="col-sm-12">
                            <a class="btn btn-info"
                               href="/nurse/eventList/${event.id}/changeToCancel ">Cancel</a>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="row">
                        <div class="col-sm-12">
                            <a class="btn btn-info"
                               href="/nurse/eventList/${event.id}/changeToDone ">Done</a>
                        </div>
                    </div>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<div class="hour_input form-group" style="display:none;">
    <table class="table table-bordered table-hover" id="hour">
        <thead>
        <tr class="table-active">
            <th>Date</th>
            <th>Time</th>
            <th>Patient</th>
            <th>Status</th>
            <th>Type</th>
            <th>Cancel</th>
            <th>Done</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="event" items="${eventsForHour}">
            <tr>
                <td><c:out value="${event.eventDateTime}"/></td>
                <td><c:out value="${event.time}"/></td>
                <td><c:out value="${event.patient.name} ${event.patient.surname} "/></td>
                <td><c:out value="${event.therapyType.name()}  "/></td>
                <td><c:out value="${event.status.name()}  "/></td>
                <td><c:out value="${event.reasonToCancel}  "/></td>
                <td>
                    <div class="row">
                        <div class="col-sm-12">
                            <a class="btn btn-info"
                               href="/nurse/eventList/${event.id}/changeToCancel ">Cancel</a>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="row">
                        <div class="col-sm-12">
                            <a class="btn btn-info"
                               href="/nurse/eventList/${event.id}/changeToDone ">Done</a>
                        </div>
                    </div>


                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script>
    $('#selectType').change(function () {
        var selectval = $(this).val();
        if (selectval === 'AllPatientsFilter') {
            $('.patientsFilter_input').show();
        } else {
            $('.patientsFilter_input').hide();
        }
    });
</script>
<script>
    $('#selectType').change(function () {
        var selectval = $(this).val();
        if (selectval === 'AllPatients') {
            $('.patients_input').show();
        } else {
            $('.patients_input').hide();
        }
    });
</script>
<script>
    $('#selectType').change(function () {
        var selectval = $(this).val();
        if (selectval === 'ByDay') {
            $('.day_input').show();
        } else {
            $('.day_input').hide();
        }
    });
</script>
</script>
<script>
    $('#selectType').change(function () {
        var selectval = $(this).val();
        if (selectval === 'ByHour') {
            $('.hour_input').show();
        } else {
            $('.hour_input').hide();
        }
    });
</script>


<form action="/logout" method="post">
    <input type="submit" class="btn btn-danger" value="Logout"/>
</form>
<script>
    $(document).ready(function () {
        $("#patientsFilter").tablesorter();
    });
    $(document).ready(function () {
        $("#patients").tablesorter();
    });
    $(document).ready(function () {
        $("#day").tablesorter();
    });
    $(document).ready(function () {
        $("#hour").tablesorter();
    });
</script>


</body>

</html>
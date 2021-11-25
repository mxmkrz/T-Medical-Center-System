<%@ page import="java.util.Date" %>
<%@ page import="com.t_systems.t_medical_center_system.entity.Appointment" %>
<%@ page import="com.t_systems.t_medical_center_system.dto.AppointmentDto" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <base href="\">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/JNKKKK/MoreToggles.css@0.2.1/output/moretoggles.min.css">
    <link rel="stylesheet" href="../static/bootstrap/css/main.css">
    <title></title>
    <script src="../static/bootstrap/js/Multi-Select-Dropdown-by-Jigar-Mistry.js"></script>
    <link rel="stylesheet" href="../static/css/Multi-Select-Dropdown-by-Jigar-Mistry.css">
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
        .align-center {
            text-align: center;
        }
    </style>

</head>
<body style="background-image: url('../static/images/1613688120_48-p-fon-dlya-prezentatsii-doktor-51.jpg')">
    <form:form action="/doctor/profile/${patient.id}/appointment" method="post"
               modelAttribute="appointmentNew">
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/doctor/profile/${patient.id}">Back</a>
        <a class="navbar-brand" href="/logout"><strong>Logout</strong></a>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col align-center">
            <h3>Please choose a period</h3>
        </div>
        <div class="col">
            <div class="col-xs-6">
                <div class="form-group" id="getWeekDay">
                    <div class="input-group date" id="datetimepicker7">
                        <input type="text" name="startOfData" class="form-control " placeholder="Start Date"/>
                        <form:errors path="startOfData" cssClass="error"/>
                        <span class="input-group-addon">
                    <em class="glyphicon glyphicon-calendar"></em></span>
                    </div>
                </div>
            </div>
        </div>


        <div class="col">
            <div class="col-xs-6">
                <div class="form-group" id="getWeekDay2">
                    <div class="input-group date" id="datetimepicker8">
                        <input type="text" name="endOfData" class="form-control" id="btn1" placeholder="End Date"/>
                        <form:errors path="endOfData" cssClass="error"/>
                        <span class="input-group-addon">
                    <em class="glyphicon glyphicon-calendar"></em>
                </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="align-center">
                <h3>Please choose a weekDay</h3>
            </div>
        </div>
        <div class="col">
            <div class="container align-center">
                <div class="mt-square3d" id=0day style="font-size:10px;">
                    <h3><strong>Sunday</strong></h3>
                    <input id="1" name="sunday" type="checkbox"/>
                    <label for="1"></label>
                </div>

                <div class="mt-square3d" id=1day style="font-size:10px;">
                    <h3><strong>Monday</strong></h3>
                    <input id="2" name="monday" type="checkbox"/>
                    <label for="2"></label>
                </div>

                <div class="mt-square3d" id=2day style="font-size:10px;">
                    <h3><strong>Tuesday</strong></h3>
                    <input id="3" name="tuesday" type="checkbox"/>
                    <label for="3"></label>
                </div>

                <div class="mt-square3d" id=3day style="font-size:10px;">
                    <h3><strong>Wednesday</strong></h3>
                    <input id="4" name="wednesday" type="checkbox"/>
                    <label for="4"></label>
                </div>

                <div class="mt-square3d" id=4day style="font-size:10px;">
                    <h3><strong>Thursday</strong></h3>
                    <input id="5" name="thursday" type="checkbox"/>
                    <label for="5"></label>
                </div>

                <div class="mt-square3d" id=5day style="font-size:10px;">
                    <h3><strong>Friday</strong></h3>
                    <input id="6" name="friday" type="checkbox"/>
                    <label for="6"></label>
                </div>

                <div class="mt-square3d" id=6day style="font-size:10px;">
                    <h3><strong>Saturday</strong></h3>
                    <input id="7" name="saturday" type="checkbox"/>
                    <label for="7"></label>
                </div>
            </div>
        </div>
        <div class="align-center">
            <form:errors path="saturday" cssClass="error"/>
        </div>
        <div class="col">
            <div class="align-center">
                <h3>Please choose a time</h3>
            </div>
            <div class="col">
                <form:select id="dates-field2" class="multiselect-ui form-control" multiple="multiple" path="time">
                    <form:option value="0">9:00 - 10:00</form:option>
                    <form:option value="1">10:00 - 11:00</form:option>
                    <form:option value="2">11:00 - 12:00</form:option>
                    <form:option value="3">12:00 - 13:00</form:option>
                    <form:option value="4">13:00 - 14:00</form:option>
                    <form:option value="5">14:00 - 15:00</form:option>
                    <form:option value="6">15:00 - 16:00</form:option>
                    <form:option value="7">16:00 - 17:00</form:option>
                    <form:option value="8">17:00 - 18:00</form:option>
                    <form:option value="9">18:00 - 19:00</form:option>
                    <form:option value="10">19:00 - 20:00</form:option>
                    <form:option value="11">20:00 - 21:00</form:option>
                </form:select>
                <form:errors path="time" cssClass="error"/>
            </div>
            <div class="col align-center">
                    <h3>Please choose the type of therapy</h3>
            </div>
            <div class="col">

                <form:select path="type" class="multiselect-ui form-control" id="selectType">
                    <form:option value="">Please select a Therapy Type</form:option>
                    <form:option value="PROCEDURE">PROCEDURE</form:option>
                    <form:option value="DRUG">DRUG</form:option>
                </form:select>
                <form:errors path="type" cssClass="error"/>
                <form:errors path="info" cssClass="error"/>
                <div class="procId_input input-group flex-nowrap col-xs-12" style="display:none;">
                    <input type="text" class="form-control" id="procId" name="info" aria-describedby="addon-wrapping"
                           placeholder="Info of Procedure"/>
                </div>

                <div class="drugId_input input-group flex-nowrap col-xs-12" style="display:none;">
                    <input type="number" class="form-control" id="drugId" name="dose" aria-describedby="addon-wrapping"
                           placeholder="Amount dose" min="1"/>
                </div>
                <div class="drugInfoId_input input-group flex-nowrap col-xs-12" style="display:none;">
                    <input type="text" class="form-control" id="drugInfoId" name="infoDrugs"
                           aria-describedby="addon-wrapping" placeholder="infoDrugs"/>
                </div>

            </div>

        </div>
        <div class="row">
            <div class="col">
                <button class="btn btn-primary col-xs-12" type="submit">Make an appointment</button>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <button class="btn btn-danger col-xs-12" type="reset">Cancel</button>
            </div>
        </div>
    </div>


    </form:form>

    </p>


    <script>
        function checkCheckBox() {
            $("#getWeekDay").on("dp.change", function (e) {


                $("#getWeekDay2").on("dp.change", function (e) {
                    var dateFirstCalendar = $('#getWeekDay input').val();
                    var currentWeekDay = new Date(dateFirstCalendar).getDay();

                    var dateFirstCalendar2 = $('#getWeekDay2 input').val();
                    var currentWeekDay2 = new Date(dateFirstCalendar2).getDay();

                    var period = getCountDay();
                    if (period < 7) {
                        if (currentWeekDay < currentWeekDay2) {
                            if (0 === currentWeekDay || 0 === currentWeekDay2) {
                                $('#0day').show()
                            } else {
                                $('#0day').hide()
                            }
                            if (1 === currentWeekDay || (currentWeekDay <= 1 && 1 <= currentWeekDay2)) {
                                $('#1day').show()
                            } else {
                                $('#1day').hide()
                            }
                            if (2 === currentWeekDay || (currentWeekDay <= 2 && 2 <= currentWeekDay2)) {
                                $('#2day').show()
                            } else {
                                $('#2day').hide()
                            }
                            if (3 === currentWeekDay || (currentWeekDay <= 3 && 3 <= currentWeekDay2)) {
                                $('#3day').show()
                            } else {
                                $('#3day').hide()
                            }
                            if (4 === currentWeekDay || (currentWeekDay <= 4 && 4 <= currentWeekDay2)) {
                                $('#4day').show()
                            } else {
                                $('#4day').hide()
                            }
                            if (5 === currentWeekDay || (currentWeekDay <= 5 && 5 <= currentWeekDay2)) {
                                $('#5day').show()
                            } else {
                                $('#5day').hide()
                            }
                            if (6 === currentWeekDay || (currentWeekDay <= 6 && 6 <= currentWeekDay2)) {
                                $('#6day').show()
                            } else {
                                $('#6day').hide()
                            }
                        }
                    }
                });
            });
        }

        window.onload = checkCheckBox();

    </script>

    <script>
        $('#selectType').change(function () {
            var selectval = $(this).val();
            if (selectval === 'PROCEDURE') {
                $('.procId_input').show();
            } else {
                $('.procId_input').hide();
            }
        });
    </script>
    <script>
        $('#selectType').change(function () {
            var selectval = $(this).val();
            if (selectval === 'DRUG') {
                $('.drugId_input').show();
            } else {
                $('.drugId_input').hide();
            }
        });
    </script>
    <script>
        $('#selectType').change(function () {
            var selectval = $(this).val();
            if (selectval === 'DRUG') {
                $('.drugInfoId_input').show();
                $('.drugInfoId_input').prop('required', true);
            } else {
                $('.drugInfoId_input').hide();
                $('.drugInfoId_input').prop('required', false);
            }
        });
    </script>

    <script type="text/javascript">
        $(function () {
            $("#datetimepicker7").datetimepicker();
            $("#datetimepicker8").datetimepicker({
                useCurrent: false
            });
            $("#datetimepicker7").on("dp.change", function (e) {
                $('#datetimepicker8').data("DateTimePicker").minDate(e.date);
            });
            $("#datetimepicker8").on("dp.change", function (e) {
                $('#datetimepicker7').data("DateTimePicker").maxDate(e.date);
            });


        });
    </script>


    <script>
        function getCountDay() {
            let welcomeData = moment($('#datetimepicker7').data("DateTimePicker").date());
            welcomeData.set({hour: 0, minute: 0, second: 0, millisecond: 0});
            let perenosData = moment($('#datetimepicker8').data("DateTimePicker").date());
            perenosData.set({hour: 0, minute: 0, second: 0, millisecond: 0})
            return perenosData.diff(welcomeData, 'days');
        }
    </script>


</body>
</html>

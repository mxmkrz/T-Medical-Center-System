
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

    <!-- Optional theme -->
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
</head>
<body>
<form:form action="/doctor/profile/${patient.id}/appointment/${appointmentNew.id}" method="post" modelAttribute="appointmentNew">
    <form:hidden path="id"/>
    <div class="row">
        <div class="col-xs-6">
            <div class="form-group" id="getWeekDay">
                <div class="input-group date" id="datetimepicker7">
                    <input type="text" name="startOfData" class="form-control "/>
                    <span class="input-group-addon">
                    <i class="glyphicon glyphicon-calendar"></i>
                </span>
                </div>
            </div>
        </div>
        <script>
            function checkCheckBox() {
                $("#getWeekDay").on("dp.change", function (e) {


                    $("#getWeekDay2").on("dp.change", function (e) {
                        var dateFirstCalendar = $('#getWeekDay input').val();
                        var currentWeekDay = new Date(dateFirstCalendar).getDay();

                        var dateFirstCalendar2 = $('#getWeekDay2 input').val();
                        var currentWeekDay2 = new Date(dateFirstCalendar2).getDay();

                        var period = getCountDay();
                        // alert(period + " period")
                        // alert(currentWeekDay + " week day 1 cal")
                        // alert(currentWeekDay2 + " week day 2 cal")
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
        <div class="col-xs-6">
            <div class="form-group" id="getWeekDay2">
                <div class="input-group date" id="datetimepicker8">
                    <input type="text" name="endOfData" class="form-control" id="btn1"/>
                    <span class="input-group-addon">
                    <i class="glyphicon glyphicon-calendar"></i>
                </span>
                </div>
            </div>
        </div>


    </div>


    <div class="container">
        <div class="mt-square3d" id=0day style="font-size:10px;">
            <h3>Sunday</h3>
            <input id="1" name="sunday" type="checkbox"/>
            <label for="1"></label>
        </div>

        <div class="mt-square3d" id=1day style="font-size:10px;">
            <h3>Monday</h3>
            <input id="2" name="monday" type="checkbox"/>
            <label for="2"></label>
        </div>

        <div class="mt-square3d" id=2day style="font-size:10px;">
            <h3>Tuesday</h3>
            <input id="3" name="tuesday" type="checkbox"/>
            <label for="3"></label>
        </div>

        <div class="mt-square3d" id=3day style="font-size:10px;">
            <h3>Wednesday</h3>
            <input id="4" name="wednesday" type="checkbox"/>
            <label for="4"></label>
        </div>

        <div class="mt-square3d" id=4day style="font-size:10px;">
            <h3>Thursday</h3>
            <input id="5" name="thursday" type="checkbox"/>
            <label for="5"></label>
        </div>

        <div class="mt-square3d" id=5day style="font-size:10px;">
            <h3>Friday</h3>
            <input id="6" name="friday" type="checkbox"/>
            <label for="6"></label>
        </div>

        <div class="mt-square3d" id=6day style="font-size:10px;">
            <h3>Saturday</h3>
            <input id="7" name="saturday" type="checkbox"/>
            <label for="7"></label>
        </div>
    </div>



    <form:select id="dates-field2" class="multiselect-ui form-control" multiple="multiple" path="time">
        <c:forEach items="${appointmentNew.time}" var="timeName" varStatus="loop">
            <form:option value="${loop.index}" >${timeName} </form:option>
        </c:forEach>
    </form:select>






    <form:select path="type" id="selectType">
        <form:option value="----">Please select a Therapy Type</form:option>
        <form:option value="PROCEDURE">PROCEDURE</form:option>
        <form:option value="DRUG" >DRUG</form:option>
    </form:select>
    <div class="procId_input form-group" style="display:none;">
        <input type="text" id="procId" name="info"  placeholder="Info of Procedure"/>
    </div>
    <div class="drugId_input form-group" style="display:none;">
        <input type="number" id="drugId" name="dose"  placeholder="Amount dose" min="1"/>
    </div>
    <div class="drugInfoId_input form-group" style="display:none;">
        <input type="text" id="drugInfoId" name="infoDrugs"  placeholder="infoDrugs"/>
    </div>
    <script>
        $('#selectType').change(function(){
            var selectval = $(this).val(); // Получим значение из select со значением #participation
            if( selectval ==='PROCEDURE') {
                $('.procId_input').show();
            } else {
                $('.procId_input').hide();
            }
        });
    </script>
    <script>
        $('#selectType').change(function(){
            var selectval = $(this).val(); // Получим значение из select со значением #participation
            if( selectval ==='DRUG') {
                $('.drugId_input').show();
            } else {
                $('.drugId_input').hide();
            }
        });
    </script>
    </script>
    <script>
        $('#selectType').change(function(){
            var selectval = $(this).val(); // Получим значение из select со значением #participation
            if( selectval ==='DRUG') {
                $('.drugInfoId_input').show();
            } else {
                $('.drugInfoId_input').hide();
            }
        });
    </script>
    <button class="btn btn-primary" type="submit">ADD</button>
    <script type="text/javascript">
        $(function () {
            // инициализация datetimepicker7 и datetimepicker8
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

        $(function () {
            $('#datetimepicker4').datetimepicker({
                locale: 'ru'
            });
        });

    </script>
    <script>
        function getCountDay() {
            let welcomeData = moment($('#datetimepicker7').data("DateTimePicker").date());
            welcomeData.set({hour: 0, minute: 0, second: 0, millisecond: 0});
            // получаем дату из 2 календаря
            let perenosData = moment($('#datetimepicker8').data("DateTimePicker").date());
            perenosData.set({hour: 0, minute: 0, second: 0, millisecond: 0})
            // получаем разницу в днях
            // document.getElementById('key').innerHTML = diffDays;
            // alert(diffDays)
            return perenosData.diff(welcomeData, 'days');
        }
    </script>


</form:form>
</body>
</html>

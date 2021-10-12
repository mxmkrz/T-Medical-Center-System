<%@ page import="com.t_systems.t_medical_center_system.dto.AppointmentListWrapper" %>
<%@ page import="java.util.ArrayList" %>
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

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    <title></title>
    <title></title></head>
<body>
<div class="row">
    <div class="col-xs-6">
        <div class="form-group">

            <div class="input-group date" id="datetimepicker7">
                <input type="text" class="form-control"/>
                <span class="input-group-addon">
                    <i class="glyphicon glyphicon-calendar"></i>
                </span>
            </div>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <div class="input-group date" id="datetimepicker8">
                <input type="text" class="form-control"/>
                <span class="input-group-addon">
                    <i class="glyphicon glyphicon-calendar"></i>
                </span>
            </div>
        </div>
    </div>
</div>
<p id='key'></p>
<button onclick="getCountDay()">Press</button>
<form:form action="/patient/profile/${patientId.id}/appointment" method="post"
           modelAttribute="appointmentListWrapper" role="form">



    <c:forEach varStatus="us" var="appointmentDtos" items="${appointmentListWrapper.appointmentDtoArrayList}" >
        <td><form:input type="hidden" path="appointmentDtoArrayList"/>${appointmentDtos.startData}</td>

<%--    <div class="mb-3"><input class="form-control" type="text" name="type" placeholder="Type"></div>--%>
<%--    <div class="mb-3"><input class="form-control" type="number" name="dose" placeholder="dose"></div>--%>
<%--    <div class="mb-3"><input class="form-control" type="text" name="startData" placeholder="startData"></div>--%>
<%--    <div class="mb-3"><input class="form-control" type="text" name="endData" placeholder="endData"></div>--%>
<%--

    <button class="btn btn-primary d-block w-100" type="submit">Sign Up</button>--%>


        <label>
            <input type="text"  name="appointmentDtos[${us.index}]">
        </label>

    </c:forEach>
    </form:form>
<%--        <td><form:input path="appointmentDtoArrayList" /></td>--%>


<%--                ${appointmentDtos}--%>
<%--                &lt;%&ndash;                    ${appointmentDtos}&ndash;%&gt;--%>
<%--                &lt;%&ndash;                    ${us.index}&ndash;%&gt;--%>

<%--    <td><form:input path="appointmentDtoArrayList[${us}].startData" /></td>&ndash;%&gt;--%>

<%--                <c:forEach items="appointmentDtos" var="ap">--%>
<%--                    <td>--%>
<%--                        ${ap}--%>
<%--                    </td>--%>
<%--                </c:forEach>--%>
<%--                                <td><form:input path="appointmentDtoArrayList[${us}].startData" /></td>--%>
<%--                <td><label>--%>
<%--                    <input name="appointmentDtoArrayList[${us.index}].type" value="${appoint}"/>--%>
<%--                </label></td>--%>
<%--                <div class="mb-3"><input class="form-control" type="text" name="appointmentDtoArrayList[${us.index}].type" value="${appoint.type}"></div>--%>

<%--                <td><input name="appointmentDtoArrayList[${us.index}].startData" value="${appoint.startData}"/></td>--%>
<%--                <td><input name="appointmentDtoArrayList[${us.index}].endData"
<%--                  &ndash;%&gt;  <button class="btn btn-primary d-block w-100" type="submit">Sign Up</button> value="${appointmentDtos.startData}"/></td>--%>
<%--        </tr>--%>

<%--    </c:forEach>--%>

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

</script>
<script>
    function getCountDay() {
        let welcomeData = moment($('#datetimepicker7').data("DateTimePicker").date());
        welcomeData.set({hour: 0, minute: 0, second: 0, millisecond: 0});
        // получаем дату из 2 календаря
        let perenosData = moment($('#datetimepicker8').data("DateTimePicker").date());
        perenosData.set({hour: 0, minute: 0, second: 0, millisecond: 0})
        // получаем разницу в днях
        const diffDays = perenosData.diff(welcomeData, 'days');
        document.getElementById('key').innerHTML = diffDays;
        alert(diffDays)
    }
</script>
</body>
</html>

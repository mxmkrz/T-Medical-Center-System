<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="/patient/profile/{id}/appointment" method="post" modelAttribute="appointment"
           class="formWithValidation"
           role="form">


    <div class="form-group row">
        <label class="col-2 col-form-label">Type</label>
        <div class="col-5">
            <div id="treatmentTypeContainer">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="treatmentType" id="inlineRadio1"
                           value="PROCEDURE"
                        ${appointment.type == "PROCEDURE"}>
                    <label class="form-check-label" for="inlineRadio1">Procedure</label>
                </div>
                <div class="col-2 form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="treatmentType" id="inlineRadio2"
                           value="MEDICINE"
                        ${appointment.type == "DRUF"}>
                    <label class="form-check-label" for="inlineRadio2">Drug</label>
                </div>
            </div>
        </div>
    </div>

    <div id="dosageGroup" class="form-group row">
        <label for="dosage" class="col-sm-2 col-form-label">Dosage</label>
        <div class="col-sm-1">
            <input type="number" id="dosage" class="form-control" placeholder="Dosage" autocomplete="off"
                   name="dosage" value="${appointment.dose}" step="any" min="0" required>
        </div>
        <label for="dosageInfo" class="col-sm-1 col-form-label">info</label>
        <div class="col-sm-6">
            <input type="text" id="dosageInfo" class="form-control" placeholder="Dosage info" autocomplete="off"
                   name="dosageInfo" value="${appointment.dose}">
        </div>
    </div>



</form:form>

<script>


</script>
</body>
</html>

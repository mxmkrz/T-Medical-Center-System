<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="\">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <link rel="stylesheet" href="../static/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="../static/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="../static/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="../static/css/Ludens-Users---3-Profile.css">
    <link rel="stylesheet" href="../static/css/styles.css">
    <style>
        .note {
            text-align: center;
            height: 80px;
            background: -webkit-linear-gradient(left, #0072ff, #8811c5);
            color: #fff;
            font-weight: bold;
            line-height: 80px;
        }

        .form-content {
            padding: 5%;
            border: 3px solid #ced4da;
            margin-bottom: 2%;
        }

        .form-control {
            border-radius: 1.5rem;
        }

        .btnSubmit {
            border: none;
            border-radius: 1.5rem;
            padding: 1%;
            width: 20%;
            cursor: pointer;
            background: #0062cc;
            color: #fff;
        }
    </style>

    <style>
        body {
            background: url('../static/images/1619575509_35-phonoteka_org-p-fon-dlya-prezentatsii-vrach-35.jpg');
        }
    </style>
</head>

<body>
<p>
<div class="row" style="margin-right: 0px;margin-left: 0px;">
    <div class="col-md-12" style="margin-bottom: 25px;padding-left: 75px;font-size: 21px;margin-top: 73px;">
        <a class="anone" href="/doctor/patients"><i
                class="fa fa-long-arrow-left"></i><span>&nbsp;Back</span></a>
    </div>
    <div class="col-md-12" style="margin-bottom: 25px;padding-left: 75px;font-size: 21px;margin-top: 73px;">
        <a class="anone" href="/logout"><i class="fa fa-long-arrow-left"></i><strong>&nbsp; Logout</strong></a>
    </div>
</div>
<div class="container register-form">
    <div class="form">
        <div class="note">
            <p>Form for adding a new patient.</p>
        </div>

        <div class="form-content">
            <div class="row">
                <form:form action="/doctor/add/" method="post" modelAttribute="patient"
                           role="form">
                <div class="col-md-6">
                    <div class="form-group">
                        <form:input type="text" class="form-control" placeholder="Name" path="name"/>
                        <form:errors path="name"/>
                    </div>
                    <div class="form-group">
                        <form:input type="text" class="form-control" placeholder="Surname" path="surname"/>
                        <form:errors path="surname"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <form:input type="text" class="form-control" placeholder="Diagnosis" path="diagnosis"/>
                        <form:errors path="diagnosis"/>
                    </div>
                    <div class="form-group">
                        <form:input type="text" class="form-control" placeholder="Insurance Number" path="insuranceNumber"/>
                        <form:errors path="insuranceNumber"/>
                    </div>
                </div>
                    <div hidden class="col-md-6">
                        <div class="col">
                            <label>
                                <form:select class="form-selected" path="status" >
                                    <form:option value="PATIENT">PATIENT</form:option>
                                    <form:option value="DISCHARGED">DISCHARGED</form:option>
                                </form:select>
                                <form:errors path="status"/>
                            </label>
                        </div>
                    </div>
                    <button type="submit" class="btnSubmit">Add</button>
            </div>

            </form:form>
        </div>
    </div>
</div>

<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</body>
<%--<script>--%>
<%--    $("#create_status").on('show.bs.modal', function (e) {--%>
<%--        var status = $(e.relatedTarget).data('patient-status');--%>
<%--        $('#statusInput').val(status);--%>
<%--    });--%>
<%--    $("#create_status").on('hidden.bs.modal', function () {--%>
<%--        var form = $(this).find('form');--%>
<%--        form[0].reset();--%>

<%--    });--%>
<%--    var form = document.querySelector('.formWithValidation1')--%>
<%--    var status = form.querySelector('.status')--%>

<%--    form.addEventListener("submit", function (event) {--%>
<%--        event.preventDefault()--%>


<%--        $.ajax({--%>
<%--            url: '/doctor/add',--%>
<%--            datatype: 'json',--%>
<%--            type: "POST",--%>
<%--            dataType: 'JSON',--%>
<%--            data: JSON.stringify({--%>
<%--                id: ${patient.id},--%>
<%--                name: '${patient.name}',--%>
<%--                surname: "${patient.surname}",--%>
<%--                diagnosis: '${patient.diagnosis}',--%>
<%--                insuranceNumber: ${patient.insuranceNumber},--%>
<%--                status: status.value,--%>
<%--            }),--%>
<%--            success: function (response) {--%>
<%--                if (response.redirect) {--%>
<%--                    window.location.href = response.redirect;--%>
<%--                }--%>
<%--            },--%>
<%--            error: function (result) {--%>
<%--                alert("error" + result.responseText);--%>
<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</html>


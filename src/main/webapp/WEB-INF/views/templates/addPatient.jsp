<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/styles.css">
</head>

<body>
<section class="register-photo">
    <div class="form-container">
        <form:form action="/doctor/add/" method="post" modelAttribute="patient" class="formWithValidation1" role="form">
            <h2 class="text-center"><strong>Create</strong> patient.</h2>
            <div class="mb-3"><input class="form-control" type="text" name="name" placeholder="Name"></div>
            <div class="mb-3"><input class="form-control" type="text" name="surname" placeholder="Surname"></div>
            <div class="mb-3"><input class="form-control" type="text" name="diagnosis" placeholder="Diagnosis"></div>
            <div class="mb-3"><input class="form-control" type="number" name="insuranceNumber" placeholder="InsuranceNumber"></div>
            <div class="form-floating">
                <select class="form-select" name="status" id="statusInput" aria-label="Floating label select example">
                    <option selected>Status</option>
                    <option value="PATIENT">PATIENT</option>
                    <option value="DISCHARGED">DISCHARGED</option>
                </select>
            </div>
            <div class="mb-3"></div>
            <div class="mb-3">
                <button class="btn btn-primary d-block w-100" type="submit">Sign Up</button>
            </div>
        </form:form>
    </div>
</section>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</body>
<script>
    $("#create_status").on('show.bs.modal', function (e) {
        var status = $(e.relatedTarget).data('patient-status');
        $('#statusInput').val(status);
    });
    $("#create_status").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();

    });
    var form = document.querySelector('.formWithValidation1')
    var status = form.querySelector('.status')

    form.addEventListener("submit", function (event) {
        event.preventDefault()


        $.ajax({
            url: '/doctor/add',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: ${patient.id},
                name: '${patient.name}',
                surname: "${patient.surname}",
                diagnosis: '${patient.diagnosis}',
                insuranceNumber: ${patient.insuranceNumber},
                status: status.value,
            }),
            success: function (response) {
                if (response.redirect) {
                    window.location.href = response.redirect;
                }
            },
            error: function (result) {
                alert("error" + result.responseText);
            }
        });
    });
</script>
</html>
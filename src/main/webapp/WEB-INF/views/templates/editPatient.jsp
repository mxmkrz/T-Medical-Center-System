<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <base href="\">
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Profile-Edit-Form-1.css">
    <link rel="stylesheet" href="../static/css/Profile-Edit-Form.css">
    <link rel="stylesheet" href="../static/css/styles.css">
</head>

<body>

<div class="container profile profile-view" id="profile">
    <div class="row">
        <div class="col-md-12 alert-col relative">
            <div class="alert alert-info alert-dismissible absolue center" role="alert"><button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button><span>Profile save with success</span></div>
        </div>
    </div>
    <form>
        <div class="row profile-row">
            <div class="col-md-4 relative">
                <div class="avatar">
                    <div class="avatar-bg center"></div>
                </div><input class="form-control form-control" type="file" name="avatar-file">
            </div>
            <div class="col-md-8">
                <h1>Edit Profile </h1>
                <hr>
                <form:form action="patient/edit" method="post" modelAttribute="profile" class="formWithValidation3"
                           role="form">
                    <form:hidden path="id"/>
                <div class="row">
                    <div class="col-sm-12 col-md-6">
                        <div class="form-group mb-3"><label class="form-label">Name </label><input class="form-control" type="text" name="name"></div>
                    </div>
                    <div class="col-sm-12 col-md-6">
                        <div class="form-group mb-3"><label class="form-label">Surname </label><input class="form-control" type="text" name="surname"></div>
                    </div>
                </div>
                <div class="form-group mb-3"><label class="form-label">Diagnosis </label><input class="form-control" type="text" autocomplete="off" required="" name="diagnosis"></div>
                <div class="row">
                    <div class="col-sm-12 col-md-6">
                        <div class="form-group mb-3"><label class="form-label">Insurance Number </label><input class="form-control" type="text" name="insuranceNumber" autocomplete="off" required=""></div>
                    </div>
                    <div class="col-sm-12 col-md-6">
                        <div class="form-floating">
                            <select class="form-select" name="status" id="statusEditInput" aria-label="Floating label select example">
                                <option selected>Status</option>
                                <option value="PATIENT">PATIENT</option>
                                <option value="DISCHARGED">DISCHARGED</option>
                            </select>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-12 content-right"><button class="btn btn-primary form-btn" type="submit">SAVE </button>
                        <button class="btn btn-danger form-btn" type="reset">CANCEL </button></div>
                </div>
                </form:form>
            </div>
        </div>
    </form>
</div>

<script src="../static/bootstrap/js/bootstrap.min.js"></script>
<script src="../static/bootstrap/js/Profile-Edit-Form.js"></script>
</body>
<script>
    $("#edit_status").on('show.bs.modal', function (e) {
        var status = $(e.relatedTarget).data('patient-status');
        $('#statusEditInput').val(status);
    });
    $("#edit_status").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();

    });
    var form = document.querySelector('.formWithValidation3')
    var status = form.querySelector('.status')

    form.addEventListener("submit", function (event) {
        event.preventDefault()


        $.ajax({
            url: '/patient/edit',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: ${profile.id},
                name: '${profile.name}',
                surname: "${profile.surname}",
                diagnosis: '${profile.diagnosis}',
                insuranceNumber: ${profile.insuranceNumber},
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
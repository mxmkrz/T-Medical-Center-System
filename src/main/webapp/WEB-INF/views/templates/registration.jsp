<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add Doctor</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="../static/css/styles.css">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

</head>

<body>
<section class="register-photo">
    <div class="form-container">
        <form:form action="/registration" method="post" modelAttribute="staff" class="formWithValidation2"
                   role="form">
            <h2 class="text-center"><strong>Create</strong> an account.</h2>
            <div class="mb-3"><input class="form-control" type="text" name="email" placeholder="Email"></div>
            <div class="mb-3"><input class="form-control" type="text" name="name" placeholder="Name"></div>
            <div class="mb-3"><input class="form-control" type="text" name="surname" placeholder="Surname"></div>
            <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password"></div>
            <div class="mb-3"><input class="form-control" type="text" name="passwordConfirm"
                                     placeholder="Password (repeat)"></div>
            <div class="form-floating">
                    <select class="form-select" name="role" id="roleInput" aria-label="Floating label select example">
                        <option selected>Role</option>
                        <option value="ROLE_DOCTOR">DOCTOR</option>
                        <option value="ROLE_NURSE">NURSE</option>
                    </select>
            </div>
            <div class="mb-3">
                <div class="form-check"><label class="form-check-label"><input class="form-check-input" type="checkbox">I agree to the terms of registration.</label></div>
            </div>
            <div class="mb-3">
                <button class="btn btn-primary d-block w-100" type="submit">Sign Up</button>
            </div>
        </form:form>
    </div>
</section>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
</body>
<script>
    $("#edit_role").on('show.bs.modal', function (e) {
        var role = $(e.relatedTarget).data('staff-role');
        $('#roleInput').val(role);
    });
    $("#edit_role").on('hidden.bs.modal', function () {
        var form = $(this).find('form');
        form[0].reset();

    });
    var form = document.querySelector('.formWithValidation2')
    var role = form.querySelector('.role')

    form.addEventListener("submit", function (event) {
        event.preventDefault()


        $.ajax({
            url: '/registration',
            datatype: 'json',
            type: "POST",
            dataType: 'JSON',
            data: JSON.stringify({
                id: ${staff.id},
                name: '${staff.name}',
                surname: "${staff.surname}",
                email: '${staff.email}',
                role: role.value,
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <title>Registration</title>
</head>
<body>
    <div style="margin-bottom: 13rem">
        <jsp:include page="/html/navbar.jsp" />
    </div>

    <h1 style="text-align: center;">Регистрация</h1>
    <hr style="margin-bottom: 3rem">
    <form class="w-25 mx-auto" action="" method="post">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">@</span>
            <input name="login" type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
        </div>

        <div class="input-group mb-3">
            <input name="pass" type="password" class="form-control" placeholder="Password" aria-label="Recipient's username" aria-describedby="basic-addon2">
        </div>
        <button class="btn btn-primary">Зарегестрироваться</button>
    </form>
</body>
</html>

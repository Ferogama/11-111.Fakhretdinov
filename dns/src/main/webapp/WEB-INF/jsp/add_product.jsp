<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <title>Add product</title>
</head>
<body>
    <div style="margin-bottom: 13rem;">
        <jsp:include page="/html/navbar.jsp" />
    </div>
    <h2 style="text-align: center;">Добавить новый товар</h2>
    <hr style="margin-top: 2rem; margin-bottom: 2rem;">
    <form action="" method="post" class="w-25 mx-auto" enctype="multipart/form-data">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Название</span>
            <input name="name" required type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">Описание</span>
            <textarea name="description" required class="form-control" aria-label="With textarea"></textarea>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">Цена</span>
            <input name="price" required type="number" class="form-control" aria-label="Username" aria-describedby="basic-addon2">
        </div>

        <div class="input-group mb-3">
            <label class="input-group-text" for="inputGroupFile01">Upload</label>
            <input name="img" type="file" class="form-control" id="inputGroupFile01">
        </div>

        <button class="btn btn-primary">Добавить товар</button>
    </form>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<title>Edit</title>
</head>
<body>
	<div style="margin-bottom: 13rem;">
		<jsp:include page="/html/navbar.jsp" />
	</div>

	<h2 style="text-align: center;">Редактировать товар</h2>
	<hr style="margin-top: 2rem; margin-bottom: 2rem;">

	<form action="" method="post" class="w-25 mx-auto">
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">Название</span>
			<input
					value="${product.getName()}"
					name="name"
					required
					type="text"
					class="form-control"
					aria-label="Username"
					aria-describedby="basic-addon1"
			>
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text">Описание</span>
			<textarea
					name="description"
					required
					class="form-control"
					aria-label="With textarea"
			>${product.getDescription()}</textarea>
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon2">Цена</span>
			<input
					value="${product.getPrice()}"
					name="price"
					required
					type="number"
					class="form-control"
					aria-label="Username"
					aria-describedby="basic-addon2"
			>
		</div>
		<button class="btn btn-primary">Сохранить изменения</button>
	</form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <title>Products</title>
</head>
<body>
    <div style="margin-bottom: 13rem;">
        <jsp:include page="/html/navbar.jsp" />
    </div>

    <div style="display: flex; flex-wrap: wrap; justify-content: space-evenly">
        <c:forEach var="product" items="${products}">

			<div class="card text-bg-secondary mb-3" style="max-width: 25rem;">
				<img src="/images/${product.getImgName()}" class="card-img-top" alt="...">
				<div class="card-header">${product.getPrice()} ₽</div>
				<div class="card-body">
					<h5 class="card-title">${product.getName()}</h5>
					<p class="card-text">${product.getDescription()}</p>
				</div>
				<div class="card-footer">
					<form action="" method="post" style="display: inline-block; margin-right: 10px">
						<button name="productId" value="${product.getId()}" class="btn btn-dark">Добавить в корзину</button>
					</form>
					<c:if test="${isAdmin}">
						<form action="${pageContext.request.contextPath}/products/delete" method="post" style="display: inline-block;">
							<button class="btn btn-outline-dark" name="deleteProductId" value="${product.getId()}">Удалить</button>
						</form>
						<form action="${pageContext.request.contextPath}/products/edit" method="get" style="display: inline-block;margin-right: 10px">
							<button class="btn btn-outline-dark" name="updateProductId" value="${product.getId()}">Редактировать</button>
						</form>
					</c:if>

					<form action="/comment/${product.getId()}" style="display: inline-block">
						<button class="btn btn-info">Отзывы</button>
					</form>

				</div>
			</div>

        </c:forEach>
    </div>
</body>
</html>

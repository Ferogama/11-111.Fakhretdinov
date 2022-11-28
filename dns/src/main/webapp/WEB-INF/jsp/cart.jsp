<%@ page import="service.ProductService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<title>Cart</title>
</head>
<body>
<div style="margin-bottom: 13rem;">
	<jsp:include page="/html/navbar.jsp"/>
</div>

<h2 style="text-align: center; margin-bottom: 3rem">Корзина</h2>

<ul class="list-group">
	<c:forEach var="order" items="${map.keySet()}">
		<li class="list-group-item">
				${ProductService.getNameById(order)}
			<b>x</b> ${map.get(order)}, ${ProductService.getPrice(order, map.get(order))} ₽
			<form style="display: inline-block; margin-left: 10px" action="/cart/delete" method="post">
				<button name="deleteProduct" value="${order}" class="btn btn-outline-danger">удалить</button>
			</form>
		</li>
	</c:forEach>
</ul>

<h4 style="margin-top: 3rem; margin-left: 10px; margin-right: 2rem; display: inline-block">Итого: </h4>
<form action="" method="post" style="display: inline-block">
	<input
			style="width: 10rem; display: inline-block; text-align: center"
			name="totalSum"
			class="form-control"
			type="text"
			value="${ProductService.getTotalPrice(requestScope.map)}"
			readonly
	> ₽
	<button style="margin-left: 10px;" class="btn btn-primary">Оплатить</button>
</form>

</body>
</html>

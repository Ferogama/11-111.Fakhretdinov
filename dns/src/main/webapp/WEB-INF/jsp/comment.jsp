<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<title>Comment</title>
</head>
<body>
	<div style="margin-bottom: 8rem;">
		<jsp:include page="/html/navbar.jsp" />
	</div>
	<h2 style="text-align: center">Отзывы</h2>
	<hr style="margin-top: 2rem; margin-bottom: 2rem">

	<div class="w-75 mx-auto">
		<ul class="list-group" style="margin-bottom: 5rem">
			<c:forEach var="comment" items="${comments}">
				<li class="list-group-item list-group-item-info">
					<b>${comment.getUserName()}</b>: ${comment.getText()}
				</li>
			</c:forEach>
		</ul>
	</div>

	<div class="w-25 mx-auto">
		<form action="" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">Оставьте свой отзыв</span>
				<input name="comment" required type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
			</div>
			<button class="btn btn-outline-info">Отправить</button>
		</form>
	</div>


</body>
</html>

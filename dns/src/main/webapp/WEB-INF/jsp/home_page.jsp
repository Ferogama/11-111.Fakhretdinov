<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
    <title>Home page</title>
</head>
<body>
    <div style="margin-bottom: 100px;">
        <jsp:include page="/html/navbar.jsp" />
    </div>

    <div id="alert" class="alert alert-secondary w-75 mx-auto" role="alert">
		<h4 class="alert-heading">Добро пожаловать в DNS</h4>
		<p>В магазине можно приобрести цифровую и бытовую технику </p>
		<hr>
		<p style="display: inline-block; margin-right: 1rem" class="mb-0">Приятных покупок!</p>
		<button id="close" class="btn btn-outline-danger">Закрыть</button>
    </div>

	<h4 style="text-align: center">Как пользоваться сайтом</h4>
	<div class="accordion w-50 mx-auto" id="accordionExample">
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingOne">
				<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					Авторизация и регистрация
				</button>
			</h2>
			<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
				<div class="accordion-body">
					Во вкладках <code>Registration</code> и <code>Log in</code> можно зарегестрироваться и авторизироваться соответсвенно.
					После того, как вы авторизировались, в левом верхнем углу вы можете посмотреть свой текущий баланс и ваш ник.
				</div>
			</div>
		</div>
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingTwo">
				<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
					Каталог товаров
				</button>
			</h2>
			<div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
				<div class="accordion-body">
					Во вкладке <code>Catalog</code> можно помортеть текущий список товаров и их цены.
					Оставлять комментарии и добавлять в корзину могут только авторизованные пользователи.
				</div>
			</div>
		</div>
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingThree">
				<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
					Корзина
				</button>
			</h2>
			<div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
				<div class="accordion-body">
					Во вкладке <code>Cart</code>, если вы авторизованы, можно посмотреть товары которые вы добавляли в корзину, удалять и расплатиться.
					Если товаров в корзине нет, вас перебросит на каталог товаров.
				</div>
			</div>
		</div>

		<div class="accordion-item">
			<h2 class="accordion-header" id="headingFour">
				<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
					Привилегии админа
				</button>
			</h2>
			<div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
				<div class="accordion-body">
					Админ может добавлять новые товары во вкладке <code>Add product</code>, редактировать и удалять имеющиеся.
				</div>
			</div>
		</div>
	</div>

	<hr style="margin-top: 3rem; margin-bottom: 3rem;">

	<script>
		$("#close").click(function () {
            $("#alert").hide();
		})
	</script>
</body>
</html>

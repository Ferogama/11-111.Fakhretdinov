<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.User" %>
<%@ page import="service.UserService" %>
<%@ page import="listeners.InitListener" %>
<nav class="navbar navbar-dark bg-dark fixed-top">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/main">
			<img style="width: 70px" src="<c:url value="/img/dns.png"/>" alt="">
            <small style="font-size: 13px; margin-left: 5px" class="text-muted">
                ${UserService.getAuthUserLogin(InitListener.session)} ${UserService.getBalance(InitListener.session)}
            </small>
        </a>
		<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar"
				aria-controls="offcanvasDarkNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar"
			 aria-labelledby="offcanvasDarkNavbarLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">DNS</h5>
				<button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas"
						aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/main">Main</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/registration">Registration</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/authorization">Log in</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
						   aria-expanded="false">
							Shop
						</a>
						<ul class="dropdown-menu dropdown-menu-dark">
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/products">Catalog</a></li>
							<li><a class="dropdown-item" href="/cart">Cart</a></li>
                            <% User user = (User) request.getSession().getAttribute("user"); %>
                            <% if ( user != null && user.getRole().equals("admin")) { %>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/products/add">Add product</a></li>
						    <% } %>
                        </ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>
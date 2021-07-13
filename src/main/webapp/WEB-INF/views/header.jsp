<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()" var="isAuth" />

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="#">${appName}</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarCollapse" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="<c:url value="/"/>">Home <span class="sr-only">(current)</span></a>
			</li>

		</ul>

		<div class="text-end">
			<c:if test="${isAuth}">Benvenuto</c:if>
			<c:if test="${!isAuth}">
					<a href="<c:url value="/login"/>" class="btn btn-outline-light me-2">Login</a>
					<a href="<c:url value="/register"/>" class="btn btn-warning">Sign-up</a>
			</c:if>
		</div>
	</div>
</nav>
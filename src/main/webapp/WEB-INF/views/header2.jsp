<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()" var="isAuth" />

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="FoodTracker/">${appName}</a>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="<c:url value="/"/>">Home <span class="sr-only">(current)</span></a>
			</li>
	      </ul>
      <ul class="myMenu">
      <li class="myDropdown"><a href="">Cibi</a>
        <ul class="mySubMenu">
            <li><a href="<c:url value="/food/add"/>">Aggiungi</a></li>
            <li><a href="<c:url value="/food/list"/>">Lista</a></li>
          </ul>
        </li>
		</ul>

		<div class="text-end">
			<c:if test="${isAuth}"><a href="<c:url value="/"/>" class="btn btn-outline-light me-2"><sec:authentication property='principal.username'/></a>
					<a href="<c:url value="/logout"/>" class="btn btn-warning">Logout</a></c:if>
			<c:if test="${!isAuth}">
					<a href="<c:url value="/login"/>" class="btn btn-outline-light me-2">Login</a>
					<a href="<c:url value="/register"/>" class="btn btn-warning">Sign-up</a>
			</c:if>
		</div>
	</div>
</nav>
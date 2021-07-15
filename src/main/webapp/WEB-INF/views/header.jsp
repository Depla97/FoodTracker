<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()" var="isAuth" />

<nav class="mynavbar">
	<ul class="menu">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li class="dropdown"><a href="<c:url value="/meal/list"/>">Pasti</a>
			<ul class="subMenu">
				<li><a href="<c:url value="/meal/add"/>">Aggiungi</a></li>
				<li><a href="<c:url value="/meal/list"/>">Lista</a></li>
			</ul></li>
		<li class="dropdown"><a href="">Cibi</a>
			<ul class="subMenu">
				<li><a href="<c:url value="/food/add"/>">Aggiungi</a></li>
				<li><a href="<c:url value="/food/list"/>">Lista</a></li>
			</ul></li>

		<c:if test="${isAuth}">
			<li class="rightaligned"><a href="<c:url value="/logout"/>">Logout</a>
			<li class="rightaligned"><a href="<c:url value="/"/>"><sec:authentication
						property='principal.username' /></a></li>
		</c:if>

		<c:if test="${!isAuth}">
			<li class="rightaligned2">
		<a href="<c:url value="/register"/>" class="btn btn-warning">Sign-up</a>
			</li>
			<li class="rightaligned2"><a href="<c:url value="/login"/>"
				class="btn btn-outline-light me-2">Login</a></li>

			</c:if>
	</ul>


</nav>

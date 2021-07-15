<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/registerUser" var="action_url" />
<main class="form-signin">
<div class="jumbotron">
<form:form method="POST" action="${action_url}" modelAttribute="newUser">
	<h1 class="h3 mb-3 fw-normal">Registrati</h1>

	<div class="form-floating">
		<form:input path="username" type="username" class="form-control" id="floatingInput"
			placeholder="Username"/><label for="floatingInput">Username</label>
	</div>
	<div class="form-floating">
		<form:input path="password" type="password" class="form-control" id="floatingPassword"
			placeholder="Password"/><label for="floatingPassword">Password</label>
	</div>

	
	<form:button class="w-100 btn btn-lg btn-primary" type="submit">Registrati</form:button>
	
</form:form>
</div>
</main>
	

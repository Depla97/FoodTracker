<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/meal/createMeal" var="action_url" />
<main role="main" class="container">
	<div>${currentUser.username}</div>
	<form:form method="POST" action="${action_url}"
		modelAttribute="newMeal">
		<div class="col-12">

			<form:label path="mealType" class="form-label">Tipo di pasto</form:label>
			<form:input path="mealType" class="form-control" placeholder="es:1" />


			<form:label path="date" class="form-label">Data del pasto</form:label>
			<form:input path="date" class="form-control"
				placeholder="format: gg/mm/aaaa " />

		</div>
		<hr class="my-4">
		<form:button class="w-100 btn btn-primary btn-lg" type="submit">Inserisci nel Database</form:button>
	</form:form>
</main>
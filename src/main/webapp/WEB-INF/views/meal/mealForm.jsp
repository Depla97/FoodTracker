<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/meal/createMeal" var="action_url" />

<main role="main" class="container">
<div class="jumbotron">
	<h1>Aggiungi il tuo pasto</h1>
	<br>
	<form:form method="POST" action="${action_url}"
		modelAttribute="newMeal">
		<div class="container">
			<%-- <form:label path="mealType" class="form-label">Tipo di pasto</form:label>
			<form:input path="mealType" class="form-control" placeholder="es:1" /> --%>
			<form:select path="mealType" style="width:200px" name="mealType">
				<option selected="selected" value="1">Colazione</option>
				<option value="2">Pranzo</option>
				<option value="3">Merenda</option>
				<option value="4">Cena</option>
				<option value="5">Spuntino</option>
			</form:select>
			<br>
			<%-- <form:label path="date" class="form-label">Data</form:label>
			<form:input path="date" class="form-control" style="width:200px"
				placeholder="formato: gg/mm/aaaa" /> --%>
				<form:select path="date" style="width:200px" name="date">
				<option selected="selected" value="1">Oggi</option>
				<option value="2">Ieri</option>
				<option value="3">Domani</option>
				</form:select>
		<hr class="my-4">
		<form:button class="w-100 btn btn-primary btn-lg" type="submit">Prosegui</form:button>
	</form:form>
	</div>
</main>
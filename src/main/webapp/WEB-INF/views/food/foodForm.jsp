<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/food/saveFood" var="action_url" />
<main role="main" class="container">
	<div class="jumbotron">
		<c:if test="${errorCode == '1'}"><h2 style="color:red">Prima di creare un pasto, inserisci almeno un alimento</h2></c:if>
		<br>
		<h1>Inserisci un alimento</h1>
		<form:form method="POST" action="${action_url}"
			modelAttribute="newFood">
			<form:label path="nome" class="form-label">Nome</form:label>
			<form:input path="nome" class="form-control" placeholder="es:Pane" />
			<br>
			<form:label path="descrizione" class="form-label">Descrizione/Marca</form:label>
			<form:input path="descrizione" class="form-control"
				placeholder="es:Mulino Bianco" />
			<br>

			<form:label path="calorie" class="form-label">Calorie per 100g</form:label>
			<form:input path="calorie" class="form-control" />
			<br>
			<form:label path="peso" class="form-label">Porzione in grammi</form:label>
			<form:input path="peso" class="form-control" />
			<br>
			<hr class="my-4">
			<form:button class="w-100 btn btn-primary btn-lg" type="submit">Inserisci nel Database</form:button>
		</form:form>
	</div>
</main>
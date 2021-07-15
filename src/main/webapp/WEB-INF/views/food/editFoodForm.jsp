<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<sec:authentication property='principal.username' var="user" />
<c:if test="${user==fEdit.user.username}">
<c:url value="/food/edit/saveFood?foodId=${foodId}" var="action_url" />
<main role="main" class="container">
	<div class="jumbotron">
		<h1>Modifica l'alimento selezionato</h1>
		<form:form method="POST" action="${action_url}"
			modelAttribute="fEdit">
			
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
			<form:button class="w-100 btn btn-primary btn-lg" type="submit">Applica le modifiche</form:button>
		</form:form>
	</div>
</main>
</c:if>
<c:if test="${user!=fEdit.user.username}">
<main role="main" class="container">
<div class="jumbotron">
<h2 style="color:red">Non sei autorizzato a modificare quest'oggetto</h2>
</div></main>
</c:if>
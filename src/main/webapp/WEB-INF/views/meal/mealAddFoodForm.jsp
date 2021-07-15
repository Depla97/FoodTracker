<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<main role="main" class="container">
<div class="jumbotron">
	<h1>Completa il tuo pasto <sec:authentication property='principal.username'/></h1>
<table class="table">
<thead class="thead-light">
	<tr>
		<th scope="col">Nome</th>
		<th scope="col">Marca</th>
		<th scope="col">Calorie</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${foodList}" var="f">
		<tr>
			
			<td>${f.nome}</td>
			<td>${f.descrizione}</td>
			<td>${f.calorie}</td>
			<td><a href="<c:url value="/meal/keepAdding?mealId=${mealId}&foodId=${f.id}"/>">Aggiungi</a></td>
			
		</tr>
</c:forEach>
</tbody>
</table>
	
	<div><h2>Cibi aggiunti</h2>
	
	<table class="table">
<thead class="thead-light">
	<tr>
		<th scope="col">Nome</th>
		<th scope="col">Marca</th>
		<th scope="col">Calorie</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${aggiunti}" var="a">
		<tr>
			<td>${a.nome}</td>
			<td>${a.descrizione}</td>
			<td>${a.calorie}</td>
			<td><a href="<c:url value="/meal/keepAdding?mealId=${mealId}&foodId=${f.id}"/>">Rimuovi</a></td>
			
		</tr>
</c:forEach>
</tbody>
</table>
	</div>
	<a href="<c:url value="/meal/completeMeal?mealId=${mealId}"/>" class="btn btn-warning">Completa il pasto e aggiungi al Database</a>
	</div>
</main>
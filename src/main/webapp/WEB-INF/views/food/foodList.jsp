<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="jumbotron">
<h1>
	Elenco alimenti di
	<sec:authentication property='principal.username' />
</h1>
</div>
<!--  <table>
	<thead>
		<td></td>
		<td>Nome</td>
		<td></td>
		<td>Marca</td>
		<td></td>
		<td>Calorie</td>
	</thead>
	<c:forEach items="${foodList}" var="f">
		<tr>
			<td>${f.nome}</td>
			<td></td>
			<td>${f.descrizione}</td>
			<td></td>
			<td>${f.calorie}</td>
		</tr>
	</c:forEach>
</table>
-->

<table class="table">
	<thead class="thead-light">
		<tr>
			<th scope="col">Nome</th>
			<th scope="col">Marca</th>
			<th scope="col">Calorie</th>
			<th scope="col">Porzione</th>
			<th scope="col">Azioni</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${foodList}" var="f">
			<tr>
				<td>${f.nome}</td>
				<td>${f.descrizione}</td>
				<td>${f.calorie}</td>
				<td>${f.peso}g</td>
				<td><a href="edit?foodId=${f.id}">Modifica</a><br>
				<a href="delete?foodId=${f.id}">Elimina</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
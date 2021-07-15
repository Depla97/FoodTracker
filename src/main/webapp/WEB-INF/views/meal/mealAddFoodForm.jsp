<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:url value="/meal/keepAdding" var="action_url" />
<main role="main" class="container">
	<div><sec:authentication property='principal.username'/></div>
	<h1>Elenco di ${currentUser.username}</h1>
<table>
<thead><td></td><td>Nome</td><td></td><td>Marca</td><td></td><td>Calorie</td></thead>
<c:forEach items="${foodList}" var="f">
<tr>
	<td>${f.nome}</td><td></td><td>${f.descrizione}</td><td></td><td>${f.calorie}</td><td><a href="<c:url value="/meal/keepAdding?mealId=${mealId}&foodId=${f.id}"/>">Aggiungi</a></td>
</tr>
</c:forEach>
</table>
	
	<div><h1>Cibi aggiunti</h1>
<table>
<thead><td></td><td>Nome</td><td></td><td>Marca</td><td></td><td>Calorie</td></thead>
<c:forEach items="${aggiunti}" var="a">
<tr>
	<td>${a.nome}</td><td></td><td>${a.descrizione}</td><td></td><td>${a.calorie}</td>
</tr>
</c:forEach>
</table></div>
	
	<a href="<c:url value="/meal/completeMeal?mealId=${mealId}"/>" class="btn btn-warning">Completa il pasto e aggiungi al Database</a>
</main>
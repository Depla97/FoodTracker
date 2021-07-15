<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	

<main role="main" class="container">
      <div class="jumbotron">
        <h1>Food Tracker</h1>
        <p class="lead"></p>
	<h2>Ecco i tuoi pasti di oggi
	<sec:authentication property='principal.username' />
</h2>
<table class="table">
<thead class="thead-light">
	<tr>
		<th scope="col">Tipo</th>
		<th scope="col">Data</th>
		<th scope="col">Calorie</th>
		<th scope="col">Azioni</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${mealList}" var="m">
		<tr>	
			<td>
			<c:if test="${m.mealType == 1}">Colazione</c:if>
			<c:if test="${m.mealType == 2}">Pranzo</c:if>
			<c:if test="${m.mealType == 3}">Merenda</c:if>
			<c:if test="${m.mealType == 4}">Cena</c:if>
			<c:if test="${m.mealType == 5}">Spuntino</c:if>
			</td>
			<td>${m.date}</td>
			<td>${m.calories}</td>
			<td><a href="<c:url value="/meal/delete?mealId=${m.id}"/>">Elimina</a></td>
		</tr>
		<c:forEach items="${m.foods}" var="f">
		<tr>

			<td>${f.nome} - ${f.descrizione} - ${f.calorie}</td>
			
		</tr>
		</c:forEach>
		</c:forEach>
</tbody>
</table>
</div>
</main>

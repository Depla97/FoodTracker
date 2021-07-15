<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 
<table>
<thead><td></td><td>Tipo</td><td></td><td>Data</td><td></td><td>Cibi</td></thead>
<c:forEach items="${mealList}" var="m">
<tr>
	<td>${m.mealType}</td><td></td><td>${f.descrizione}</td><td></td><td><c:forEach items="${m.foods}" var="f">${f.nome}</c:forEach></td>
</tr>
</c:forEach>
</table>
<hr/> -->
<main>
<h1>Elenco pasti di ${currentUser.username}</h1>
<table class="table">
<thead class="thead-light">
	<tr>
		<th scope="col">Tipo</th>
		<th scope="col">Data</th>
		<th scope="col">Calorie</th>
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
		</tr>
		<tr>
		<td>
			<c:forEach items="${m.foods}" var="f">
			<td>${f.nome}</td>
			</c:forEach>
			</td>
		</tr>
		</c:forEach>
</tbody>
</table>

</main>
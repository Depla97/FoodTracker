<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Elenco di ${currentUser.username}</h1>
<table>
<thead><td></td><td>Nome</td><td></td><td>Marca</td><td></td><td>Calorie</td></thead>
<c:forEach items="${foodList}" var="f">
<tr>
	<td>${f.nome}</td><td></td><td>${f.descrizione}</td><td></td><td>${f.calorie}</td>
</tr>
</c:forEach>
</table>
<hr/>
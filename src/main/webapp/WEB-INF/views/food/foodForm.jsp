<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/save" var="action_url" />
<main role="main" class="container">
<form:form method="POST" action="${action_url}" modelAttribute="newFood">
<div class="col-12">
              <form:label path="nome" class="form-label">Nome dell'alimento</form:label>
              <form:input path="nome" class="form-control" placeholder="es:Pane"/>
              <div class="invalid-feedback">
                Per favore inserisci un nome valido
              </div>
           
            <form:label path="descrizione" class="form-label">Nome dell'alimento</form:label>
              <form:input path="descrizione" class="form-control" placeholder="es:Pane"/>
              <div class="invalid-feedback">
                Per favore inserisci un nome valido
              </div>
            
            <form:label path="calorie" class="form-label">Nome dell'alimento</form:label>
              <form:input path="calorie" class="form-control" placeholder="es:Pane"/>
              <div class="invalid-feedback">
                Per favore inserisci un nome valido
              </div>
          </div>
          <hr class="my-4">
          <form:button class="w-100 btn btn-primary btn-lg" type="submit">Inserisci nel Database</form:button>
        </form:form>
            </main>
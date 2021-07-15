 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <main role="main" class="container">
      <div class="jumbotron">
        <h1>Home page</h1>
        <p class="lead"></p>
        <a class="btn btn-lg btn-primary" href="<c:url value="/food/add"/>" role="button">Inserisci un alimento &raquo;</a><br>
     	<!--  <a class="btn btn-lg btn-primary" href="addMeal" role="button">Inserisci un pasto &raquo;</a>-->
     	<a class="btn btn-lg btn-primary" href="<c:url value="/food/list"/>" role="button">Vai alla lista dei tuoi alimenti &raquo;</a>
      	<br>
      	<a class="btn btn-lg btn-primary" href="<c:url value="/meal/add"/>" role="button">Crea un nuovo pasto &raquo;</a>
      	<br>
      	<a class="btn btn-lg btn-primary" href="<c:url value="/meal/list"/>" role="button">Visualizza i tuoi pasti&raquo;</a>
      </div>
</main>

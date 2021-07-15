<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main class="form-signin">
<div class="jumbotron">
<form name='login' action="<c:url value="/login" />" method='POST'>
	<h1 class="h3 mb-3 fw-normal">Accedi al tuo account</h1>
	<c:if test="${error==true}"><h4 style="color:red">Username o password errati</h2></c:if>
	<div class="form-floating">
		<input name= "username" type="username" class="form-control" id="floatingInput"
			placeholder="Username"> <label for="floatingInput">Username</label>
	</div>
	<div class="form-floating">
		<input name="password" type="password" class="form-control" id="floatingPassword"
			placeholder="Password"> <label for="floatingPassword">Password</label>
	</div>
	
	<button class="w-100 btn btn-lg btn-primary" type="submit">Accedi</button>
	
</form>
<%--
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         --%>
         </div>
         
</main>

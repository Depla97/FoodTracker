<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main class="form-signin">
<form name='login' action="<c:url value="/login" />" method='POST'>
	<img class="mb-4" src="/docs/5.0/assets/brand/bootstrap-logo.svg"
		alt="" width="72" height="57">
	<h1 class="h3 mb-3 fw-normal">Accedi al tuo account</h1>

	<div class="form-floating">
		<input  name= "username" class="form-control" id="floatingInput"
			placeholder="Username"> <label for="floatingInput">Username</label>
	</div>
	<div class="form-floating">
		<input name="password" class="form-control" id="floatingPassword"
			placeholder="Password"> <label for="floatingPassword">Password</label>
	</div>
	
	<button class="w-100 btn btn-lg btn-primary" type="submit">Accedi</button>
	
</form>
<%--
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         --%>
</main>

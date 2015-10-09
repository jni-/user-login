<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="jumbotron">				
	<c:url value="/login" var="loginAction" />
	<form:form commandName="loginForm" action="${loginAction}" method="POST" class="form-inline" role="form">
		<div class="form-group">
			<div class="input-group">
      			<div class="input-group-addon">@</div>
				<form:input path="email" placeholder="email" class="form-control" />
			</div>
		</div>
		
		<div class="form-group">
			<form:password path="password" placeholder="password" class="form-control" />
		</div>
		
		<button type="submit" class="btn btn-primary">Login</button>
	</form:form>
</div>

<p><small>Valid login: "test@test.com" and "1234" (whitout quotes, of course)</small></p>
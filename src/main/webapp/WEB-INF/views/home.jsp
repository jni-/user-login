<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>

	<c:if test="${not empty alert}">
		<div class="alert alert-danger">
			${alert}
		</div>
	</c:if>

	<c:choose>
		<c:when test="${not empty email}">
			Welcome, ${email} (<a href="<c:url value="/logout" />">Logout</a>)
		</c:when>
		<c:otherwise>
            <jsp:include page="_login_form.jsp" />
		</c:otherwise>
	</c:choose>


</body>
</html>
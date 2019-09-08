<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>

<title>ArtStudio 99 Homepage</title>
</head>
<body>
	<h2>ArtStudio 99 Homepage</h2>
	<hr>

	<p>Welcome to ArtStudio 99</p>
	<hr>
	<p>
		User:
		<security:authentication property="principal.username" />
		<br> <br> Role(s):
		<security:authentication property="principal.authorities" />
	</p>
	<hr>
	<security:authorize access="hasRole('TEACHER')">
		<p>
			<a href="${pageContext.request.contextPath}/teachers">PED Day
				Meeting</a> (Only for Manager)
		</p>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">Security System
				Meeting</a> (Only for Admin)
		</p>
	</security:authorize>
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>
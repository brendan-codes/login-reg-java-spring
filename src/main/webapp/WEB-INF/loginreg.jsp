<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login or Register</h1>
	
	<p>Register</p>
	<form:form method="POST" action="/registration" modelAttribute="userObject">
		<p>
			<form:label path="name">Name:</form:label>
			<form:input type="type" path="name"></form:input>
		</p>
		<p>
			<form:label path="email">Email:</form:label>
			<form:input type="type" path="email"></form:input>
		</p>
		<p>
			<form:label path="password">Password:</form:label>
			<form:input type="type" path="password"></form:input>
		</p>
		<p>
			<form:label path="passwordConfirmation">Confirm Password:</form:label>
			<form:input type="type" path="passwordConfirmation"></form:input>
		</p>
		<input type="submit" value="Register!" />
	</form:form>
	
	<p>Login</p>
	<form action="/login" method="POST">
		Email: <input type="text" name="email" />
		Password: <input type="text" name="password"/>
		<input type="submit" value="Login!" />
	</form>
</body>
</html>
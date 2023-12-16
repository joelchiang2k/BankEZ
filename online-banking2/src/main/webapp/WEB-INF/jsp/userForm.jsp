<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
</head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

<style>
.error{
   color:red;
   font-style:italic;
   font-weight:bold;
}

</style>
<body>
<div align="center">
	<table>
	<tr>
	<td><a href="home">Home</a></td><td>|</td>
	<td><a href="userForm">User Form</a></td><td>|</td>
	<td><a href="roleForm">Role Form</a></td><td>|</td>
	<td><a href="accountForm">Account Form</a></td><td>|</td>
	<td><a href="customerForm">Customer Form</a></td><td>|</td>
	<td><a href="branchForm">Branch Form</a></td>
	
	<sec:authorize access="isAuthenticated()">
	<td>|</td>
		<td><a href="logout">Logout</a></td>
	</sec:authorize>
	<td></td>
	<td></td>
	</tr>
	</table>
</div>


<div align="center">

<h1>User Form</h1>
<sec:authorize access="isAuthenticated()">
		<br>Logged In User:<sec:authentication property="principal.username"/>
		<br>Principal: <sec:authentication property="principal"/>
		<br>Password: <sec:authentication property="principal.password"/>
		<br>Enabled: <sec:authentication property="principal.enabled"/>
		<br>AccountNonExpired: <sec:authentication property="principal.accountNonExpired"/>
		<br>AccountNonLocked: <sec:authentication property="principal.accountNonLocked"/>
		<br>credentialsNonExpired: <sec:authentication property="principal.credentialsNonExpired"/>
		<br>Granted Authorities: <sec:authentication property="principal.authorities"/>
		<br> loggedInUser: ${loggedInUser}
		<br><a href="logout">Logout</a>
</sec:authorize>


<f:form action="saveUser" modelAttribute="user">
<table>
<c:if test="${hasErrors}">
	<tr>
		<td>Errors:</td>
		<td><f:errors path="*" cssClass="error"/></td>
	</tr>
</c:if>

	<tr>
		<td>User Id:</td>
		<td><f:input path="userId" value="${user.userId}"/></td>
	</tr>

	<tr>
		<td>User Name</td>
		<td><f:input  path="username" value="${user.username}"/></td>
	</tr>

	<tr>
		<td>Password</td>
		<td><f:password   path="password" value="${user.password}"/></td>
	</tr>


	<tr>
		<td>Email</td>
		<td><f:input   path="email" value="${user.email}"/></td>
	</tr>

	<tr>
		<td>Roles</td>
		<td>
			<c:forEach items="${roles}" var="role">
				<c:if test="${retrievedRole.contains(role) }" >
					<f:checkbox path="roles" label="${role.name}" value="${role.roleId}" checked="true"/>
					</c:if>
					
					<c:if test="${!retrievedRole.contains(role) }" >
					<f:checkbox path="roles" label="${role.name}" value="${role.roleId}" />
				</c:if>
			</c:forEach>
		</td>
	</tr>

<tr>
<td colspan="2" align="center"><input  type="submit" value="submit" class="btn btn-primary"/></td>
</tr>
</table>
</f:form>

</div>

<div align="center">

<table border="1">
<thead>
<tr><td>User Id</td><td>Name</td><td>Password</td><td>Email</td>
<td>Roles</td>
<td>Action</td>
</tr>
</thead>

<c:forEach items="${users}" var="user">
<tr>
<td>${user.getUserId() }</td>
<td>${user.getUsername() }</td>
<td>${user.getPassword() }</td>
<td>${user.getEmail() }</td>

<td>
<c:forEach items="${user.getRoles()}" var="role">
${role.getName()}

</c:forEach>
</td>

<td>
<a href="${pageContext.request.contextPath}/update?userId=${user.getUserId() }">Update</a>
<a href="${pageContext.request.contextPath}/delete?userId=${user.getUserId() }">Delete</a>
</td>
</tr>
</c:forEach>

</table>
</div>
</body>
</html>
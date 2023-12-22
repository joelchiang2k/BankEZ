<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Account Form</title>
<style type="text/css">
.error
(
color
:red
;
)
</style>
</head>
<div align="center">
	<table>
	<tr>
	<td><a href="home">Home</a></td><td>|</td>
	<td><a href="userForm">User Form</a></td><td>|</td>
	<td><a href="roleForm">Role Form</a></td><td>|</td>
	<td><a href="accountForm">Account Form</a></td><td>|</td>
	<td><a href="customerForm">Customer Form</a></td><td>|</td>
	<td><a href="branchForm">Branch Form</a></td></td><td>|</td>
	<td><a href="bankTransactionForm">Bank Transaction Form</a></td>
	
	<sec:authorize access="isAuthenticated()">
	<td>|</td>
		<br> loggedInUser: ${loggedInUser}
		<td><a href="logout">Logout</a></td>
	</sec:authorize>
	<td></td>
	<td></td>
	</tr>
	</table>
</div>



<body>
	<div align="center">
		<h1>Account Form</h1>
		<f:form action="saveAccount" modelAttribute="account">
			<table>
			
				<tr>
					<td>Account Id:</td>
					<td><f:input path="accountId" value="${account.accountId}"/></td>
					<td><f:errors path="accountId" cssClass="error" /></td>
				</tr> 
				
				<tr>
					<td>Account Type:${selectedAccountType}</td>
					<td>
					<c:forEach items="${accountTypes}" var="at">
					<c:if test="${selectedAccountType==at.name()}" >
						<f:radiobutton  path="accountType" value="${at.name()}" label="${at.name()}" checked="checked"/>
					</c:if>
					
					<c:if test="${selectedAccountType!=at.name()}" >
						<f:radiobutton  path="accountType" value="${at.name()}" label="${at.name()}" />
					</c:if>
					</c:forEach>
					</td>
				</tr>
				
				<tr>
					<td>Date Opened:</td><td><f:input type="date"  path="accountDateOpened" name="accountDateOpened"  value="${account.getAccountDateOpened()}"/></td>
				</tr>
				
				<tr>
					<td>Account Folder:</td>
					<td><f:input path="accountFolder" value="${account.accountFolder}"/></td>
					<td><f:errors path="accountFolder" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Account Balance:</td>
					<td><f:input path="accountBalance" value="${account.accountBalance}"/></td>
					<td><f:errors path="accountBalance" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Account Branch:</td>
					<td><f:input path="accountBranch.branchId" value="${account.accountBranch.getBranchId()}"/></td>
					<td><f:errors path="accountBranch.branchId" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Account Customer:</td>
					<td><f:input path="accountCustomer.customerId" value="${account.accountCustomer.getCustomerId()}"/></td>
					<td><f:errors path="accountCustomer.customerId" cssClass="error" /></td>
				</tr>	 			
			</table>
			<tr>
				<td colspan="2" align="center"><input type="submit"   value="Submit" /></td>
			</tr>
		</f:form>
	</div>
	<p></p>
	<p></p>
	<div class="container-sm" align="center">
		<table class="table table-bordered border-primary">
			<thead>
				<tr>
					<td>Account Id</td>
					<td>Account Type</td>
					<td>Date Opened</td>
					<td>Account Folder</td>
					<td>Account Balance</td>
					<td>Account Branch</td>
					<td>Account Customer</td>
				</tr>
			</thead>
			<c:forEach items="${accounts}" var="account">

				<tr>
					<td>${account.getAccountId() }</td>
					<td>${account.getAccountType()}</td>
					<td>${account.getAccountDateOpened()}</td>
					<td>${account.getAccountFolder()}</td>
					<td>${account.getAccountBalance()}</td>
					<td>${account.getAccountBranch().getBranchName()}</td>
					<td>${account.getAccountCustomer().getCustomerName()}</td>
					<td><a href="updateAccount?accountId=${account.getAccountId()}">Update</a></td>
					<td><a href="deleteAccount?accountId=${account.getAccountId()}">Delete</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>
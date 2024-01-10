<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Bank Transaction Form</title>
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
	<td><a href="bankTransactionForm">Bank Transaction Form</a></td><td>|</td>
	<td><a href="searchForm">Search Form</a></td>
	
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
		<h1>Search Form</h1>
		<sec:authorize access="hasAuthority('Admin')">
		<f:form action="saveSearch" modelAttribute="search">
			<table>
				
				<tr>
					<td>Search For Account By Id:</td>
					<td><f:input path="searchAccountId" value="${search.searchAccountId}"/></td>
					<td><f:errors path="searchAccountId" cssClass="error" /></td>
				</tr>
		
				<tr>
				    <td>Search Transaction Type: ${selectedSearchTransactionType}</td>
				    <td>
				        <select name="searchTransactionType" id="searchTransactionType">
				            <c:forEach items="${searchTransactionTypes}" var="bt">
				                <option value="${bt.name()}" 
				                        <c:if test="${selectedSearchTransactionType == bt.name()}">selected</c:if>>
				                    ${bt.name()}
				                </option>
				            </c:forEach>
				        </select>
				    </td>
				    <td><f:errors path="searchTransactionType" cssClass="error" /></td>
				</tr>

				
				<tr>
					<td>Search From:</td><td><f:input type="datetime-local"  path="searchFromDateTime" name="searchFromDateTime"  value="${search.getSearchFromDateTime()}"/></td>
					<td><f:errors path="searchFromDateTime" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Search To:</td><td><f:input type="datetime-local"  path="searchToDateTime" name="searchToDateTime"  value="${search.getSearchToDateTime()}"/></td>
					<td><f:errors path="searchToDateTime" cssClass="error" /></td>
				</tr>
				
				 			
			</table>
			<tr>
				<td colspan="2" align="center"><input type="submit"   value="Submit" /></td>
			</tr>
		</f:form>
		</sec:authorize>
	</div>
	<p></p>
	<p></p>
	<div class="container-sm" align="center">
		<table class="table table-bordered border-primary">
			<thead>
				<tr>
					<td>Account Id</td>
					<td>Bank Transaction From Account</td>
					<td>Bank Transaction To Account</td>
					<td>Bank Transaction Amount</td>
					<td>Bank Transaction Type</td>
					<td>Bank Transaction Date</td>
					<td>Comment</td>
				</tr>
			</thead>
			<c:forEach items="${bankTransactions}" var="bankTransaction">

				<tr>
					<td>${bankTransaction.getBankTransactionId() }</td>
					<td>${bankTransaction.getBankTransactionFromAccount()}</td>
					<td>${bankTransaction.getBankTransactionToAccount()}</td>
					<td>${bankTransaction.getBankTransactionAmount()}</td>
					<td>${bankTransaction.getBankTransactionType()}</td>
					<td>${bankTransaction.getBankTransactionDateTime()}</td>
					<td>${bankTransaction.getComment()}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>
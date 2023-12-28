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
		<h1>Bank Transaction Form</h1>
		<f:form action="saveBankTransaction" modelAttribute="bankTransaction">
			<table>
			
				<tr>
					<td>Bank Transaction Id:</td>
					<td><f:input path="bankTransactionId" value="${bankTransaction.bankTransactionId}"/></td>
					<td><f:errors path="bankTransactionId" cssClass="error" /></td>
				</tr> 
				
				<tr>
					<td>Bank Transaction From Account:</td>
					<td><f:input path="bankTransactionFromAccount" value="${bankTransaction.bankTransactionFromAccount}"/></td>
					<td><f:errors path="bankTransactionFromAccount" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Bank Transaction To Account:</td>
					<td><f:input path="bankTransactionToAccount" value="${bankTransaction.bankTransactionToAccount}"/></td>
					<td><f:errors path="bankTransactionToAccount" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Bank Transaction Amount:</td>
					<td><f:input path="bankTransactionAmount" value="${bankTransaction.bankTransactionAmount}"/></td>
					<td><f:errors path="bankTransactionAmount" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Bank Transaction Type:${selectedBankTransactionType}</td>
					<td>
					<c:forEach items="${bankTransactionTypes}" var="bt">
					<c:if test="${selectedBankTransactionTypes==bt.name()}" >
						<f:radiobutton  path="bankTransactionType" value="${bt.name()}" label="${bt.name()}" checked="checked"/>
					</c:if>
					
					<c:if test="${selectedBankTransactionType!=bt.name()}" >
						<f:radiobutton  path="bankTransactionType" value="${bt.name()}" label="${bt.name()}" />
					</c:if>
					</c:forEach>
					</td>
					<td><f:errors path="bankTransactionType" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Transaction Date:</td><td><f:input type="datetime-local"  path="bankTransactionDateTime" name="bankTransactionDateTime"  value="${bankTransaction.getBankTransactionDateTime()}"/></td>
					<td><f:errors path="bankTransactionDateTime" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Comment:</td>
					<td><f:input path="comment" value="${bankTransaction.comment}"/></td>
					<td><f:errors path="comment" cssClass="error" /></td>
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
					<td>Bank Transaction Id</td>
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
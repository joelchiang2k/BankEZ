<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Branch Form</title>
<style type="text/css">
.error
(
color
:red
;
)
</style>
</head>
<body>
	<div align="center">
		<h1>Branch Form</h1>
		<f:form action="saveBranch" method="POST" modelAttribute="branch">
			<table>
			
				<tr>
					<td>Branch Id:</td>
					<td><f:input path="branchId" value="${branch.branchId}"/></td>
					<td><f:errors path="branchId" cssClass="error" /></td>
				</tr> 
				

				<tr>
					<td>Branch Name:</td>
					<td><f:input path="branchName" value="${branch.branchName}"/></td>
					<td><f:errors path="branchName" cssClass="error" /></td>
				</tr>
				
				<tr>
				<td>AddressLine1:</td>
				<td>
				<f:input path="branchAddress.addressLine1" value="${branch.getBranchAddress().getAddressLine1()}"/>
				</td>
				</tr>
				
				<tr>
				<td>AddressLine2:</td>
				<td>
				<f:input path="branchAddress.addressLine2" value="${branch.getBranchAddress().getAddressLine2()}"/>
				</td>
				</tr>
				
				<tr>
				<td>City:</td>
				<td>
				<f:input path="branchAddress.city"  value="${branch.getBranchAddress().getCity()}"/>
				</td>
				</tr>
				
				<tr>
				<td>State:</td>
				<td>
				<f:input path="branchAddress.state"  value="${branch.getBranchAddress().getState()}"/>
				</td>
				</tr>
				
				<tr>
				<td>Country:</td>
				<td>
				<f:input path="branchAddress.country"  value="${branch.getBranchAddress().getCountry()}"/>
				</td>
				</tr>
				
				<tr>
				<td>Zipcode:</td>
				<td>
				<f:input path="branchAddress.zipcode"  value="${branch.getBranchAddress().getZipcode()}"/>
				</td>
				</tr>
				

				<tr>
					<td colspan="3" align="center"><input type="submit"
						value="submit"  class="btn btn-primary"/></td>
				</tr>

			</table>
		</f:form>
	</div>
	<p></p>
	<p></p>
	<div class="container-sm" align="center">
		<table class="table table-bordered border-primary">
			<thead>
				<tr>
					<td>Branch Id</td>
					<td>Branch Name</td>
					<td>Address Line 1</td>
					<td>Address Line 2</td>
					<td>City</td>
					<td>State</td>
					<td>Country</td>
				</tr>
			</thead>
			<c:forEach items="${branches}" var="branch">

				<tr>
					<td>${branch.getBranchId() }</td>
					<td>${branch.getBranchName() }</td>
					<td>${branch.getBranchAddress().getAddressLine1() }</td>
					<td>${branch.getBranchAddress().getAddressLine2() }</td>
					<td>${branch.getBranchAddress().getCity() }</td>
					<td>${branch.getBranchAddress().getState() }</td>
					<td>${branch.getBranchAddress().getCountry() }</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>
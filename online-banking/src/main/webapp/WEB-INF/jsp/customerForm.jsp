<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Customer Form</title>
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
		<td><a href="accountForm">Account Form</a></td><td>|</td>
		<td><a href="branchForm">Branch Form</a></td><td>|</td>
		<td><a href="roleForm">Role Form</a></td><td>|</td>
		<td><a href="userForm">User Form</a></td><td>|</td>
		<td><a href="customerForm">Customer Form</a></td><td>|</td>
		<sec:authorize access="isAuthenticated()">
		<td>|</td>
		<td><a href="logout">Logout</a></td>
		</sec:authorize>
		</tr>
	</table>
</div>
<body>
	<div align="center">
		<h1>Customer Form</h1>
		<f:form action="saveCustomer" modelAttribute="customer">
			<table>
			
				<tr>
					<td>Customer Id:</td>
					<td><f:input path="customerId" value="${customer.customerId}"/></td>
					<td><f:errors path="customerId" cssClass="error" /></td>
				</tr> 
				

				<tr>
					<td>Customer Name:</td>
					<td><f:input path="customerName" value="${customer.customerName}"/></td>
					<td><f:errors path="customerName" cssClass="error" /></td>
				</tr>
				
				<tr>
				<td>Gender: </td>
				<td>
				<c:forEach items="${genders}" var="g">
					<c:choose>
						<c:when test="${selectedGender==g}">
							<f:radiobutton   path="gender" name="gender"  value="${g}" label="${g}" checked="true"/>
						</c:when>
						<c:otherwise>
							<f:radiobutton   path="gender" name="gender"  value="${g}" label="${g}" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				</tr>
				
				<tr>
					<td>DOB</td><td><f:input type="date"  path="customerDOB" name="customerDOB"  value="${customer.getCustomerDOB()}"/></td>
				</tr>
				
				
				<tr>
					<td>Mobile No:</td>
					<td>
						<f:input path="customerMobileNo" value="${customer.getCustomerMobileNo()}"/>
					</td>
				</tr>
				
				<tr>
				<td>AddressLine1:</td>
				<td>
				<f:input path="customerAddress.addressLine1" value="${customer.getCustomerAddress().getAddressLine1()}"/>
				<td><f:errors path="customerAddress.addressLine1" cssClass="error" /></td>
				</td>
				</tr>
				
				<tr>
				<td>AddressLine2:</td>
				<td>
				<f:input path="customerAddress.addressLine2" value="${customer.getCustomerAddress().getAddressLine2()}"/>
				<td><f:errors path="customerAddress.addressLine2" cssClass="error" /></td>
				</td>
				</tr>
				
				<tr>
				<td>City:</td>
				<td>
				<f:input path="customerAddress.city"  value="${customer.getCustomerAddress().getCity()}"/>
				<td><f:errors path="customerAddress.city" cssClass="error" /></td>
				</td>
				</tr>
				
				<tr>
				<td>State:</td>
				<td>
				<f:input path="customerAddress.state"  value="${customer.getCustomerAddress().getState()}"/>
				<td><f:errors path="customerAddress.state" cssClass="error" /></td>
				</td>
				</tr>
				
				<tr>
				<td>Country:</td>
				<td>
				<f:input path="customerAddress.country"  value="${customer.getCustomerAddress().getCountry()}"/>
				<td><f:errors path="customerAddress.country" cssClass="error" /></td>
				</td>
				</tr>
				
				<tr>
				<td>Zipcode:</td>
				<td>
				<f:input path="customerAddress.zipcode"  value="${customer.getCustomerAddress().getZipcode()}"/>
				<td><f:errors path="customerAddress.zipcode" cssClass="error" /></td>
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
					<td>Customer Id</td>
					<td>Customer Name</td>
					<td>Gender</td>
					<td>DOB</td>
					<td>Mobile No</td>
					<td>Address Line 1</td>
					<td>Address Line 2</td>
					<td>City</td>
					<td>State</td>
					<td>Country</td>
					<td>ZipCode</td>
				</tr>
			</thead>
			<c:forEach items="${customers}" var="customer">

				<tr>
					<td>${customer.getCustomerId() }</td>
					<td>${customer.getCustomerName() }</td>
					<td>${customer.getGender() }</td>
					<td>${customer.getCustomerDOB() }</td>
					<td>${customer.getCustomerMobileNo() }</td>
					<td>${customer.getCustomerAddress().getAddressLine1() }</td>
					<td>${customer.getCustomerAddress().getAddressLine2() }</td>
					<td>${customer.getCustomerAddress().getCity() }</td>
					<td>${customer.getCustomerAddress().getState() }</td>
					<td>${customer.getCustomerAddress().getCountry() }</td>
					<td>${customer.getCustomerAddress().getZipcode() }</td>
					<td><a href="updateCustomer?customerId=${customer.getCustomerId()}">Update</a></td>
					<td><a href="deleteCustomer?customerId=${customer.getCustomerId()}">Delete</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>
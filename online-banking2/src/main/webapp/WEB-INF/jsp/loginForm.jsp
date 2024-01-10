<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
</head>
<body>
	<div align="center">
		<h1>Login Form</h1>
		<table>
		<tr class="alert alert-info">
		<td>${message}</td>
		</tr>
		</table>
		<p/>
		<form action="login" method="POST">
			<table border="1">

				<tr>
					<td>User Name:</td>
					<td><input type="text" name="username"></td>
				</tr>


				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" value="submit" class="btn btn-lg btn-primary btn-block"></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>
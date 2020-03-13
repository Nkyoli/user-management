<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/loginpage.css">
<title>Login Page</title>
</head>
<body>
	<img src="img/wave.svg" alt="Waves" class="wave">
	<div class="container">
		<h1>Welcome, ${firstName}</h1>
		<div class="div_btn">
			<a href="#" class="btn"><span>Add User </span> <ion-icon
					name="add-circle"></ion-icon></a> <a href="index.html" class="btn"><span>log
					Out</span> <ion-icon name="log-out"></ion-icon></a>
		</div>
		<div class="div-table">
			<table>
				<tr class="table_head">
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Address</th>
					<th>Phone Number</th>
					<th>Birth Date</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.userName}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.address}</td>
						<td>${user.phoneNumber}</td>
						<td>${user.birthDate}</td>
						<td><ion-icon name="create" class="edit"></ion-icon> <ion-icon
							name="trash-sharp" class="delete"></ion-icon></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>
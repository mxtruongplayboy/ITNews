<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang đăng nhập</title>
<link rel="stylesheet" href="../assets/css/login.css" />
</head>
<body>
	<div class="login-container">
		<h2>Đăng nhập</h2>
		<form action="../account/checkLogin" method="POST" class="login-form">
			<div class="form-group">
				<label for="username">Tên đăng nhập:</label> <input type="text"
					id="username" name="username" required />
			</div>
			<div class="form-group">
				<label for="password">Mật khẩu:</label> <input type="password"
					id="password" name="password" required />
			</div>
			<div><p style="color: red;">Tên tài khoản hoặc mật khẩu sai</p></div>
			<div><p style="color: red;">Tài khoản đang bị khoá</p></div>
			<div class="form-group">
				<button type="submit">Đăng nhập</button>
			</div>
		</form>
	</div>
</body>
</html>
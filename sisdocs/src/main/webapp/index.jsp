<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/logincadastro.css" type="text/css">
<title>sisdocs | Login</title>
</head>
<body>
	<form action="ServletUserLogin" method="GET">
		<label for="nome">Login</label>
		<input id="login" type="text" name="login" required>
		<label for="senha">Senha</label>
		<input id="senha" type="password" name="senha" required>
		<input type="submit" value="Login">
		
		<a href="cadastrocliente.jsp">Criar uma conta</a>
	</form>
	<div style="display: flex;align-items: center;flex-direction: column;">
		<span style="font-size:13px;font-style:italic;color:lime;">
			# ${msg}
		</span>
	</div>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/logincadastro.css" type="text/css">
<title>sisdocs | Criar Conta</title>
</head>
<body>
	<form action="ServletCreateUser" method="POST">
		<label for="nome">Nome</label>
		<input id="nome" type="text" name="nome" required>
		
		<label for="nome">Login</label>
		<input id="login" type="text" name="login" required>
		
		<label for="senha">Senha</label>
		<input id="senha" type="password" name="senha" required>
			
		<input type="submit" value="Criar conta">
		<a href="index.jsp">Já tenho uma conta</a>
	</form>
</body>
</html>
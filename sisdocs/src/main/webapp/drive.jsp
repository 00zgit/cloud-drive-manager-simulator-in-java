<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	background-color: black;
	margin: 5px;
}

* {
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	
	background-color: black;
	color: #fff;
	
	margin:0;
	padding:0;
}

hr{
	margin: 10px 0px;
}

h1,h2 {
	margin-bottom: 4px;
	white-space: nowrap;
	cursor: default;
}
/*$page*/


/************************
*						*
*		TABLE			*				
*						*
************************/
/**/
table {
	margin: auto;
	justify-content: center;
	align-items:center;
	
	border-collapse: collapse;
	letter-spacing: 1px;
	font-size: 0.8rem;
}

td, th {
	width: 300px;
	padding: 10px 20px;
}

th {
	cursor: default;
	border: 1px solid rgb(190, 190, 190);
}

td {
	text-align: center;
	white-space: nowrap;
	transition: all 0.2s;
}
td:first-child{
	padding-inline-start: 2em;
	text-align: start;
}

td:hover {
	color: lime;
	background-color: rgba(230, 230, 230, 0.1);
	cursor: pointer;
	transition: all 0.2s;
}
/*$page*/


/************************
*						*
*		FORMS			*				
*						*
************************/
/**/
form {
	display: inline-block;
	
	border-right: 1px solid #fff;
	
	height: auto;
	padding-right: 10px;
	margin: 6px 10px 0 0;
}

select{
	max-width: 110px;
}

.btn {
	border: 1px solid lime;
	background-color: lime;
	color: rgb(0,0,0);
	
	text-align: center;
	
	font-weight: 600;
	text-decoration: none;
	cursor: pointer;
	text-transform:uppercase;
	
	display: inline-block;
	
	padding: 1px 8px;
	
	margin: 4px 2px;
}
.btn-hover {
	transition-duration: 0.4s;
}
.btn-hover:hover {
	color: #fff;
	background-color: black;
}
/*$page*/
</style>
<title>sisdocs | Drive Manager</title>
</head>
<body>

	<h1>Drive Manager</h1>
	<h2>Index of <strong style="color:lime;">${location}</strong></h2>

	<div class="form">
	
	<!-- FORMULÁRIO PARA LISTAR TODAS AS PASTAS CRIADAS PELO USUÁRIO -->
		<form method="GET" action="ServletListAllFolders">
			<input type="submit" value="LIST FOLDERS" class="btn-hover btn">
		</form>
	
	
	<!-- FORMULÁRIO PARA ADICIONAR UMA PASTA DO USUÁRIO -->
		<form method="POST" action="ServletAddFolder">
			<input type="text" name="folderName" required>
			<input type="submit" value="Add Folder" class="btn-hover btn">
		</form>
		
		
	<!-- FORMULÁRIO PARA LISTAR OS ARQUIVOS DE QUALQUER PASTA SELECIONADA DO CLIENTE -->
		<form method="GET" action="ServletListFolder">
			<select required name="folderId">
				<option disabled selected>Select a folder</option>
				<c:forEach items="${folderList}" var="folder">
					<option value="${folder.id}"> ${folder.nome} </option>
				</c:forEach>
			</select>
			<input type="submit" value="List" class="btn-hover btn">
		</form>
		
		
	<!-- FORMULÁRIO PARA UPAR (O NOME DE) UM ARQUIVO NA PASTA SELECIONADA -->
		<form method="POST" action="ServletAddFile" enctype="multipart/form-data">
			<select required name="folderId">
				<option disabled selected>Select a folder</option>
				<c:forEach items="${folderList}" var="folder">
					<option value="${folder.id}"> ${folder.nome} </option>
				</c:forEach>
			</select>
			<input type="file" name="file">
			<input type="submit" value="Upload" class="btn-hover btn">
		</form>
		
		
	<!-- FORMULÁRIO PARA DELETAR UM ARQUIVO DA PASTA ATUAL -->
		<form method="GET" action="ServletDeleteFile">
			<select name="fileToDelete">
				<option value="" disabled selected>Select a file</option>
				<c:forEach items="${files}" var="file">
					<option><c:out value="${file.nome}"></c:out></option>
				</c:forEach>
			</select>
			<input type="submit" value="Delete" class="btn-hover btn">
		</form>
	</div>

	<hr>

	<div style="margin:0 0 15px 0;">
		<span style="font-size:13px;font-style:italic;color:lime;">
			# ${msg}
		</span>
	</div>

	<table>
		<thead>
			<tr class="header" id="theader">
				<th>Name</th>
				<th>Size</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${files}" var="file">
				<tr>
					<td><c:out value="${file.nome}"></c:out></td>
					<td><c:out value="${file.size}"></c:out></td>
				</tr>
			</c:forEach>		
		</tbody>
	</table>

</body>
</html>
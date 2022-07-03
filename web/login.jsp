<%@ page pageEncoding="GB18030"%>
 
<html> 
	<head>
		<title>JSP for UserForm form</title>
	</head>
	<body>
		<form method="post" action="./login">
			<label>
				Username:
				<input type="text" name="username"/>
			</label><br/>
			<label>
				Password:
				<input type="text" name="password"/>
			</label><br/>
			<input type="SUBMIT" name="submit" value="Submit"> 
		</form>
	</body>
</html>


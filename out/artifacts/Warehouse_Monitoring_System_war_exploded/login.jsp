<%--
  Created by IntelliJ IDEA.
  User: Wiede
  Date: 2022/7/5
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="./login">
    <label>
        username :
        <input type="text" name="username"/>
    </label><br/>
    <label>
        password :
        <input type="text" name="password"/>
    </label><br/>
    <input type="SUBMIT" name="submit" value="Submit">
</form>
</body>
</html>

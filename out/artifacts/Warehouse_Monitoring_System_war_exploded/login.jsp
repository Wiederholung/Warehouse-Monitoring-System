<%--
  Created by IntelliJ IDEA.
  User: Wiede
  Date: 2022/7/5
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./resources/css/style.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1 class="card_title">Login to your account</h1>
        <p class="card_title-info">Warehouse Monitoring System</p>
<%--        TODO 后端无法就收会话中的参数--%>
        <form class="card_form" method="post" action="./login">
            <div class="input">
                <input type="text" class="input_field" required name="username" />
                <label class="input_label">Username</label><br/>
            </div>

            <div class="input">
                <input type="password" class="input_field" required name="password" />
                <label class="input_label">Password</label><br/>
            </div>

            <input class="card_button" type="submit" value="Log in" name="submit" />
            <button class="card_button" type="button"
                    onclick="window.location.href='register.jsp'">
                Create an account
            </button>
        </form>
    </div>

</div>

</body>
</html>
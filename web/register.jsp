<%--
  Created by IntelliJ IDEA.
  User: xmzheng
  Date: 2022/7/5
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./resources/css/style.css">
</head>
<body1>
<div class="container">
    <div class="card">
        <h1 class="card_title">Register your account</h1>
        <p class="card_title-info">Warehouse Monitoring System</p>

        <form class="card_form" method="post" action="./register">
            <div class="input">
                <input type="text" class="input_field" required name="username" id="username" />
                <label class="input_label">Username</label><br/>
            </div>
            <div class="input">
                <input type="password" class="input_field" required name="password" id="password" />
                <label class="input_label">Password</label><br/>

            </div>
            <input class="card_button" type="SUBMIT" value="Create" name="create" />
            <button class="card_button" type="button"
                    onclick="window.location.href='login.jsp'">
                Return
            </button>
        </form>
    </div>
</div>
</body1>
</html>

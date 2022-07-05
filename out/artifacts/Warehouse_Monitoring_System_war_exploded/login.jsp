<%--
  Created by IntelliJ IDEA.
  User: xmzheng
  Date: 2022/7/4
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"><link rel="stylesheet" href="./style.css">

</head>
    <body>
    <!-- partial:index.partial.html -->
        <div class="container">
            <div class="card">
                <h1 class="card_title">Login to your account</h1>
                <p class="card_title-info">Warehouse Monitoring System</p>
                <form class="card_form" method="post" action="./login">
                    <div class="input">
                        <input type="username" class="input_field" required />
                        <label class="input_label">Username</label><br/>
                    </div>
                    <div class="input">
                        <input type="password" class="input_field" required />
                        <label class="input_label">Password</label><br/>

                    </div>
                    <input class="card_button" type="submit" value="log in" name="submit" />
                    <button class="card_button" color="grey">Create an account</button>
                </form>
            </div>
        </div>
    </body>
</html>

<%@ page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Welcome</title>
  </head>
  
  <body>
    Welcome, <%= (String)session.getAttribute("username") %>!
  </body>
</html>
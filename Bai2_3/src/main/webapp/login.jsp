<%--
  Created by IntelliJ IDEA.
  User: nhatbaodinh
  Date: 19/2/25
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%
  String username = request.getParameter("username");
  String password = request.getParameter("password");
  if ("ABC".equals(username) && "MNK".equals(password)) {
    response.sendRedirect("UserProfile.html");
  } else {
    response.sendRedirect("login.html");
  }
%>
</body>
</html>

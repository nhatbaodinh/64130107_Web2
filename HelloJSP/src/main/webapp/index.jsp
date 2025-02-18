<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello JSP Example</title>
</head>
<body>
<h1><%= "HELLO JSP - 64.CNTT - CLC" %></h1>
<br/>
<%= new Date().toString()%>
<%
int x, y;
x = 5;
y = x + 10;
%>
<%=y%>
<% out.print(y); %>
</body>
</html>
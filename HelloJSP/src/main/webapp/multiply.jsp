<%--
  Created by IntelliJ IDEA.
  User: nhatbaodinh
  Date: 19/2/25
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Multiply</title>
</head>
<body>
<%
  //Lay gia tri tham so tu URL
  //http://localhost:8080/multiply.jsp?a=5&b=6
  String strA = request.getParameter("a");
  int valueA = Integer.parseInt(strA);
  String strB = request.getParameter("b");
  int valueB = Integer.parseInt(strB);
  out.print("a = " + valueA + "<br>");
  out.print("b = " + valueB + "<br>");
  out.print("a x b = " + (valueA * valueB) + "<br>");
%>
</body>
</html>


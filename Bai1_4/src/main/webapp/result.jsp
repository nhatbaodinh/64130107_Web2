<%--
  Created by IntelliJ IDEA.
  User: nhatbaodinh
  Date: 17/2/25
  Time: 00:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Kết quả BMI</title>
  <style>
    body { font-family: Arial, sans-serif; text-align: center; padding: 50px; background-color: #f8f9fa; }
    .result-box { background: white; padding: 20px; border-radius: 10px; display: inline-block; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
    h2 { color: #333; }
    p { font-size: 18px; margin: 10px 0; }
    .back-btn { padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer; text-decoration: none; }
    .back-btn:hover { background-color: #0056b3; }
  </style>
</head>
<body>
<div class="result-box">
  <h2>Kết quả BMI</h2>
  <p><strong>BMI:</strong> <%= request.getAttribute("bmi") %></p>
  <p><strong>Phân loại:</strong> <%= request.getAttribute("category") %></p>
  <a href="index.jsp" class="back-btn">Tính lại</a>
</div>
</body>
</html>

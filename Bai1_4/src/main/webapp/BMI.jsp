<%--
  Created by IntelliJ IDEA.
  User: nhatbaodinh
  Date: 17/2/25
  Time: 00:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tính chỉ số BMI</title>
  <style>
    body { font-family: Arial, sans-serif; text-align: center; padding: 50px; background-color: #f8f9fa; }
    form { background: white; padding: 20px; border-radius: 10px; display: inline-block; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
    input { margin: 10px; padding: 10px; width: 200px; }
    button { padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer; }
    button:hover { background-color: #0056b3; }
  </style>
</head>
<body>
<h2>Tính chỉ số BMI</h2>
<form action="BMI" method="POST">
  <label>Chiều cao (cm):</label><br>
  <input type="text" name="height" required><br>
  <label>Cân nặng (kg):</label><br>
  <input type="text" name="weight" required><br>
  <button type="submit">Tính BMI</button>
</form>
</body>
</html>

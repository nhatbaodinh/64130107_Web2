<%--
  Created by IntelliJ IDEA.
  User: nhatbaodinh
  Date: 17/2/25
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thông tin cá nhân</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f8f9fa;
      font-family: 'Arial', sans-serif;
    }
    .profile-card {
      max-width: 500px;
      margin: 50px auto;
      padding: 20px;
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    .profile-card img {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      object-fit: cover;
    }
    h1 {
      font-size: 24px;
      color: #333;
    }
    p {
      font-size: 18px;
      color: #555;
      margin: 5px 0;
    }
  </style>
</head>
<body>

<div class="profile-card">
  <img src="${anh}" alt="Avatar">
  <h1>${hoTen}</h1>
  <p><strong>MSSV:</strong> ${mssv}</p>
  <p><strong>Lớp:</strong> ${lop}</p>
  <p><strong>Trường:</strong> ${truong}</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


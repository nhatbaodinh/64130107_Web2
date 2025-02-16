package ntu.edu.vn.bai1_4;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BMI")
public class BMIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/BMI.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double height = Double.parseDouble(request.getParameter("height"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            height /= 100;
            double bmi = weight / (height * height);
            String category;

            if (bmi < 18.5) category = "Gầy";
            else if (bmi < 24.9) category = "Bình thường";
            else if (bmi < 29.9) category = "Thừa cân";
            else category = "Béo phì";

            request.setAttribute("bmi", String.format("%.2f", bmi));
            request.setAttribute("category", category);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dữ liệu nhập không hợp lệ. Vui lòng nhập số.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

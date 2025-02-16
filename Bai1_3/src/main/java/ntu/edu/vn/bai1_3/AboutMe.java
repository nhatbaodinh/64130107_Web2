package ntu.edu.vn.bai1_3;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AboutMe")
public class AboutMe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hoTen", "Đinh Nhật Bảo");
        request.setAttribute("mssv", "64130107");
        request.setAttribute("lop", "64.CNTT - CLC1");
        request.setAttribute("truong", "Trường Đại học Nha Trang - NTU");
        request.setAttribute("anh", "https://cdn.haitrieu.com/wp-content/uploads/2022/03/Logo-DH-Nha-Trang-NTU-1.png");

        request.getRequestDispatcher("/AboutMe.jsp").forward(request, response);
    }
}

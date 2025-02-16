package ntu.edu.vn.bai1_2;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/testdopost")
public class testDoPost extends HttpServlet {
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter traVe = response.getWriter();
        traVe.append("Bạn đã gửi request bằng phương thức GET, đây là đáp ứng của chúng tôi");
        String noiDungHTML = "<html><head><title>Test DoPost</title></head><body>"
                + "<form method='post' action='testdopost'>"
                + "<label for='name'>Họ và tên:</label>"
                + "<input type='text' id='name' name='name'><br><br>"
                + "<label for='age'>Tuổi:</label>"
                + "<input type='text' id='age' name='age'><br><br>"
                + "<input type='submit' value='Gửi'>"
                + "</form></body></html>";
        traVe.append(noiDungHTML);
    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter traVe = response.getWriter();
        traVe.append("Bạn đã gửi request bằng phương thức POST, đây là đáp ứng của chúng tôi");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        traVe.append("<br><br>Họ và tên: " + name + "<br>Tuổi: " + age);
    }
}
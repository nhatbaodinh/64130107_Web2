package ntu.edu.vn.bai1_1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/subpage")
public class subpage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("param1", request.getParameter("value1"));
        request.setAttribute("param2", request.getParameter("value2"));
        request.getRequestDispatcher("/subpage.jsp").forward(request, response);
    }
}


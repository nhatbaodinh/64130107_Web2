package controllers;

import jakarta.servlet.http.HttpServletRequest;
import models.StudentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DisplayData {
  @RequestMapping("/")
  public String display(ModelMap MSSV, ModelMap hoTen, ModelMap namSinh, ModelMap gioiTinh) {
    MSSV.addAttribute("MSSV", "64130107");
    hoTen.addAttribute("hoTen", "Đinh Nhật Bảo");
    namSinh.addAttribute("namSinh", "2004");
    gioiTinh.addAttribute("gioiTinh", "Nam");
    return "index";
  }

  @RequestMapping("/truyenobject")
  public String truyenObject(ModelMap model) {
    StudentModel student = new StudentModel("64130107", "Đinh Nhật Bảo", true, 2004);
    model.addAttribute("student", student);
    return "object";
  }

  @RequestMapping("/truyendsobject")
  public String truyenDSObject(ModelMap model) {
    List<StudentModel> students = new ArrayList<>();
    students.add(new StudentModel("64130107", "Đinh Nhật Bảo", true, 2004));
    students.add(new StudentModel("64130108", "Nguyễn Văn A", true, 2004));
    students.add(new StudentModel("64130109", "Trần Thị B", false, 2004));
    model.addAttribute("students", students);
    return "dsobject";
  }

  @RequestMapping("/login")
  public String login(HttpServletRequest request, ModelMap model) {
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");

    if (id != null && !id.isEmpty()) {
      model.addAttribute("id", id);
    }
    return "user";
  }
}

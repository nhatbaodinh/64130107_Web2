package nhatbaodinh.tonghopgk.controllers;

import nhatbaodinh.tonghopgk.models.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/about")
  public String about(ModelMap model) {
    SinhVien student = new SinhVien();
    student.setMSSV("64130107");
    student.setHoTen("Đinh Nhật Bảo");
    student.setDiemTB(9.0);
    model.addAttribute("student", student);
    return "about";
  }

  @GetMapping("/list")
  public String list() {
    return "list";
  }

  @GetMapping("/news")
  public String news() {
    return "news";
  }
}

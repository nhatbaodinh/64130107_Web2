package nhatbaodinh.tonghopgk.controllers;

import models.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/about")
  public String about(ModelMap model) {
    SinhVien student = new SinhVien("64130107", "Đinh Nhật Bảo", 9.0);
    model.addAttribute("student", student);
    return "about";
  }

  @GetMapping("/list")
  public String list(ModelMap model) {
    List<SinhVien> dsSinhVien = List.of(
        new SinhVien("64130107", "Đinh Nhật Bảo", 9.2),
        new SinhVien("64130108", "Nguyễn Văn A", 8.1),
        new SinhVien("64130109", "Trần Thị B", 7.1),
        new SinhVien("64130110", "Lê Văn C", 6.1),
        new SinhVien("64130111", "Phạm Thị D", 5.1)
    );
    model.addAttribute("dsSinhVien", dsSinhVien);
    return "list";
  }

  @GetMapping("/news")
  public String news() {
    return "news";
  }
}

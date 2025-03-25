package nhatbaodinh.tonghopgk.controllers;

import models.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
  @GetMapping("/")
  public String index(@RequestParam(name = "page", required = false, defaultValue = "home") String page, ModelMap model) {
    model.addAttribute("page", page);

    if(page.equals("about")) {
      SinhVien student = new SinhVien("64130107", "Đinh Nhật Bảo", 9.0);
      model.addAttribute("student", student);
    }
    if(page.equals("list")) {
      List<SinhVien> dsSinhVien = List.of(
          new SinhVien("64130107", "Đinh Nhật Bảo", 9.2),
          new SinhVien("64130108", "Nguyễn Văn A", 8.1),
          new SinhVien("64130109", "Trần Thị B", 7.1),
          new SinhVien("64130110", "Lê Văn C", 6.1),
          new SinhVien("64130111", "Phạm Thị D", 5.1)
      );
      model.addAttribute("dsSinhVien", dsSinhVien);
    }

    return "index";
  }
}

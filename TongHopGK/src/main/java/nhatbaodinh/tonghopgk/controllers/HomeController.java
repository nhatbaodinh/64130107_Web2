package nhatbaodinh.tonghopgk.controllers;

import models.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
  private final List<SinhVien> dsSinhVien = new ArrayList<>();

  public HomeController() {
    dsSinhVien.add(new SinhVien("64130107", "Đinh Nhật Bảo", 9.5));
    dsSinhVien.add(new SinhVien("SV001", "Nguyen Van A", 8.5));
    dsSinhVien.add(new SinhVien("SV002", "Tran Thi B", 7.5));
    dsSinhVien.add(new SinhVien("SV003", "Le Van C", 6.5));
  }

  @GetMapping("/")
  public String index(@RequestParam(name = "page", required = false, defaultValue = "home") String page, ModelMap model) {
    model.addAttribute("page", page);

    if(page.equals("about")) {
      model.addAttribute("mssv", "64130107");
      model.addAttribute("hoTen", "Đinh Nhật Bảo");
      model.addAttribute("diemTB", 9.5);
    }
    else if(page.equals("list")) {
      model.addAttribute("dsSinhVien", dsSinhVien);
    }
    else if(page.equals("addnew")) {
      model.addAttribute("student", new SinhVien());
    }
    return "index";
  }

  @PostMapping("/addStudent")
  public String addStudent(@ModelAttribute SinhVien student) {
    dsSinhVien.add(student);
    return "redirect:/?page=list";
  }
}
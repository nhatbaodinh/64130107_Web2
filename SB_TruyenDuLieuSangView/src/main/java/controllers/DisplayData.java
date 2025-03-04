package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

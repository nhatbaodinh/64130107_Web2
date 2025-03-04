package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChaoController {
  @RequestMapping("/chao")
  public String Chao() {
    return "chao";
  }
  @RequestMapping("/tambiet")
  public String tamBiet(ModelMap model) {
    model.addAttribute("message", "Tạm biệt");
    return "tambiet";
  }
}
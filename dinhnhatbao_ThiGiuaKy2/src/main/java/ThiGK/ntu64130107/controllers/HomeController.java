package ThiGK.ntu64130107.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String getIndex(ModelMap studentId, ModelMap fullName, ModelMap dayOfBirth, ModelMap groupClass) {
    studentId.addAttribute("studentId", "64130107");
    fullName.addAttribute("fullName", "Đinh Nhật Bảo");
    dayOfBirth.addAttribute("dayOfBirth", "14/02/2004");
    groupClass.addAttribute("groupClass", "64.CNTT-CLC");
    return "index";
  }
}

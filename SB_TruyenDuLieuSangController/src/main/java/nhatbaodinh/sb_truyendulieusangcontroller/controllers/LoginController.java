package nhatbaodinh.sb_truyendulieusangcontroller.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

  @RequestMapping("/login")
  public String loginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String login(HttpServletRequest request, ModelMap model) {
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");

    if (!id.isEmpty()) {
      model.addAttribute("id", id);
      return "user";
    } else {
      return "login";
    }
  }
}

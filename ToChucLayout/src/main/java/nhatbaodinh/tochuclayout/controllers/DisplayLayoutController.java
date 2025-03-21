package nhatbaodinh.tochuclayout.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayLayoutController {
  @RequestMapping("/")
  public String displayLayout() {
    return "index";
  }
}
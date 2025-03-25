package nhatbaodinh.tonghopgk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TongHopGKController {
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/about")
  public String about() {
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

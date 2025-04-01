package thiGK.ntu64130107.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import thiGK.ntu64130107.model.Topic;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PathController {
  private final List<Topic> topics = new ArrayList<>();

  public PathController() {
    topics.add(new Topic("1", "Java Spring Boot", "Xây dựng ứng dụng Web", "GV01", "Web"));
    topics.add(new Topic("2", "Machine Learning", "Dự đoán dữ liệu", "GV02", "AI"));
    topics.add(new Topic("3", "Mobile App", "Xây dựng app di động", "GV03", "Mobile"));
  }

  public String index(@RequestParam(name = "page", required = false, defaultValue = "home") String page, ModelMap model) {
    model.addAttribute("page", page);

    if ("topic-all".equals(page)) {
      model.addAttribute("topics", topics);
    }

    return "index";
  }
}
package thiGK.ntu64130107.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thiGK.ntu64130107.model.Topic;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PathController {
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/topic/all")
  public String topicAll(Model model) {
    List<Topic> topics = new ArrayList<>();
    topics.add(new Topic("1", "Java Spring Boot", "Xây dựng ứng dụng Web", "GV01", "Web"));
    topics.add(new Topic("2", "Machine Learning", "Dự đoán dữ liệu", "GV02", "AI"));
    topics.add(new Topic("3", "Mobile App", "Xây dựng app di động", "GV03", "Mobile"));

    model.addAttribute("topics", topics);
    return "layout/topic-all";
  }
}

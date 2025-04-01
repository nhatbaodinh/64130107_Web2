package thiGK.ntu64130107.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("topics", topics); // Đảm bảo danh sách được thêm vào model
    return "index";
  }

  @GetMapping("/topic/all")
  public String getAllTopics(Model model) {
    model.addAttribute("topics", topics);
    return "fragments/topic-all";
  }

  @GetMapping("/topic/new")
  public String newTopic(Model model) {
    model.addAttribute("topic", new Topic());
    return "fragments/topic-form";
  }

  @PostMapping("/topic/save")
  public String saveTopic(@ModelAttribute Topic topic, Model model) {
    topics.add(topic); // Lưu vào danh sách (tạm thời)
    model.addAttribute("topics", topics);
    return "index"; // Hiển thị lại trang chính với danh sách mới
  }
  
  @GetMapping("/topic/view/id")
  public String viewTopic(Model model) {
    model.addAttribute("topic", new Topic());
    return "fragments/topic-view";
  }

  @GetMapping("/topic/delete/id")
  public String deleteTopic(Model model) {
    model.addAttribute("topic", new Topic());
    return "fragments/topic-delete";
  }
}

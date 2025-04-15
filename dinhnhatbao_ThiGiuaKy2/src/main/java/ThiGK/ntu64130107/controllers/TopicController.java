package ThiGK.ntu64130107.controllers;

import ThiGK.ntu64130107.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicController {
  private final List<Topic> topics = new ArrayList<>();

  public TopicController(){
    topics.add(new Topic("1", "Topic 1", "Description 1", "Supervisor 1", "Type 1"));
    topics.add(new Topic("2", "Topic 2", "Description 2", "Supervisor 2", "Type 2"));
    topics.add(new Topic("3", "Topic 3", "Description 3", "Supervisor 3", "Type 3"));
    topics.add(new Topic("4", "Topic 4", "Description 4", "Supervisor 4", "Type 4"));
    topics.add(new Topic("5", "Topic 5", "Description 5", "Supervisor 5", "Type 5"));
  }

  @GetMapping("/topic/all")
  public String getAllTopics(Model model) {
    model.addAttribute("topics", topics);
    return "topicList";
  }

  @GetMapping("/topic/new")
  public String getNewTopic(Model model) {
    model.addAttribute("topic", new Topic());
    return "topicForm";
  }

  @PostMapping("/topic/new")
  public String postNewTopic(@ModelAttribute Topic topic) {
    topics.add(topic);
    return "redirect:/topic/all";
  }

  @GetMapping("/topic/view/{id}")
  public String getTopicById(@PathVariable String id, Model model) {
    Topic found = topics.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
    model.addAttribute("topic", found);
    return "topicView";
  }

  @GetMapping("/topic/edit/{id}")
  public String getEditTopic(@PathVariable String id, Model model) {
    Topic found = topics.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
    model.addAttribute("topic", found);
    return "topicEdit";
  }

  @PostMapping("/topic/edit/{id}")
  public String postEditTopic(@PathVariable String id, @ModelAttribute Topic topic) {
    Topic found = topics.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
    if (found != null) {
      found.setTopicName(topic.getTopicName());
      found.setTopicDescription(topic.getTopicDescription());
      found.setSupervisorId(topic.getSupervisorId());
      found.setTopicType(topic.getTopicType());
    }
    return "redirect:/topic/all";
  }

  @GetMapping("/topic/delete/{id}")
  public String getDeleteTopic(@PathVariable String id) {
    topics.removeIf(t -> t.getId().equals(id));
    return "redirect:/topic/all";
  }
}

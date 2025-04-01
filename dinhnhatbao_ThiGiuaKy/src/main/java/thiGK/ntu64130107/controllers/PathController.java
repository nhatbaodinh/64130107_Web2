package thiGK.ntu64130107.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import thiGK.ntu64130107.model.Student;
import thiGK.ntu64130107.model.Topic;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PathController {
  private final List<Topic> topics = new ArrayList<>();
  private final List<Student> students = new ArrayList<>();

  public PathController() {
    topics.add(new Topic("1", "Java Spring Boot", "Xây dựng ứng dụng Web", "GV01", "Web"));
    topics.add(new Topic("2", "Machine Learning", "Dự đoán dữ liệu", "GV02", "AI"));
    topics.add(new Topic("3", "Mobile App", "Xây dựng app di động", "GV03", "Mobile"));

    students.add(new Student("1", "Nguyễn Văn A", "1"));
    students.add(new Student("2", "Trần Thị B", "2"));
    students.add(new Student("3", "Lê Văn C", "3"));
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("topics", topics);
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
    topics.add(topic);
    model.addAttribute("topics", topics);
    return "fragments/topic-all";
  }

  @GetMapping("/topic/view/{id}")
  public String viewTopic(@PathVariable String id, Model model) {
    Topic topic = topics.stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
    model.addAttribute("topic", topic);
    return "fragments/topic-view";
  }

  @GetMapping("/topic/delete/{id}")
  public String deleteTopic(@PathVariable String id, Model model) {
    topics.removeIf(t -> t.getId().equals(id));
    model.addAttribute("topics", topics);
    return "index";
  }

  @GetMapping("/student/all")
  public String getAllStudents(Model model) {
    model.addAttribute("students", students);
    return "fragments/student-all";
  }

  @GetMapping("/student/new")
  public String newStudent(Model model) {
    model.addAttribute("student", new Student());
    return "fragments/student-form";
  }

  @PostMapping("/student/save")
  public String saveStudent(@ModelAttribute Student student, Model model) {
    students.add(student);
    model.addAttribute("students", students);
    return "fragments/student-all";
  }

  @GetMapping("/student/view/{id}")
  public String viewStudent(@PathVariable String id, Model model) {
    Student student = students.stream()
        .filter(s -> s.getId().equals(id))
        .findFirst()
        .orElse(null);
    model.addAttribute("student", student);
    return "fragments/student-view";
  }

  @GetMapping("/student/delete/{id}")
  public String deleteStudent(@PathVariable String id, Model model) {
    students.removeIf(s -> s.getId().equals(id));
    model.addAttribute("students", students);
    return "index";
  }
}

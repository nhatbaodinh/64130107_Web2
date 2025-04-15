package ThiGK.ntu64130107.controllers;

import ThiGK.ntu64130107.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
  private final List<Student> students = new ArrayList<>();

  public StudentController(){
    students.add(new Student("1", "Student 1", "Group 1"));
    students.add(new Student("2", "Student 2", "Group 2"));
    students.add(new Student("3", "Student 3", "Group 3"));
    students.add(new Student("4", "Student 4", "Group 4"));
    students.add(new Student("5", "Student 5", "Group 5"));
  }

  @GetMapping("/student/all")
  public String getAllStudents(Model model) {
    model.addAttribute("students", students);
    return "studentList";
  }

  @GetMapping("/student/new")
  public String getNewStudent(Model model) {
    model.addAttribute("student", new Student());
    return "studentForm";
  }

  @PostMapping("/student/new")
  public String postNewStudent(@ModelAttribute Student student) {
    students.add(student);
    return "redirect:/student/all";
  }

  @GetMapping("/student/view/{id}")
  public String getStudentById(@PathVariable String id, Model model) {
    Student found = students.stream()
        .filter(s -> s.getId().equals(id))
        .findFirst()
        .orElse(null);
    model.addAttribute("student", found);
    return "studentView";
  }

  @GetMapping("/student/edit/{id}")
  public String getEditStudent(@PathVariable String id, Model model) {
    Student found = students.stream()
        .filter(s -> s.getId().equals(id))
        .findFirst()
        .orElse(null);
    model.addAttribute("student", found);
    return "studentEdit";
  }

  @PostMapping("/student/edit/{id}")
  public String postEditStudent(@PathVariable String id, @ModelAttribute Student student) {
    Student found = students.stream()
        .filter(s -> s.getId().equals(id))
        .findFirst()
        .orElse(null);
    if (found != null) {
      found.setName(student.getName());
      found.setGroupId(student.getGroupId());
    }
    return "redirect:/student/all";
  }

  @GetMapping("/student/delete/{id}")
  public String getDeleteStudent(@PathVariable String id) {
    students.removeIf(s -> s.getId().equals(id));
    return "redirect:/student/all";
  }
}
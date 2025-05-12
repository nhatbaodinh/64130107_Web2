package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.dto.CategoryPostCount;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.CategoryRepository;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PathController {

  private final CategoryRepository categoryRepository;

  private final PostRepository postRepository;

  @GetMapping({"/about", "/about.html"})
  public String aboutPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    List<Object[]> postsByMonth = postRepository.findPostsCountByMonth();
    model.addAttribute("postsByMonth", postsByMonth);

    return "about";
  }

  @GetMapping({"/contact", "/contact.html"})
  public String contactPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    List<Object[]> postsByMonth = postRepository.findPostsCountByMonth();
    model.addAttribute("postsByMonth", postsByMonth);

    return "contact";
  }

  @GetMapping({"/single", "/single.html"})
  public String blogPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    List<Object[]> postsByMonth = postRepository.findPostsCountByMonth();
    model.addAttribute("postsByMonth", postsByMonth);

    return "single";
  }

  @GetMapping({"/travel", "/travel.html"})
  public String travelPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    List<Object[]> postsByMonth = postRepository.findPostsCountByMonth();
    model.addAttribute("postsByMonth", postsByMonth);

    return "travel";
  }

  @GetMapping({"/photography", "/photography.html"})
  public String photographyPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    List<Object[]> postsByMonth = postRepository.findPostsCountByMonth();
    model.addAttribute("postsByMonth", postsByMonth);

    return "photography";
  }

  @GetMapping({"/fashion", "/fashion.html"})
  public String fashionPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    List<Object[]> postsByMonth = postRepository.findPostsCountByMonth();
    model.addAttribute("postsByMonth", postsByMonth);

    return "fashion";
  }
}


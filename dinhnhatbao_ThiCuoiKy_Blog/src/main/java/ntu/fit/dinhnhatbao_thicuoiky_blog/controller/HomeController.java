package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.dto.CategoryPostCount;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Post;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.CategoryRepository;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final PostRepository postRepository;

  private final CategoryRepository categoryRepository;

  @GetMapping({"/", "/home", "/index", "/index.html"})
  public String indexPage(HttpSession session, Model model) {
    // Lấy user từ session (nếu có)
    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
      model.addAttribute("user", currentUser);
    }

    // Lấy danh sách bài viết
    List<Post> posts = postRepository.findAll();
    model.addAttribute("posts", posts);

    // Lấy danh sách các bài viết theo danh mục
    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    // Lấy số lượng bài viết theo tháng
    List<Object[]> postsByMonth = postRepository.findPostsCountByMonth();
    model.addAttribute("postsByMonth", postsByMonth);

    return "index";
  }
}
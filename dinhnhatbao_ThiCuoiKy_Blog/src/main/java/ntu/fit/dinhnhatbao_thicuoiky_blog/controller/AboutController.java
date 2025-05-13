package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.CategoryService;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutController {

  private final PostService postService;
  private final CategoryService categoryService;

  @GetMapping({"/about", "/about.html"})
  public String aboutPage(HttpSession session, Model model) {
    // Lấy user từ session (nếu có)
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    return "about";
  }
}

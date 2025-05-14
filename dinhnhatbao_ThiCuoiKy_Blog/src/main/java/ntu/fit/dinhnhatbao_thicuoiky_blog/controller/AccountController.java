package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.CategoryService;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccountController {

  private final PostService postService;
  private final CategoryService categoryService;

  @GetMapping("/account")
  public String accountPage(HttpSession session, Model model) {
    // Lấy user từ session (nếu có)
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));

    // Lấy số lượng bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    if (user != null) {
      return "account";
    }

    return "redirect:/login";
  }

  @GetMapping("/editProfile")
  public String editProfilePage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    if (user != null) {
      return "editProfile";
    }

    return "redirect:/login";
  }

  @PostMapping("/editProfile")
  public String saveProfile(@RequestParam("fullName") String fullName,
                            HttpSession session,
                            Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    if (user != null) {
      user.setFullName(fullName);
      session.setAttribute("user", user);
      return "redirect:/account";
    }

    return "redirect:/login";
  }
}
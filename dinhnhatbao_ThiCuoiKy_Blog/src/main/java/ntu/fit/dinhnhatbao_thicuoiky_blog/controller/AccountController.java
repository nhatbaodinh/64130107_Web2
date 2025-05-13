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
    model.addAttribute("categories", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    if (user != null) {
      model.addAttribute("user", user);
      return "account";  // Trang thông tin tài khoản của người dùng
    }

    return "redirect:/login";  // Nếu không đăng nhập, chuyển hướng đến trang login
  }

  @GetMapping("/editProfile")
  public String editProfilePage(HttpSession session, Model model) {
    // Lấy user từ session (nếu có)
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    if (user != null) {
      model.addAttribute("user", user);
      return "editProfile";  // Trả về trang chỉnh sửa hồ sơ
    }

    return "redirect:/login";  // Nếu không đăng nhập, chuyển hướng đến trang login
  }

  @PostMapping("/editProfile")
  public String saveProfile(@RequestParam("fullName") String fullName, HttpSession session, Model model) {
    // Lấy user từ session (nếu có)
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    if (user != null) {
      // Cập nhật họ tên mới cho người dùng
      user.setFullName(fullName);

      // Lưu lại thông tin người dùng đã thay đổi vào session (hoặc cơ sở dữ liệu nếu cần)
      session.setAttribute("user", user);

      // Chuyển hướng về trang thông tin tài khoản
      return "redirect:/account";
    }

    return "redirect:/login";  // Nếu không có người dùng trong session, chuyển hướng đến trang login
  }
}
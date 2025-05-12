package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.dto.CategoryPostCount;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

  private final CategoryRepository categoryRepository;

  @GetMapping("/account")
  public String accountPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    if (user != null) {
      model.addAttribute("user", user);
      return "account";  // Trang thông tin tài khoản của người dùng
    }

    return "redirect:/login";  // Nếu không đăng nhập, chuyển hướng đến trang login
  }

  @GetMapping("/editProfile")
  public String editProfilePage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");

    List<CategoryPostCount> categories = categoryRepository.findCategoryPostCounts();
    model.addAttribute("categories", categories);

    if (user != null) {
      model.addAttribute("user", user);
      return "editProfile";  // Trả về trang chỉnh sửa hồ sơ
    }

    return "redirect:/login";  // Nếu không đăng nhập, chuyển hướng đến trang login
  }

  @PostMapping("/editProfile")
  public String saveProfile(@RequestParam("fullName") String fullName, HttpSession session) {
    // Lấy thông tin người dùng từ session
    User user = (User) session.getAttribute("user");

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
package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpSession;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Role;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  // Trang đăng nhập
  @GetMapping("/login")
  public String loginPage() {
    return "login"; // Trả về trang login.html
  }

  // Trang đăng ký
  @GetMapping("/register")
  public String registerPage() {
    return "register"; // Trả về trang register.html
  }


  // Đăng nhập tài khoản
  @PostMapping("/login")
  public String login(@RequestParam String username,
                      @RequestParam String password,
                      HttpSession session,
                      RedirectAttributes redirectAttributes) {

    Optional<User> userOpt = userService.loginUser(username, password);

    if (userOpt.isPresent()) {
      User user = userOpt.get();

      if (!user.isEnabled()) {
        redirectAttributes.addFlashAttribute("error", "Tài khoản đã bị khóa.");
        return "redirect:/login";
      }

      // Lưu session người dùng sau khi đăng nhập
      session.setAttribute("user", user);
      return "redirect:/home";
    }

    redirectAttributes.addFlashAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
    return "redirect:/login";
  }

  // Đăng ký tài khoản
  @PostMapping("/register")
  public String register(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String fullName,
                         RedirectAttributes redirectAttributes) {

    try {
      if (userService.existsByUsername(username)) {
        redirectAttributes.addFlashAttribute("error", "Tên người dùng đã tồn tại.");
        return "redirect:/register";
      }

      User user = User.builder()
          .username(username)
          .password(password)
          .fullName(fullName)
          .role(Role.USER)
          .enabled(true)
          .build();

      userService.save(user);
      redirectAttributes.addFlashAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
      return "redirect:/login";

    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi đăng ký.");
      return "redirect:/register";
    }
  }

  // Đăng xuất tài khoản
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate(); // Hủy session hiện tại
    return "redirect:/login";
  }
}
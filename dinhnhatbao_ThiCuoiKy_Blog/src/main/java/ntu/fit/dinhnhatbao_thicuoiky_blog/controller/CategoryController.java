package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Category;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Role;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.CategoryService;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;
  private final PostService postService;

  // Danh sách danh mục
  @GetMapping({"/categories", "/categories.html"})
  public String categoryPage(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) {
      return "redirect:/";
    }
    
    // Lấy user từ session (nếu có)
    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
      model.addAttribute("user", currentUser);
    }

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));
    model.addAttribute("categories", categoryService.findAll());
    return "categories/all_categories";  // Đảm bảo trang JSP/Thymeleaf được sử dụng ở đây
  }

  // Thêm danh mục mới
  @PostMapping("/categories/new")
  @ResponseBody
  public ResponseEntity<String> newCategory(@RequestParam("name") String name) {
    if (categoryService.existsByName(name)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Category name already exists.");
    }

    Category category = new Category();
    category.setName(name.trim());
    categoryService.saveCategory(category);

    return ResponseEntity.ok("Category created successfully.");
  }

  // Cập nhật danh mục
  @PostMapping("/categories/update")
  public String editCategory(@RequestParam("id") Long id, @RequestParam("name") String name) {
    boolean isExist = categoryService.existsByName(name);
    if (isExist) {
      return "redirect:/categories?error=Danh mục đã tồn tại.";
    }
    categoryService.updateCategory(id, name);
    return "redirect:/categories";  // Chuyển hướng lại danh sách danh mục
  }

  // Xóa danh mục
  @PostMapping("/categories/delete/{id}")
  public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    if (categoryService.hasPosts(id)) {
      redirectAttributes.addFlashAttribute("error", "Không thể xóa danh mục vì đang thuộc bài viết.");
      return "redirect:/categories";
    }

    categoryService.deleteCategoryById(id);
    redirectAttributes.addFlashAttribute("success", "Xóa danh mục thành công.");
    return "redirect:/categories";
  }
}
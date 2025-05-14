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

  // Trang quản lý danh mục
  @GetMapping({"/categories", "/categories.html"})
  public String categoryPage(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) {
      return "redirect:/";
    }

    populateCommonModel(model, user);
    model.addAttribute("categories", categoryService.findAll());

    return "categories/all_categories";
  }

  // Thêm danh mục mới
  @PostMapping("/categories/new")
  @ResponseBody
  public ResponseEntity<String> newCategory(@RequestParam("name") String name) {
    if (categoryService.existsByName(name)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Danh mục đã tồn tại.");
    }

    Category category = new Category();
    category.setName(name.trim());
    categoryService.saveCategory(category);

    return ResponseEntity.ok("Thêm danh mục thành công.");
  }

  // Cập nhật danh mục
  @PostMapping("/categories/update")
  public String editCategory(@RequestParam("id") Long id,
                             @RequestParam("name") String name) {
    if (categoryService.existsByName(name)) {
      return "redirect:/categories?error=Danh mục đã tồn tại.";
    }

    categoryService.updateCategory(id, name);
    return "redirect:/categories";
  }

  // Xóa danh mục
  @PostMapping("/categories/delete/{id}")
  public String deleteCategory(@PathVariable("id") Long id,
                               RedirectAttributes redirectAttributes) {
    if (categoryService.hasPosts(id)) {
      redirectAttributes.addFlashAttribute("error", "Không thể xóa danh mục vì đang thuộc bài viết.");
      return "redirect:/categories";
    }

    categoryService.deleteCategoryById(id);
    redirectAttributes.addFlashAttribute("success", "Xóa danh mục thành công.");
    return "redirect:/categories";
  }

  // Xem bài viết theo danh mục
  @GetMapping("/category/{id}")
  public String viewPostsByCategory(@PathVariable("id") Long id,
                                    Model model,
                                    HttpSession session) {
    // Lấy user từ session (nếu có)
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    // Lấy tất cả danh sách danh mục
    model.addAttribute("categories", categoryService.getTopCategoriesNoLimit());

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));

    // Lấy số lượng bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    Category category = categoryService.findById(id);
    if (category == null) {
      return "redirect:/posts";
    }

    model.addAttribute("category", category);
    model.addAttribute("posts", category.getPosts());

    return "posts/posts_by_category";
  }

  // Hàm tiện ích dùng chung để gán user, categories, postsByMonth
  private void populateCommonModel(Model model, User user) {
    model.addAttribute("user", user);
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));
  }
}
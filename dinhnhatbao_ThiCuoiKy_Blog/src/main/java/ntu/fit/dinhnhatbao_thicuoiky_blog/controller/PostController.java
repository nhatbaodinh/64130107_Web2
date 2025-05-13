package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Category;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Post;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Role;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.User;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.CategoryService;
import ntu.fit.dinhnhatbao_thicuoiky_blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final CategoryService categoryService;

  @GetMapping({"/admin", "/admin.html"})
  public String adminPage(HttpSession session, Model model) {
    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
      model.addAttribute("user", currentUser);
    }

    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) {
      return "redirect:/";
    }
    return "posts/admin";
  }

  @GetMapping({"/posts", "/posts.html"})
  public String travelPage(HttpSession session, Model model) {
    // Lấy user từ session (nếu có)
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);

    //Lấy danh sách tat cả các bài viết
    model.addAttribute("posts", postService.getAllPosts());

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    return "posts";
  }

  @GetMapping("posts/new")
  public String newPostForm(Model model) {
    model.addAttribute("post", new Post());
    List<Category> categories = categoryService.findAll();
    model.addAttribute("categories", categories);

    return "posts/new_post";
  }

  @PostMapping("posts/save")
  public String createPost(@ModelAttribute Post post,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           HttpServletRequest request) throws IOException {
    // Tạo tên file duy nhất
    String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

    // Đường dẫn vật lý thật
    String uploadDir = System.getProperty("user.dir") + "/uploads/images/post/";
    File dir = new File(uploadDir);
    if (!dir.exists()) dir.mkdirs();

    // Lưu file
    File file = new File(dir, filename);
    imageFile.transferTo(file);

    // Lưu đường dẫn (đường dẫn URL để frontend truy cập)
    post.setImageUrl("/uploads/images/post/" + filename);
    post.setCreatedAt(LocalDateTime.now());

    // Gán tác giả
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user != null) {
      post.setAuthor(user);
    }

    postService.savePost(post);
    return "redirect:/posts";
  }
}
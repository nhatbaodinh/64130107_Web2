package ntu.fit.dinhnhatbao_thicuoiky_blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final CategoryService categoryService;

  // Hiển thị danh sách bài viết
  @GetMapping({"/posts", "/posts.html"})
  public String travelPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);
    model.addAttribute("posts", postService.getAllPosts());
    model.addAttribute("categories", categoryService.getTopCategoriesNoLimit());
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));
    return "posts";
  }

  // Tạo bài viết mới
  @GetMapping("posts/new")
  public String newPostForm(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) return "redirect:/";
    model.addAttribute("user", user);
    model.addAttribute("post", new Post());
    model.addAttribute("categories", categoryService.getTopCategoriesNoLimit());
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));
    return "posts/new_post";
  }

  // Lưu bài viết
  @PostMapping("posts/save")
  public String createPost(@ModelAttribute Post post,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           HttpServletRequest request, HttpSession session) throws IOException {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) return "redirect:/";

    // Tạo tên file duy nhất
    String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

    // Tạo thư mục nếu chưa có
    String uploadDir = System.getProperty("user.dir") + "/uploads/images/post/";
    File dir = new File(uploadDir);
    if (!dir.exists()) dir.mkdirs();

    // Lưu file vật lý
    File file = new File(dir, filename);
    imageFile.transferTo(file);

    // Cập nhật bài viết
    post.setImageUrl("/uploads/images/post/" + filename);
    post.setCreatedAt(LocalDateTime.now());

    if (user != null) post.setAuthor(user);

    postService.savePost(post);

    return "redirect:/posts";
  }

  // Xóa bài viết
  @PostMapping("posts/delete/{id}")
  public String deletePost(@PathVariable Long id) {
    Post post = postService.findById(id);

    if (post != null && post.getImageUrl() != null) {
      String uploadDir = System.getProperty("user.dir") + "/uploads/images/post/";
      File file = new File(uploadDir + new File(post.getImageUrl()).getName());
      if (file.exists()) file.delete();
    }

    postService.deletePostById(id);
    return "redirect:/posts";
  }

  // Chỉnh sửa bài viết
  @GetMapping("posts/edit/{id}")
  public String editPostForm(@PathVariable Long id, Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) return "redirect:/";

    model.addAttribute("user", user);
    model.addAttribute("post", postService.findById(id));
    model.addAttribute("categories", categoryService.getTopCategoriesNoLimit());
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));
    return "posts/edit_post";
  }

  // Cập nhật bài viết
  @PostMapping("posts/update/{id}")
  public String updatePost(@PathVariable Long id,
                           @ModelAttribute Post updatedPost,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           HttpSession session) throws IOException {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) return "redirect:/";

    Post post = postService.findById(id);
    if (post == null) return "redirect:/posts";

    // Nếu có upload ảnh mới
    if (imageFile != null && !imageFile.isEmpty()) {
      if (post.getImageUrl() != null) {
        String uploadDir = System.getProperty("user.dir") + "/uploads/images/post/";
        File oldFile = new File(uploadDir + new File(post.getImageUrl()).getName());
        if (oldFile.exists()) oldFile.delete();
      }

      String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
      String uploadDir = System.getProperty("user.dir") + "/uploads/images/post/";
      File dir = new File(uploadDir);
      if (!dir.exists()) dir.mkdirs();

      File file = new File(dir, filename);
      imageFile.transferTo(file);
      post.setImageUrl("/uploads/images/post/" + filename);
    }

    post.setTitle(updatedPost.getTitle());
    post.setContent(updatedPost.getContent());
    post.setCategory(updatedPost.getCategory());
    post.setCreatedAt(LocalDateTime.now());

    postService.savePost(post);
    return "redirect:/posts";
  }

  // Xem chi tiết bài viết
  @GetMapping("post/{id}")
  public String getPostById(@PathVariable("id") Long id, Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user != null) model.addAttribute("user", user);

    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    Post post = postService.findById(id);
    model.addAttribute("post", post);
    return "posts/post_detail";
  }

  // Xem bài viết theo danh mục
  @GetMapping("/posts/{month}")
  public String getPostsByMonth(@PathVariable String month, Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("user", user);
    model.addAttribute("posts", postService.getPostsByMonth(month));
    model.addAttribute("categories", categoryService.getTopCategoriesNoLimit());
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));
    model.addAttribute("month", month);
    return "posts/posts_by_month";
  }

  // Tìm kiếm bài viết theo từ khóa
  @GetMapping("/posts/search")
  public String searchPosts(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user != null) model.addAttribute("user", user);

    model.addAttribute("posts", postService.searchPosts(keyword));
    model.addAttribute("keyword", keyword);
    model.addAttribute("categories", categoryService.getTopCategoriesNoLimit());
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    return "posts/search_results";
  }
}
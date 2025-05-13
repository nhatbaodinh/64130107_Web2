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
  public String newPostForm(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) {
      return "redirect:/";
    }

    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
      model.addAttribute("user", currentUser);
    }

    model.addAttribute("post", new Post());
    List<Category> categories = categoryService.findAll();
    model.addAttribute("categories", categories);

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

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

  @PostMapping("posts/delete/{id}")
  public String deletePost(@PathVariable Long id) {
      Post post = postService.findById(id);
      if (post != null && post.getImageUrl() != null) {
          // Convert the image URL to the physical file path
          String uploadDir = System.getProperty("user.dir") + "/uploads/images/post/";
          File file = new File(uploadDir + new File(post.getImageUrl()).getName());

          // Delete the file if it exists
          if (file.exists()) {
              file.delete();
          }
      }
      // Delete the post from the database
      postService.deletePostById(id);
      return "redirect:/posts";
  }

  @GetMapping("posts/edit/{id}")
  public String editPostForm(@PathVariable Long id, Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) {
      return "redirect:/";
    }

    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
      model.addAttribute("user", currentUser);
    }

    Post post = postService.findById(id);
    model.addAttribute("post", post);

    List<Category> categories = categoryService.findAll();
    model.addAttribute("categories", categories);

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories_footer", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    return "posts/edit_post";
  }

  @PostMapping("posts/update/{id}")
  public String updatePost(@PathVariable Long id,
                           @ModelAttribute Post updatedPost,
                           @RequestParam("imageFile") MultipartFile imageFile, HttpSession session) throws IOException {
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.ADMIN) {
      return "redirect:/";
    }
    Post post = postService.findById(id);
    if (post == null) return "redirect:/posts";

    // Nếu người dùng có upload ảnh mới thì xử lý ảnh và xóa ảnh cũ
    if (imageFile != null && !imageFile.isEmpty()) {
      // Xóa ảnh cũ
      if (post.getImageUrl() != null) {
        String uploadDir = System.getProperty("user.dir") + "/uploads/images/post/";
        File file = new File(uploadDir + new File(post.getImageUrl()).getName());
        if (file.exists()) {
          file.delete();
        }
      }

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
    }

    // Cập nhật các thông tin khác
    post.setTitle(updatedPost.getTitle());
    post.setContent(updatedPost.getContent());
    post.setCategory(updatedPost.getCategory());
    post.setCreatedAt(LocalDateTime.now());

    postService.savePost(post);
    return "redirect:/posts";
  }

  @GetMapping("post/{id}")
  public String getPostById(@PathVariable("id") Long id, Model model, HttpSession session) {
    // Lấy user từ session (nếu có)
    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
      model.addAttribute("user", currentUser);
    }

    // Lấy danh sách danh mục theo số lượng bài viết
    model.addAttribute("categories", categoryService.getTopCategories(4));

    // Lấy so luong bài viết theo tháng
    model.addAttribute("postsByMonth", postService.getPostsCountByMonthLimit(4));

    Post post = postService.findById(id);
    model.addAttribute("post", post);
    return "posts/post_detail";
  }
}
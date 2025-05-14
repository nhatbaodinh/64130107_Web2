package ntu.fit.dinhnhatbao_thicuoiky_blog.service;

import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Post;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  // CRUD
  public void savePost(Post post) {
    postRepository.save(post);
  }

  public void deletePostById(Long id) {
    postRepository.deleteById(id);
  }

  public Post findById(Long id) {
    return postRepository.findById(id).orElse(null);
  }

  // Lấy danh sách bài viết
  public List<Post> getAllPosts() {
    return postRepository.findAllPosts();
  }

  // Lấy danh sách bài viết theo danh mục
  public List<Post> getLatestPosts(int limit) {
    return postRepository.findLatestPosts(PageRequest.of(0, limit));
  }

  public List<Post> getPostsByMonth(String month) {
    return postRepository.findPostsByMonth(month);
  }

  // Lấy danh sách bài viết theo tiêu đề
  public List<Post> searchPosts(String keyword) {
    return postRepository.searchPosts(keyword);
  }

  // Lấy danh sách bài viết theo tiêu đề và danh mục
  public List<Object[]> getPostsCountByMonthLimit(int limit) {
    List<Object[]> all = postRepository.findPostsCountByMonth();
    return all.subList(0, Math.min(limit, all.size())); // an toàn
  }
}
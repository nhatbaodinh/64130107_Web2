package ntu.fit.dinhnhatbao_thicuoiky_blog.repository;

import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("SELECT FUNCTION('DATE_FORMAT', p.createdAt, '%Y-%m') AS month, COUNT(p) AS postCount " +
      "FROM Post p " +
      "GROUP BY FUNCTION('DATE_FORMAT', p.createdAt, '%Y-%m') " +
      "ORDER BY month DESC LIMIT 4")
  List<Object[]> findPostsCountByMonth();
}

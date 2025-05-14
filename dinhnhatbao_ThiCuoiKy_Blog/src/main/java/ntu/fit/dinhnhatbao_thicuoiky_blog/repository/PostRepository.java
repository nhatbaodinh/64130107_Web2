package ntu.fit.dinhnhatbao_thicuoiky_blog.repository;

import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
  List<Post> findAllPosts();

  @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
  List<Post> findLatestPosts(Pageable pageable);

  @Query("""
      SELECT p FROM Post p
      WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
      """)
  List<Post> searchPosts(@Param("keyword") String keyword);

  @Query("""
      SELECT FUNCTION('DATE_FORMAT', p.createdAt, '%Y-%m') AS month, COUNT(p) AS postCount
      FROM Post p
      GROUP BY FUNCTION('DATE_FORMAT', p.createdAt, '%Y-%m')
      ORDER BY month DESC
      """)
  List<Object[]> findPostsCountByMonth();

  @Query("""
      SELECT p FROM Post p
      WHERE FUNCTION('DATE_FORMAT', p.createdAt, '%Y-%m') = :month
      ORDER BY p.createdAt DESC
      """)
  List<Post> findPostsByMonth(@Param("month") String month);
}
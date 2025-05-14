package ntu.fit.dinhnhatbao_thicuoiky_blog.repository;

import ntu.fit.dinhnhatbao_thicuoiky_blog.dto.CategoryPostCount;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query("SELECT c FROM Category c")
  List<Category> findAll();

  @Query("SELECT c FROM Category c WHERE c.name = ?1")
  Category findByName(String name);

  boolean existsByNameIgnoreCase(String name);

  @Query("""
      SELECT c.id AS id, c.name AS name, COUNT(p.id) AS postCount 
      FROM Category c LEFT JOIN c.posts p 
      GROUP BY c.id, c.name 
      ORDER BY postCount DESC
      """)
  List<CategoryPostCount> findCategoryPostCounts();
}
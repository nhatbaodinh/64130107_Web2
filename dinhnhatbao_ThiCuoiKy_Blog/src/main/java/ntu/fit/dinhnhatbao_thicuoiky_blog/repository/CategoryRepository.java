package ntu.fit.dinhnhatbao_thicuoiky_blog.repository;

import ntu.fit.dinhnhatbao_thicuoiky_blog.dto.CategoryPostCount;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Category;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

  @Query("SELECT c.name AS name, COUNT(p.id) AS postCount " +
      "FROM Category c LEFT JOIN c.posts p " +
      "GROUP BY c.id " +
      "ORDER BY postCount DESC")
  List<CategoryPostCount> findCategoryPostCounts();

  @Query("SELECT c FROM Category c")
  List<Category> findAll();
}
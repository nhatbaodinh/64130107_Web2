package ntu.fit.dinhnhatbao_thicuoiky_blog.service;

import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.dto.CategoryPostCount;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Category;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Post;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryPostCount> getTopCategories(int limit) {
    return categoryRepository.findCategoryPostCounts()
        .stream()
        .limit(limit)
        .toList();
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public void saveCategory(Category category) {
    categoryRepository.save(category);
  }

  public void deleteCategoryById(Long id) {
    categoryRepository.deleteById(id);
  }

  public void findByName(String name) {
    categoryRepository.findByName(name);
  }

  public boolean existsByName(String name) {
    return categoryRepository.existsByNameIgnoreCase(name.trim());
  }

  // Cập nhật danh mục
  public void updateCategory(Long id, String name) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Category not found"));

    category.setName(name);
    categoryRepository.save(category);
  }

  public boolean hasPosts(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElse(null);
    return category != null && category.getPosts() != null && !category.getPosts().isEmpty();
  }
}
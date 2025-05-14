package ntu.fit.dinhnhatbao_thicuoiky_blog.service;

import lombok.RequiredArgsConstructor;
import ntu.fit.dinhnhatbao_thicuoiky_blog.dto.CategoryPostCount;
import ntu.fit.dinhnhatbao_thicuoiky_blog.model.Category;
import ntu.fit.dinhnhatbao_thicuoiky_blog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  // Lấy danh sách các danh mục với số lượng bài viết top
  public List<CategoryPostCount> getTopCategories(int limit) {
    return categoryRepository.findCategoryPostCounts()
        .stream()
        .limit(limit)
        .toList();
  }

  public List<CategoryPostCount> getTopCategoriesNoLimit() {
    return categoryRepository.findCategoryPostCounts()
        .stream()
        .toList();
  }

  // Lấy tất cả danh mục
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  // Tìm danh mục theo ID
  public Category findById(Long id) {
    return categoryRepository.findById(id).orElse(null);
  }

  // Tìm danh mục theo tên
  public void findByName(String name) {
    categoryRepository.findByName(name);
  }

  // Kiểm tra sự tồn tại của danh mục theo tên
  public boolean existsByName(String name) {
    return categoryRepository.existsByNameIgnoreCase(name.trim());
  }

  // Lưu danh mục mới
  public void saveCategory(Category category) {
    categoryRepository.save(category);
  }

  // Cập nhật danh mục
  public void updateCategory(Long id, String name) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục với ID: " + id));

    category.setName(name);
    categoryRepository.save(category);
  }

  // Xóa danh mục theo ID
  public void deleteCategoryById(Long id) {
    categoryRepository.deleteById(id);
  }

  // Kiểm tra xem danh mục có bài viết hay không
  public boolean hasPosts(Long categoryId) {
    Category category = categoryRepository.findById(categoryId).orElse(null);
    return category != null && category.getPosts() != null && !category.getPosts().isEmpty();
  }
}
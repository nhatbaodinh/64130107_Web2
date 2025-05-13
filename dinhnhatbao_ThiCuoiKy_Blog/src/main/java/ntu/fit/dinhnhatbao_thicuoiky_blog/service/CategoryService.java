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

  public List<CategoryPostCount> getTopCategories(int limit) {
    return categoryRepository.findCategoryPostCounts()
        .stream()
        .limit(limit)
        .toList();
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }
}
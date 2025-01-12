package br.com.qima.assessment.bruno.domain.service;

import br.com.qima.assessment.bruno.domain.entity.CategoryEntity;
import br.com.qima.assessment.bruno.domain.repository.CategoryRepository;
import br.com.qima.assessment.bruno.presentation.dto.CategoryCreateDto;
import br.com.qima.assessment.bruno.presentation.dto.CategoryDto;
import br.com.qima.assessment.bruno.presentation.mapper.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;


  public CategoryEntity getCategoryById(Long id) {
    return getCategoryFromRepository(id).orElseThrow(
        () -> new EntityNotFoundException("Category not found"));
  }


  public CategoryDto createCategory(CategoryCreateDto categoryDto) {
    validateCategoryCreating(categoryDto);
    CategoryEntity category = CategoryEntity.builder()
        .name(categoryDto.getName())
        .parent(
            categoryDto.getParentId() != null ? getCategoryById(categoryDto.getParentId()) : null)
        .build();
    categoryRepository.save(category);
    return CategoryMapper.toDto(category);
  }

  private void validateCategoryCreating(CategoryCreateDto categoryDto) {
    if (Objects.nonNull(categoryDto.getParentId())) {
      getCategoryFromRepository(categoryDto.getParentId())
          .orElseThrow(() -> new EntityNotFoundException("Parent category not found"));
    }
    categoryRepository.findById(categoryDto.getId())
        .ifPresent(c -> {
          throw new IllegalArgumentException("Category already exists");
        });
  }

  private Optional<CategoryEntity> getCategoryFromRepository(Long id) {
    return categoryRepository.findById(id);
  }

  public CategoryDto updateCategory(Long id, CategoryCreateDto categoryDto) {
    CategoryEntity category = getCategoryById(id);
    category.setName(categoryDto.getName());
    category.setParent(
        categoryDto.getParentId() != null ? getCategoryById(categoryDto.getParentId()) : null);
    categoryRepository.save(category);
    return CategoryMapper.toDto(category);
  }
}

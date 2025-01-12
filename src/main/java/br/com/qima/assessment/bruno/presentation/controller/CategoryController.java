package br.com.qima.assessment.bruno.presentation.controller;

import br.com.qima.assessment.bruno.domain.service.CategoryService;
import br.com.qima.assessment.bruno.domain.service.ProductService;
import br.com.qima.assessment.bruno.presentation.dto.CategoryCreateDto;
import br.com.qima.assessment.bruno.presentation.dto.CategoryDto;
import br.com.qima.assessment.bruno.presentation.dto.ProductDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreateDto categoryDto) {
    CategoryDto category = categoryService.createCategory(categoryDto);
    return ResponseEntity.status(201).body(category);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDto> updateCategory(
      @PathVariable Long id,
      @RequestBody CategoryCreateDto categoryDto) {
    CategoryDto category = categoryService.updateCategory(id, categoryDto);
    return ResponseEntity.ok(category);
  }
}

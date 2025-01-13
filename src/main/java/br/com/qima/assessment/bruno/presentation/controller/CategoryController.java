package br.com.qima.assessment.bruno.presentation.controller;

import br.com.qima.assessment.bruno.domain.service.CategoryService;
import br.com.qima.assessment.bruno.presentation.dto.CategoryCreateDto;
import br.com.qima.assessment.bruno.presentation.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Endpoints de categorias")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  @Operation(summary = "Obter todas as categorias")
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @PostMapping
  @Operation(summary = "Criar uma nova categoria")
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreateDto categoryDto) {
    CategoryDto category = categoryService.createCategory(categoryDto);
    return ResponseEntity.status(201).body(category);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Atualizar uma categoria existente")
  public ResponseEntity<CategoryDto> updateCategory(
      @PathVariable Long id,
      @RequestBody CategoryCreateDto categoryDto) {
    CategoryDto category = categoryService.updateCategory(id, categoryDto);
    return ResponseEntity.ok(category);
  }
}

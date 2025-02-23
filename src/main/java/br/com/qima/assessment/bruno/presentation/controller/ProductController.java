package br.com.qima.assessment.bruno.presentation.controller;

import br.com.qima.assessment.bruno.domain.service.ProductService;
import br.com.qima.assessment.bruno.presentation.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "Product endpoints")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  @Operation(summary = "Get all products")
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    List<ProductDto> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a product by ID")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
    ProductDto product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

  @PostMapping
  @Operation(summary = "Create a new product")
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productCreateDTO) {
    ProductDto product = productService.createProduct(productCreateDTO);
    return ResponseEntity.status(201).body(product);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an existing product")
  public ResponseEntity<ProductDto> updateProduct(
      @PathVariable Long id,
      @RequestBody ProductDto productCreateDTO) {
    ProductDto product = productService.updateProduct(id, productCreateDTO);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a product")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}

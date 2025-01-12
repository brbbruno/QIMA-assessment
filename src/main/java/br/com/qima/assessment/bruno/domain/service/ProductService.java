package br.com.qima.assessment.bruno.domain.service;

import br.com.qima.assessment.bruno.domain.entity.CategoryEntity;
import br.com.qima.assessment.bruno.domain.entity.ProductEntity;
import br.com.qima.assessment.bruno.domain.repository.ProductRepository;
import br.com.qima.assessment.bruno.presentation.dto.ProductDto;
import br.com.qima.assessment.bruno.presentation.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  private final CategoryService categoryService;

  public ProductDto createProduct(ProductDto productDto) {
    CategoryEntity category = categoryService.getCategoryById(productDto.getCategoryId());
    validateIfProductExists(productDto);
    productRepository.save(ProductMapper.toEntity(productDto, category));
    return productDto;

  }

  public List<ProductDto> getAllProducts() {
    return productRepository.findAll()
        .stream()
        .map(productEntity ->
            ProductMapper.toDto(productEntity, buildCategoryPath(productEntity.getCategory())))
        .collect(Collectors.toList());
  }

  public ProductDto updateProduct(Long id, ProductDto productCreateDTO) {
    ProductEntity productEntity = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    CategoryEntity category = categoryService.getCategoryById(productCreateDTO.getCategoryId());

    productEntity.setName(productCreateDTO.getName());
    productEntity.setDescription(productCreateDTO.getDescription());
    productEntity.setPrice(productCreateDTO.getPrice());
    productEntity.setAvailable(productCreateDTO.isAvailable());
    productEntity.setCategory(category);
    productRepository.save(productEntity);

    return ProductMapper.toDto(productEntity, buildCategoryPath(category));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  public ProductDto getProductById(Long id) {
    Optional<ProductEntity> productEntity = productRepository.findById(id);
    return productEntity.map(
            entity -> ProductMapper.toDto(entity,
                buildCategoryPath(entity.getCategory()))
        )
        .orElse(null);
  }

  private String buildCategoryPath(CategoryEntity category) {
    StringBuilder categoryPath = new StringBuilder();
    while (category.getParent() != null) {
      categoryPath.insert(0, category.getName() + "/");
      category = category.getParent();
    }
    return categoryPath.toString();
  }

  private void validateIfProductExists(ProductDto productCreateDTO) {
    if (productRepository.findByIdOrName(productCreateDTO.getId(), productCreateDTO.getName())
        .isEmpty()) {
      throw new EntityNotFoundException("Product not found");
    }
  }
}

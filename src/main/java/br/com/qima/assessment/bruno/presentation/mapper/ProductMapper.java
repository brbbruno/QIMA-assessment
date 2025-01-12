package br.com.qima.assessment.bruno.presentation.mapper;

import br.com.qima.assessment.bruno.domain.entity.CategoryEntity;
import br.com.qima.assessment.bruno.domain.entity.ProductEntity;
import br.com.qima.assessment.bruno.presentation.dto.ProductDto;

public class ProductMapper {

  public static ProductEntity toEntity(ProductDto productDto, CategoryEntity category) {
    return ProductEntity.builder()
        .name(productDto.getName())
        .description(productDto.getDescription())
        .price(productDto.getPrice())
        .category(category)
        .build();
  }

  public static ProductDto toDto(ProductEntity productEntity, String categoryPath) {
    return ProductDto.builder()
        .name(productEntity.getName())
        .description(productEntity.getDescription())
        .price(productEntity.getPrice())
        .categoryPath(categoryPath)
        .build();
  }

}

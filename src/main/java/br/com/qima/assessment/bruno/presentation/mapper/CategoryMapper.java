package br.com.qima.assessment.bruno.presentation.mapper;

import br.com.qima.assessment.bruno.domain.entity.CategoryEntity;
import br.com.qima.assessment.bruno.presentation.dto.CategoryDto;

public class CategoryMapper {

  public static CategoryEntity toEntity(CategoryDto categoryDto) {
    return CategoryEntity.builder()
        .name(categoryDto.getName())
        .parent(toEntity(categoryDto.getParent()))
        .build();
  }

  public static CategoryDto toDto(CategoryEntity categoryEntity) {
    return CategoryDto.builder()
        .name(categoryEntity.getName())
        .parent(toDto(categoryEntity.getParent()))
        .build();
  }

}

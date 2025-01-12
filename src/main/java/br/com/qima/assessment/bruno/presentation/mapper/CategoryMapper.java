package br.com.qima.assessment.bruno.presentation.mapper;

import br.com.qima.assessment.bruno.domain.entity.CategoryEntity;
import br.com.qima.assessment.bruno.presentation.dto.CategoryDto;
import java.util.stream.Collectors;

public class CategoryMapper {

  public static CategoryEntity toEntity(CategoryDto categoryDto) {
    return CategoryEntity.builder()
        .name(categoryDto.getName())
        .parent(toEntity(categoryDto.getParent()))
        .subCategories(categoryDto.getSubCategories()
            .stream()
            .map(CategoryMapper::toEntity)
            .collect(Collectors.toList()))
        .build();
  }

  public static CategoryDto toDto(CategoryEntity categoryEntity) {
    return CategoryDto.builder()
        .name(categoryEntity.getName())
        .parent(toDto(categoryEntity.getParent()))
        .subCategories(categoryEntity.getSubCategories()
            .stream()
            .map(CategoryMapper::toDto)
            .collect(Collectors.toList()))
        .build();
  }

}

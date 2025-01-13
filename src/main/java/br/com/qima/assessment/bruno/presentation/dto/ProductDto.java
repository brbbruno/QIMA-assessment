package br.com.qima.assessment.bruno.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private Long categoryId;
  private String categoryName;
  private String categoryPath;
  private boolean available;

}

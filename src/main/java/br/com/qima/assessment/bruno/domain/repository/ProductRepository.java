package br.com.qima.assessment.bruno.domain.repository;

import br.com.qima.assessment.bruno.domain.entity.CategoryEntity;
import br.com.qima.assessment.bruno.domain.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  List<ProductEntity> findByCategory(CategoryEntity category);

  List<ProductEntity> findByIdOrName(Long id, String name);

}

package br.com.qima.assessment.bruno.domain.repository;

import br.com.qima.assessment.bruno.domain.entity.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  @Query("SELECT c FROM CategoryEntity c LEFT JOIN FETCH c.parent WHERE c.id = :id")
  Optional<CategoryEntity> findCategoryWithParent(@Param("id") Long id);

}

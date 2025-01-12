package br.com.qima.assessment.bruno.domain.repository;

import br.com.qima.assessment.bruno.domain.entity.AutenticationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AutenticationEntity, Long> {

  Optional<AutenticationEntity> findByUsername(String username);
}

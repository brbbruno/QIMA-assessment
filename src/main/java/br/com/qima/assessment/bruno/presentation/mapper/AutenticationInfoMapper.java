package br.com.qima.assessment.bruno.presentation.mapper;

import br.com.qima.assessment.bruno.domain.entity.AutenticationEntity;
import br.com.qima.assessment.bruno.presentation.dto.AutenticationInfoDto;

public class AutenticationInfoMapper {

  public static AutenticationEntity toEntity(AutenticationInfoDto autenticationInfoDto) {
    return AutenticationEntity.builder()
        .username(autenticationInfoDto.getUsername())
        .password(autenticationInfoDto.getPassword())
        .build();
  }
}

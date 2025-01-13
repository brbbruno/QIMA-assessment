package br.com.qima.assessment.bruno.presentation.dto;

import lombok.Builder;

@Builder
public record ErrorInfoDto(String message, Integer status) {

}

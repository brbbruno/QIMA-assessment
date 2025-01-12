package br.com.qima.assessment.bruno.presentation.controller;

import br.com.qima.assessment.bruno.domain.service.UserValidationService;
import br.com.qima.assessment.bruno.infra.configuration.jwt.JwtTokenProvider;
import br.com.qima.assessment.bruno.presentation.dto.AutenticationInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserValidationService userValidationService;

  @PostMapping("/auth")
  public String authenticate(@RequestBody AutenticationInfoDto request) {
    userValidationService.validate(request);
    return jwtTokenProvider.createToken(request.getUsername());
  }

  @PostMapping("/create")
  public void create(@RequestBody AutenticationInfoDto request) {
    userValidationService.createUser(request);
  }
}

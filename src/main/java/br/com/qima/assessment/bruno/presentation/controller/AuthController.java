package br.com.qima.assessment.bruno.presentation.controller;

import br.com.qima.assessment.bruno.domain.service.JwtTokenService;
import br.com.qima.assessment.bruno.domain.service.UserValidationService;
import br.com.qima.assessment.bruno.presentation.dto.AutenticationInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Endpoints de autenticação")
public class AuthController {

  private final JwtTokenService jwtTokenService;
  private final UserValidationService userValidationService;

  @PostMapping("/create")
  @Operation(summary = "Criar um novo usuário")
  public void create(@RequestBody AutenticationInfoDto request) {
    userValidationService.createUser(request);
  }

  @PostMapping("/validate")
  @Operation(summary = "Validar credenciais do usuário e retornar um token JWT")
  public String authenticate(@RequestBody AutenticationInfoDto request) {
    userValidationService.validate(request);
    return jwtTokenService.createToken(request.getUsername());
  }

  @PostMapping("c")
  @Operation(summary = "Validar se um token JWT é válido")
  public void validateToken(@RequestBody String token) {
    jwtTokenService.validateToken(token);
  }
}

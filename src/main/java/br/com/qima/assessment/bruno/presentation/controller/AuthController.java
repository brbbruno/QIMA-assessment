package br.com.qima.assessment.bruno.presentation.controller;

import br.com.qima.assessment.bruno.domain.service.JwtTokenService;
import br.com.qima.assessment.bruno.domain.service.UserValidationService;
import br.com.qima.assessment.bruno.presentation.dto.AutenticationInfoDto;
import br.com.qima.assessment.bruno.presentation.dto.TokenDto;
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
@Tag(name = "Auth Controller", description = "Authentication endpoints")
public class AuthController {

  private final JwtTokenService jwtTokenService;
  private final UserValidationService userValidationService;

  @PostMapping("/create")
  @Operation(summary = "Create a new user")
  public void create(@RequestBody AutenticationInfoDto request) {
    userValidationService.createUser(request);
  }

  @PostMapping("/validate")
  @Operation(summary = "Validate user credentials and return a JWT token")
  public String authenticate(@RequestBody AutenticationInfoDto request) {
    userValidationService.validate(request);
    return jwtTokenService.createToken(request.getUsername());
  }

  @PostMapping("/validate/session")
  @Operation(summary = "Validate if a JWT token is valid")
  public void validateToken(@RequestBody TokenDto request) {
    jwtTokenService.validateToken(request.getToken());
  }
}

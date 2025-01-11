package br.com.bruno.study.example.studiedemo.presentation.controller;

import br.com.bruno.study.example.studiedemo.domain.model.AutenticationInfo;
import br.com.bruno.study.example.studiedemo.infra.configuration.jwt.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/auth")
  public String authenticate(@RequestBody AutenticationInfo request) {
    return jwtTokenProvider.createToken(request.getUsername());
  }
}

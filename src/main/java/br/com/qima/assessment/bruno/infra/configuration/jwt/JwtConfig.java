package br.com.qima.assessment.bruno.infra.configuration.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class JwtConfig {

  private final String secretKey;

  public JwtConfig(@Value("${security.jwt.token.secret-key}") String secretKey) {
    this.secretKey = secretKey;
  }
}

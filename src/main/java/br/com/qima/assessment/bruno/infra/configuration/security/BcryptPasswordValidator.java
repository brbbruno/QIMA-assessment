package br.com.qima.assessment.bruno.infra.configuration.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BcryptPasswordValidator {

  @Bean
  @Qualifier("passwordValidator")
  public BCryptPasswordEncoder passwordVerifier() {
    return new BCryptPasswordEncoder();
  }
}

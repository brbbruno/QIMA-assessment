package br.com.qima.assessment.bruno.infra.configuration.security;

import br.com.qima.assessment.bruno.infra.configuration.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private static final String[] PUBLIC_URLS = {"/test/**",
      "/auth/**",
      "/auth"};
  public static final String PRIVATE_URLS = "/api/**";
  private final JwtAuthenticationFilter jwtTokenAuthenticationFilter;

  @Bean
  @Order(2)
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.sessionManagement(sessionManagement -> {
          sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })
        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry
                .requestMatchers(PUBLIC_URLS)
                .permitAll()
                .requestMatchers(PRIVATE_URLS)
                .authenticated())
        .csrf(AbstractHttpConfigurer::disable)
        .addFilterBefore(jwtTokenAuthenticationFilter, AnonymousAuthenticationFilter.class);

    return httpSecurity.build();
  }
}

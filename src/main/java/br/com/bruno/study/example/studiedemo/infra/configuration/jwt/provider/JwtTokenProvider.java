package br.com.bruno.study.example.studiedemo.infra.configuration.jwt.provider;

import br.com.bruno.study.example.studiedemo.infra.configuration.jwt.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private final JwtConfig jwtConfig;

  public JwtTokenProvider(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }


  public String createToken(String username) {
    Date now = new Date();
    long validityInMilliseconds = 3600000;
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
        .compact();
  }
}

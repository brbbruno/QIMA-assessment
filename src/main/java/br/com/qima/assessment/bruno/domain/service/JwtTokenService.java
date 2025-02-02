package br.com.qima.assessment.bruno.domain.service;

import br.com.qima.assessment.bruno.domain.exception.ExpiredJwtException;
import br.com.qima.assessment.bruno.infra.configuration.jwt.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

  private final JwtConfig jwtConfig;

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      throw new ExpiredJwtException("Token expired, please login again!");
    }
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

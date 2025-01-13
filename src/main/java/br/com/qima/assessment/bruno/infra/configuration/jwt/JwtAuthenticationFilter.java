package br.com.qima.assessment.bruno.infra.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtConfig jwtConfig;

  public JwtAuthenticationFilter(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    String jwt = getJwtFromRequest(request);

    if (jwt != null && validateToken(jwt)) {
      Claims claims = Jwts.parser()
          .setSigningKey(jwtConfig.getSecretKey())
          .parseClaimsJws(jwt)
          .getBody();
      String username = claims.getSubject();

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          username, null, null);
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  private boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}

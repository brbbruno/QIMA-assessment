package br.com.qima.assessment.bruno.infra.configuration.jwt;

import br.com.qima.assessment.bruno.domain.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String BEARER_PREFIX = "Bearer ";
  private static final String AUTHORIZATION_HEADER = "Authorization";

  private final JwtConfig jwtConfig;
  private final JwtTokenService jwtTokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      authenticateRequestIfValid(request);
    } finally {
      filterChain.doFilter(request, response);
    }
  }

  private void authenticateRequestIfValid(HttpServletRequest request) {
    getJwtFromRequest(request)
        .filter(jwtTokenService::validateToken)
        .ifPresent(jwt -> setAuthentication(jwt, request));
  }

  private void setAuthentication(String jwt, HttpServletRequest request) {
    Claims claims = extractClaims(jwt);
    UsernamePasswordAuthenticationToken authentication = createAuthentication(claims.getSubject(),
        request);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private Claims extractClaims(String jwt) {
    return Jwts.parser()
        .setSigningKey(jwtConfig.getSecretKey())
        .parseClaimsJws(jwt)
        .getBody();
  }

  private UsernamePasswordAuthenticationToken createAuthentication(String username,
      HttpServletRequest request) {
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(username, null, null);
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    return authentication;
  }

  private Optional<String> getJwtFromRequest(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER))
        .filter(token -> token.startsWith(BEARER_PREFIX))
        .map(token -> token.substring(BEARER_PREFIX.length()));
  }
}


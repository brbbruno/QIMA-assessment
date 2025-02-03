package br.com.qima.assessment.bruno.domain.exception;

public class ExpiredJwtException extends RuntimeException {

  public ExpiredJwtException(String message) {
    super(message);
  }
}

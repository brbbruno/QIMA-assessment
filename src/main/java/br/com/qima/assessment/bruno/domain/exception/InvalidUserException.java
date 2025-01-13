package br.com.qima.assessment.bruno.domain.exception;

public class InvalidUserException extends RuntimeException {

  public InvalidUserException(String message) {
    super(message);
  }
}

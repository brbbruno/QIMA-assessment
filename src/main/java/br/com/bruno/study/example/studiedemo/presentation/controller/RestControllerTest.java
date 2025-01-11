package br.com.bruno.study.example.studiedemo.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerTest {

  @GetMapping("/test")
  public String test() {
    return "Test";
  }

}

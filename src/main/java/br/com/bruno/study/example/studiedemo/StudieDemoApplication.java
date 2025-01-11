package br.com.bruno.study.example.studiedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.bruno.study.example.studiedemo")
public class StudieDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudieDemoApplication.class, args);
  }

}

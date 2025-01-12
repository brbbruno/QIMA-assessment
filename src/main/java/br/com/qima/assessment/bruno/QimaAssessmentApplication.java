package br.com.qima.assessment.bruno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.qima.assessment.bruno")
@EnableJpaRepositories(basePackages = "br.com.qima.assessment.bruno.domain.repository")
@EntityScan(basePackages = "br.com.qima.assessment.bruno.domain.entity")
public class QimaAssessmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(QimaAssessmentApplication.class, args);
  }

}

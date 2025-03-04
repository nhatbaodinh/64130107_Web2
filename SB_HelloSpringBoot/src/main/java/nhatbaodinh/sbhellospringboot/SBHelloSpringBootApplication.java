package nhatbaodinh.sbhellospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "controllers")
public class SBHelloSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SBHelloSpringBootApplication.class, args);
  }

}

package nhatbaodinh.chatspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "controllers")
public class ChatSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatSpringBootApplication.class, args);
  }

}

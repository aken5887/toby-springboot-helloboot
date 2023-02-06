package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import tobyspring.config.EnableMyAutoConfiguration;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
@EnableMyAutoConfiguration
public class HellobootApplication {
  public static void main(String[] args) {
    SpringApplication.run(HellobootApplication.class, args);
  }
}


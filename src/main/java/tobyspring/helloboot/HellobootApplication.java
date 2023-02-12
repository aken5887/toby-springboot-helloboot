package tobyspring.helloboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.EnableMyAutoConfiguration;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
@EnableMyAutoConfiguration
public class HellobootApplication {

  @Bean
  ApplicationRunner applicationRunner(Environment env){
    return new ApplicationRunner() {
      @Override
      public void run(ApplicationArguments args) throws Exception {
        System.out.println(env.getProperty("server.contextPath"));
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(HellobootApplication.class, args);
  }
}


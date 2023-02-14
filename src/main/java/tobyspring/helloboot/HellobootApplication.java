package tobyspring.helloboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.config.EnableMyAutoConfiguration;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
@EnableMyAutoConfiguration
public class HellobootApplication {

  private final JdbcTemplate jdbcTemplate;

  public HellobootApplication(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }

  @Bean
  ApplicationRunner applicationRunner(Environment env){
    return new ApplicationRunner() {
      @Override
      public void run(ApplicationArguments args) throws Exception {
        System.out.println(env.getProperty("server.contextPath"));
      }
    };
  }

  @PostConstruct
  void init(){
    jdbcTemplate.execute("create table if not exists greeting (name varchar(50) primary key, count int)");
  }


  public static void main(String[] args) {
    SpringApplication.run(HellobootApplication.class, args);
  }
}


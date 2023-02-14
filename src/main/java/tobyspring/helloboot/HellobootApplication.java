package tobyspring.helloboot;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HellobootApplication {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final JdbcTemplate jdbcTemplate;

  public HellobootApplication(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }

  @Bean
  ApplicationRunner applicationRunner(Environment env){
    return new ApplicationRunner() {
      @Override
      public void run(ApplicationArguments args) throws Exception {
        logger.info(env.getProperty("server.servlet.context-path"));
      }
    };
  }

  @PostConstruct
  void init(){
    logger.info("PostConstruct init");
    jdbcTemplate.execute("create table if not exists greeting (name varchar(50) primary key, count int)");
  }


  public static void main(String[] args) {
    SpringApplication.run(HellobootApplication.class, args);
  }
}


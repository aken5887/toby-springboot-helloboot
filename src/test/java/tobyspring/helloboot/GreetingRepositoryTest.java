package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
public class GreetingRepositoryTest {

  @Autowired GreetingRepository repository;
  @Autowired JdbcTemplate jdbcTemplate;

  @Test
  void findGreetingFail(){
    Assertions.assertThat(repository.findGreeting("toby")).isNull();
  }

  @Test
  void increaseCountTest(){
    repository.increaseCount("Toby");
    Assertions.assertThat(repository.countOf("Toby")).isEqualTo(1);
    repository.increaseCount("Toby");
    Assertions.assertThat(repository.countOf("Toby")).isEqualTo(2);
  }
}

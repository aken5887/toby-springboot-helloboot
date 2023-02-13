package tobyspring.helloboot;

import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@HellobootTest
public class HelloServiceCountTest {
  @Autowired HelloService helloService;
  @Autowired GreetingRepository greetingRepository;

  @Test
  void sayHelloIncreaseCount(){
    helloService.sayHello("Toby");
    Assertions.assertThat(greetingRepository.countOf("Toby")).isEqualTo(1);

    IntStream.rangeClosed(1, 10).forEach(count -> {
      helloService.sayHello("Toby");
      Assertions.assertThat(greetingRepository.countOf("Toby")).isEqualTo(count+1);
    });
  }
}

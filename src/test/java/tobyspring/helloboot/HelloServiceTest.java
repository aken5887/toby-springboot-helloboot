package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
  @Test
  void serviceTest(){
    HelloService helloService = new SimpleHelloService();
    String ret = helloService.sayHello("yong");
    Assertions.assertThat(ret).contains("yong");
  }

  @Test
  void decoratorTest(){
    HelloDecorator helloDecorator = new HelloDecorator(name -> name);
    String ret = helloDecorator.sayHello("Spring");
    Assertions.assertThat(ret).isEqualTo("*Spring*");
  }
}

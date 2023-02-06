package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

  @Test
  void helloControllerTest(){
    HelloController controller = new HelloController(name -> name);
    String ret = controller.hello("yong");
    Assertions.assertThat(ret).isEqualTo("yong");
  }

  @Test
  void helloControllerFailTest(){
    HelloController controller = new HelloController(name->name);
    Assertions.assertThatThrownBy(()->{
      String ret = controller.hello(null);
    }).isInstanceOf(IllegalArgumentException.class);

    Assertions.assertThatThrownBy(()->{
      String ret = controller.hello("");
    }).isInstanceOf(IllegalArgumentException.class);
  }
}

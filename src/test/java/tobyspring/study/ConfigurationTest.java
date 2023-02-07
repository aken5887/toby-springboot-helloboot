package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

  @Test
  void pojo() {
    MyConfig myConfig = new MyConfig();
    Assertions.assertThat(myConfig.bean1().common).isNotSameAs(myConfig.bean2().common);
  }

  @Test
  void configuration(){
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(MyConfig.class);
    context.refresh();

    Bean1 bean1 = context.getBean(Bean1.class);
    Bean2 bean2 = context.getBean(Bean2.class);

    System.out.println(bean1.common);
    Assertions.assertThat(bean1.common).isSameAs(bean2.common);
  }

  @Test
  void proxyCommonMethod(){
    MyConfigProxy proxy = new MyConfigProxy();
    Bean1 bean1 = proxy.bean1();
    Bean2 bean2 = proxy.bean2();

    System.out.println(bean1.common);
    Assertions.assertThat(bean1.common).isSameAs(bean2.common);
  }

  @Test
  void noProxyBeanMethod(){
    AnnotationConfigApplicationContext context
        = new AnnotationConfigApplicationContext();
    context.register(MyConfigNoProxy.class);
    context.refresh();

    Common common1 = context.getBean(Bean1.class).common;
    Common common2 = context.getBean(Bean2.class).common;

    Assertions.assertThat(common1).isNotSameAs(common2);
  }

  @Configuration(proxyBeanMethods = false)
  static class MyConfigNoProxy extends MyConfig{
  }

  // 스프링 컨테이너 내부에서 일어나는 일을 비슷하게 구현 (싱글톤)
  static class MyConfigProxy extends MyConfig {
    private Common common;

    @Override
    Common common() {
      if(this.common == null){
        this.common = super.common();
      }
      return this.common;
    }
  }

  @Configuration
  static class MyConfig {
    @Bean
    Common common(){
      return new Common();
    }

    @Bean
    Bean1 bean1(){
      return new Bean1(common());
    }

    @Bean
    Bean2 bean2(){
      return new Bean2(common());
    }
  }

  static class Common {
    public Common(){
      System.out.println("created" + this);
    }
  }

  static class Bean1 {
    private final Common common;
    public Bean1(Common common){
      this.common = common;
    }
  }

  static class Bean2 {
    private final Common common;
    public Bean2(Common common){
      this.common = common;
    }
  }
}

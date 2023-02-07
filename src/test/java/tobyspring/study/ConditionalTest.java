package tobyspring.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {

  @Test
  void conditionalTest() {
    ApplicationContextRunner contextRunner = new ApplicationContextRunner();
    contextRunner.withUserConfiguration(MyTrueConfig.class)
        .run(context -> {
          Assertions.assertThat(context).hasSingleBean(MyTrueConfig.class);
          Assertions.assertThat(context).hasSingleBean(MyBean.class);
        });

    ApplicationContextRunner contextRunner2 = new ApplicationContextRunner();
    contextRunner2.withUserConfiguration(MyFalseConfig.class)
        .run(context -> {
          Assertions.assertThat(context).doesNotHaveBean(MyFalseConfig.class);
          Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
        });
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(BooleanCondition.class)
  @interface BooleanConditional {
    boolean value();
  }

  @Configuration
  @BooleanConditional(true)
  static class MyTrueConfig{
    @Bean
    public MyBean myBean(){
      return new MyBean();
    }
  }

  @Configuration
  @BooleanConditional(false)
  static class MyFalseConfig{
    @Bean
    public MyBean myBean(){
      return new MyBean();
    }
  }

  static class BooleanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
          BooleanConditional.class.getName());
      Boolean value = (Boolean) annotationAttributes.get("value");
      return value;
    }
  }

  static class MyBean {
    public MyBean(){
      System.out.println("myBean created"+this);
    }
  }
}

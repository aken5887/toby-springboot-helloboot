package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass(value="org.apache.catalina.startup.Tomcat", use =false)
public class TomcatServletWebServerFactoryConfig {
  @Bean("tomcatWebServerFactory")
  public ServletWebServerFactory tomcatServletWebServerFactory(){
    return new TomcatServletWebServerFactory();
  }
}

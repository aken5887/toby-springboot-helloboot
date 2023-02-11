package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass(value="org.eclipse.jetty.server.Server", use =true)
public class JettyServletWebServerFactoryConfig {
  @Bean("jettyWebServerFactory")
  @ConditionalOnMissingBean
  public ServletWebServerFactory jettyServletWebServerFactory(){
    return new JettyServletWebServerFactory();
  }
}

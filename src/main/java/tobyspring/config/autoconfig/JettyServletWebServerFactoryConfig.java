package tobyspring.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass(value="org.eclipse.jetty.server.Server", use =true)
public class JettyServletWebServerFactoryConfig {

  @Value("${contextPath}")
  String contextPath;

  @Bean("jettyWebServerFactory")
  @ConditionalOnMissingBean
  public ServletWebServerFactory jettyServletWebServerFactory(Environment env){
    JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
    System.out.println(this.contextPath);
    factory.setContextPath(this.contextPath);
    return factory;
  }
}

package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServletWebServerFactoryConfig {
  @Bean
  public ServletWebServerFactory servletWebServerFactory(){
    return new TomcatServletWebServerFactory();
  }
}

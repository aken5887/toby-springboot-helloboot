package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration(proxyBeanMethods = false)
public class CustomWebServerFactoryConfig {
  @Bean
  public ServletWebServerFactory customWebServerFactory(){
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
    factory.setPort(9090);
    return factory;
  }
}

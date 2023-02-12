package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.ServerProperties;

@MyAutoConfiguration
@ConditionalMyOnClass(value="org.eclipse.jetty.server.Server", use =true)
@EnableMyConfigurationProperties(ServerProperties.class)
public class JettyServletWebServerFactoryConfig {
  @Bean("jettyWebServerFactory")
  @ConditionalOnMissingBean
  public ServletWebServerFactory jettyServletWebServerFactory(ServerProperties properties){
    JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
    factory.setContextPath(properties.getContextPath());
    factory.setPort(properties.getServerPort());
    return factory;
  }
}

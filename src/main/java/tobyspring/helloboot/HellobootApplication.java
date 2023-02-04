package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {
  public static void main(String[] args) {
    /** Spring Container **/
    GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
    applicationContext.registerBean(HelloController.class);
    applicationContext.registerBean(SimpleHelloService.class);
    applicationContext.refresh(); // 애플리케이션 컨텍스트 초기화

    ServletWebServerFactory factory = new TomcatServletWebServerFactory();
    WebServer webServer = factory.getWebServer(servletContext -> {
      servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
          .addMapping("/*");
    });
    webServer.start();
  }
}


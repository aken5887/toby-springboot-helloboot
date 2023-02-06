package tobyspring.config;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
  public static void run(Class<?> applicationClass, String... args) {
    /** Spring Container **/
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
      @Override
      protected void onRefresh() {
        super.onRefresh();
        /** Servlet Container */
        ServletWebServerFactory factory = this.getBean(ServletWebServerFactory.class);
        WebServer webServer = factory.getWebServer(servletContext -> {
          DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
          //         dispatcherServlet.setApplicationContext(this); 생략가능
          servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*");
        });
        webServer.start();
      }
    };
    applicationContext.register(applicationClass);
    applicationContext.refresh(); // 애플리케이션 컨텍스트 초기화
  }
}
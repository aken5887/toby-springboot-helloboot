package tobyspring.helloboot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HellobootApplication {
  public static void main(String[] args) {
    ServletWebServerFactory factory = new TomcatServletWebServerFactory();
    WebServer webServer = factory.getWebServer(servletContext -> {
      servletContext.addServlet("frontcontroller", new HttpServlet() {
        HelloController helloController = new HelloController(); // 인스턴스를 한번 만들어서 재사용
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          // 인증, 보안, 다국어, 공통 기능
          String requestURL = req.getRequestURI();
          String httpMethod = req.getMethod();
          String url = httpMethod+"|"+requestURL;
          switch(url){
            case "GET|/hello":
              String name = req.getParameter("name");

              String ret = helloController.hello(name); // 작업을 위임

              resp.setStatus(HttpStatus.OK.value());
              resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
              resp.getWriter().println(ret);
              break;
            default:
              resp.setStatus(HttpStatus.BAD_REQUEST.value());
          }
        }
      }).addMapping("/*");
    });
    webServer.start();
  }
}


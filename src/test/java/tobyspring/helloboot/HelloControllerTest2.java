package tobyspring.helloboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HelloControllerTest2 {

  @Autowired
  TestRestTemplate testRestTemplate;

  @Test
  void HelloServlet_테스트(){
    String url = "http://localhost:8080/hello";
    ResponseEntity<?> responseEntity = this.testRestTemplate.exchange(url, HttpMethod.GET, null, Object.class);
    Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
  }
}

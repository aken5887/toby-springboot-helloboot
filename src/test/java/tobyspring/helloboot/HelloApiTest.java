package tobyspring.helloboot;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {
  @Test
  void helloApi(){
    // http localhost:8080/hello?name=Spring
    // HTTPie
    TestRestTemplate restTemplate = new TestRestTemplate();
    ResponseEntity<String> response
        = restTemplate.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");
    // status code 200
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    // header(content-type) text/plain
    Assertions.assertTrue(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.TEXT_PLAIN_VALUE));
    // body Hello Spring
    Assertions.assertEquals("*Hello, Spring*", response.getBody());
  }

  // 실패 테스트
  @Test
  void HelloApiFail(){
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ResponseEntity<String> res
        = testRestTemplate.getForEntity("http://localhost:8080/hello?name=", String.class);
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
  }
}

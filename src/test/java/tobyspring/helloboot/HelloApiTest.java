package tobyspring.helloboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class HelloApiTest {

  @Value("${server.port}")
  private int port;

  @Test
  void helloApi(){
    String HELLO_API_URL = "http://localhost:"+port+"/app/hello";
    // http localhost:8080/hello?name=Spring
    // HTTPie
    TestRestTemplate restTemplate = new TestRestTemplate();
    ResponseEntity<String> response
        = restTemplate.getForEntity(HELLO_API_URL +"?name={name}", String.class, "Spring");
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
    String HELLO_API_URL = "http://localhost:"+port+"/app/hello";

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ResponseEntity<String> res
        = testRestTemplate.getForEntity(HELLO_API_URL +"?name=", String.class);
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
  }

  @Test
  void increaseCountTest(){
    String url = "http://localhost:"+port+"/app";

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ResponseEntity<String> res;
    int count = 0;

    res = testRestTemplate.getForEntity(url+"/count?name={name}", String.class, "Yong");
    count = Integer.parseInt(res.getBody()) + 1;

    res = testRestTemplate.getForEntity(url+"/hello?name={name}",String.class, "Yong");
    org.assertj.core.api.Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

    res = testRestTemplate.getForEntity(url+"/count?name={name}", String.class, "Yong");
    org.assertj.core.api.Assertions.assertThat(res.getBody()).isEqualTo(String.valueOf(count));
  }
}

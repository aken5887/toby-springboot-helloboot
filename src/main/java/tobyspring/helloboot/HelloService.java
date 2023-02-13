package tobyspring.helloboot;

public interface HelloService {
  String sayHello(String name);

  default String countOf(String name){
    return "0";
  }
}

package tobyspring.helloboot;

import org.springframework.stereotype.Service;

@Service
public class SimpleHelloService implements HelloService{

  private final GreetingRepository greetingRepository;

  public SimpleHelloService(GreetingRepository repository){
    this.greetingRepository = repository;
  }

  @Override
  public String sayHello(String name) {
    this.greetingRepository.increaseCount(name);
    return "Hello, "+name;
  }

  @Override
  public String countOf(String name) {
    int count = greetingRepository.countOf(name);
    return String.valueOf(count);
  }
}

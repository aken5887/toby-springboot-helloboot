package tobyspring.helloboot;

public interface GreetingRepository {

  Greeting findGreeting(String name);

  void increaseCount(String name);

  default int countOf(String name){
    Greeting greeting = findGreeting(name);
    if(greeting == null) return 0;
    return greeting.getCount();
  }
}

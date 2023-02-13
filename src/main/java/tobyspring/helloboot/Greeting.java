package tobyspring.helloboot;

public class Greeting {
  private String name;
  private int count;

  public Greeting(String name, int count) {
    this.name = name;
    this.count = count;
  }

  public String getName() {
    return name;
  }

  public int getCount() {
    return count;
  }
}

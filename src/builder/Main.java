package builder;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    Person person1 = new Person.PersonBuilder(101).name("John").age(20).gender('M').build();
    System.out.println(person1.toString());
  }
}
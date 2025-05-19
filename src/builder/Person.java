package builder;

/**
 * Implementing a builder design pattern. The user needs to create a person object, with the
 * flexibility to add any number of attributes.
 */
public class Person {
  private final String name;
  private final int age;
  private final char gender;
  private final int id;
  /* This is an antipattern, user needs to remember the order of attributes and needs to pass all
  the attributes while creating the object.
  public Person(String name, int age, char gender, int id) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.id = id;
  }
   */
  // Using builder pattern to create an object. This constructor is hidden from the user.
  private Person(PersonBuilder builder) {
    this.name = builder.name;
    this.age = builder.age;
    this.gender = builder.gender;
    this.id = builder.id;
  }

  public static class PersonBuilder {
    private String name;
    private int age;
    private char gender;
    private int id;

    public PersonBuilder(int id) {
      this.id = id;
    }
    public PersonBuilder name(String name) {
      this.name = name;
      return this;
    }
    public PersonBuilder age(int age) {
      this.age = age;
      return this;
    }
    public PersonBuilder gender(char gender) {
      this.gender = gender;
      return this;
    }
    public Person build() {
      return new Person(this);
    }
  }
  public String toString() {
    return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", id=" + id + "]";
  }
}

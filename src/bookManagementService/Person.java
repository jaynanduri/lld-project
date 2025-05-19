package bookManagementService;

abstract public class Person {
  protected String personId;
  public Person(String id) {
    personId = id;
  }
  public String getId() {
    return personId;
  }
}

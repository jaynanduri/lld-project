package strategy;

public class UpperCaseConvertor implements Convertor{
  @Override
  public String convert(String input) {
    return input.toUpperCase();
  }
}

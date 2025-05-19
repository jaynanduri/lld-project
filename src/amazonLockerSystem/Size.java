package amazonLockerSystem;

public enum Size {
  SMALL(0),
  MEDIUM(1),
  LARGE(2);
  private int numVal;
  Size(int numVal) {
    this.numVal = numVal;
  }
  public int getNumVal() {
    return numVal;
  }
}

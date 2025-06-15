public class arc {
  private int x;
  private int y;
  private int val;

  public arc(int d, int a, int v) {
    x = d;
    y = a;
    val = v;
  }

  public arc(arc a) {
    x = a.x;
    y = a.y;
    val = a.val;
  }

  public int getval() {
    return val;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}

package boardGame;

public class Position {

  private int colunm;
  private int row;

  public int getColunm() {
    return colunm;
  }

  public void setColunm(int colunm) {
    this.colunm = colunm;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public Position(int colunm, int row) {
    this.colunm = colunm;
    this.row = row;
  }

  public Position() {
  }

  @Override
  public String toString() {
    return "Position [colunm=" + colunm + ", row=" + row + "]";
  }

}

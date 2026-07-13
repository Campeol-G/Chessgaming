package boardGame;

public class Position {

  private int column;
  private int row;

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public Position(int column, int row) {
    this.column = column;
    this.row = row;
  }

  public Position() {
  }

  @Override
  public String toString() {
    return "Position [column=" + column + ", row=" + row + "]";
  }

}

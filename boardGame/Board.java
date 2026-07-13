package boardGame;

import boardGame.Piece;
import boardGame.Position;

public class Board {
  private int rows;
  private int colunm;
  private Piece[][] pieces;

  public Board(int rows, int colunm) {
    this.rows = rows;
    this.colunm = colunm;
    pieces = new Piece[rows][colunm];
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public int getColunm() {
    return colunm;
  }

  public void setColunm(int colunm) {
    this.colunm = colunm;
  }

  public Piece piece(int row, int colunm) {
    return pieces[row][colunm];
  }

  public Piece piece(Position position) {
    return pieces[position.getRow()][position.getColunm()];
  }

  public void placePiece(Piece piece, Position position) {
    pieces[position.getRow()][position.getColunm()] = piece;
    piece.position = position;
  }
}

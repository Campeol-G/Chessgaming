package boardGame;

import boardGame.BoardException;
import boardGame.Piece;
import boardGame.Position;

public class Board {
  private int rows;
  private int colunms;
  private Piece[][] pieces;

  public Board(int rows, int colunm) {
    if (rows < 1 || colunm < 1) {
      throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
    }
    this.rows = rows;
    colunms = colunm;
    pieces = new Piece[rows][colunm];
  }

  public int getRows() {
    return rows;
  }

  public int getColunm() {
    return colunms;
  }

  public Piece piece(int row, int colunm) {
    if (!positionExists(row, colunm)) {
      throw new BoardException("Position not on the board");
    }
    return pieces[row][colunm];
  }

  public Piece piece(Position position) {
    if (!positionExists(position)) {
      throw new BoardException("Position not on the board");
    }

    return pieces[position.getRow()][position.getColunm()];
  }

  public void placePiece(Piece piece, Position position) {
    if (thereIsAPiece(position)) {
      throw new BoardException("There is already a piece on position" + position);
    }
    pieces[position.getRow()][position.getColunm()] = piece;
    piece.position = position;
  }

  private boolean positionExists(int row, int colunm) {
    return row >= 0 && row < rows && colunm >= 0 && colunm < colunms;
  }

  public boolean positionExists(Position position) {
    return positionExists(position.getRow(), position.getColunm());
  }

  public boolean thereIsAPiece(Position position) {
    if (!positionExists(position)) {
      throw new BoardException("Position not on the board");
    }
    return piece(position) != null;
  }
}

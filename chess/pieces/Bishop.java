package chess.pieces;

import chess.chessPiece;
import boardGame.Board;
import chess.Color;
import boardGame.Position;

public class Bishop extends chessPiece {

  public Bishop(Board board, Color color) {
    super(board, color);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];
    Position aux = new Position(0, 0);

    // upper left
    aux.setValues(position.getRow() - 1, position.getColumn() - 1);
    while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
      aux.setValues(aux.getRow() - 1, aux.getColumn() - 1);
    }
    if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // upper right
    aux.setValues(position.getRow() - 1, position.getColumn() + 1);
    while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
      aux.setValues(aux.getRow() - 1, aux.getColumn() + 1);
    }
    if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // down left
    aux.setValues(position.getRow() + 1, position.getColumn() - 1);
    while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
      aux.setValues(aux.getRow() + 1, aux.getColumn() - 1);
    }
    if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // down right
    aux.setValues(position.getRow() + 1, position.getColumn() + 1);
    while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
      aux.setValues(aux.getRow() + 1, aux.getColumn() + 1);
    }
    if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    return mat;
  }

  @Override
  public String toString() {
    return "B";
  }

}

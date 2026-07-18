package chess.pieces;

import boardGame.Position;
import chess.chessPiece;
import boardGame.Board;
import chess.Color;

public class Knight extends chessPiece {

  public Knight(Board board, Color color) {
    super(board, color);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];
    Position aux = new Position(0, 0);
    // upper right
    aux.setValues(position.getRow() - 2, position.getColumn() + 1);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // upper left
    aux.setValues(position.getRow() - 2, position.getColumn() - 1);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // right upper
    aux.setValues(position.getRow() - 1, position.getColumn() + 2);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // right down
    aux.setValues(position.getRow() + 1, position.getColumn() + 2);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // left upper
    aux.setValues(position.getRow() - 1, position.getColumn() - 2);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // left down
    aux.setValues(position.getRow() + 1, position.getColumn() - 2);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // down right
    aux.setValues(position.getRow() + 2, position.getColumn() + 1);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    // down left
    aux.setValues(position.getRow() + 2, position.getColumn() - 1);
    if (getBoard().positionExists(aux) && (!getBoard().thereIsAPiece(aux) || isThereOpponentPiece(aux))) {
      mat[aux.getRow()][aux.getColumn()] = true;
    }

    return mat;
  }

  @Override
  public String toString() {
    return "N";
  }

}

package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.Color;
import chess.chessMatch;
import chess.chessPiece;
import chess.pieces.Rook;

public class King extends chessPiece {

  private chessMatch cm;

  public King(Board board, Color color, chessMatch cm) {
    super(board, color);
    this.cm = cm;
  }

  @Override
  public String toString() {
    return "K";
  }

  private boolean canMove(Position position) {
    chessPiece p = (chessPiece) getBoard().piece(position);
    return p == null || p.getColor() != getColor();
  }

  private boolean testRookCastling(Position position) {
    chessPiece aux = (chessPiece) getBoard().piece(position);
    return aux != null && aux instanceof Rook && aux.getColor() == getColor() && aux.getMoveCount() == 0;
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];
    Position p = new Position(0, 0);

    // upper left
    p.setValues(position.getRow() - 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // upper right
    p.setValues(position.getRow() - 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // upper
    p.setValues(position.getRow() - 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // left
    p.setValues(position.getRow(), position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // right
    p.setValues(position.getRow(), position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // down
    p.setValues(position.getRow() + 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // down left
    p.setValues(position.getRow() + 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // down right
    p.setValues(position.getRow() + 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // special move castling
    if (getMoveCount() == 0 && !cm.getCheck()) {
      // castling king side
      Position aux = new Position(position.getRow(), position.getColumn() + 3);
      if (testRookCastling(aux)) {
        Position p1 = new Position(position.getRow(), position.getColumn() + 1);
        Position p2 = new Position(position.getRow(), position.getColumn() + 2);
        if (!getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2)) {
          mat[position.getRow()][position.getColumn() + 2] = true;
        }
      }
      // castling queen side
      aux = new Position(position.getRow(), position.getColumn() - 4);
      if (testRookCastling(aux)) {
        Position p1 = new Position(position.getRow(), position.getColumn() - 1);
        Position p2 = new Position(position.getRow(), position.getColumn() - 2);
        Position p3 = new Position(position.getRow(), position.getColumn() - 3);
        if (!getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2) && !getBoard().thereIsAPiece(p3)) {
          mat[position.getRow()][position.getColumn() - 2] = true;
        }
      }

    }

    return mat;
  }

}

package chess.pieces;

import boardGame.Position;
import boardGame.Board;
import chess.Color;
import chess.chessMatch;
import chess.chessPiece;

public class Pawn extends chessPiece {

  private chessMatch cm;

  public Pawn(Board board, Color color, chessMatch cm) {
    super(board, color);
    this.cm = cm;
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];
    Position p = new Position(0, 0);

    if (getColor() == Color.WHITE) {

      p.setValues(position.getRow() - 1, position.getColumn());
      if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValues(position.getRow() - 2, position.getColumn());
      Position p2 = new Position(position.getRow() - 1, position.getColumn());
      if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)
          && !getBoard().thereIsAPiece(p2) && getBoard().positionExists(p2) && getMoveCount() == 0) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValues(position.getRow() - 1, position.getColumn() - 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValues(position.getRow() - 1, position.getColumn() + 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      // enpassant
      if (position.getRow() == 3) {
        Position left = new Position(position.getRow(), position.getColumn() - 1);
        Position right = new Position(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(left) && isThereOpponentPiece(left)
            && getBoard().piece(left) == cm.getEnPassantVulnerable()) {
          mat[left.getRow() - 1][left.getColumn()] = true;
        }
        if (getBoard().positionExists(right) && isThereOpponentPiece(right)
            && getBoard().piece(right) == cm.getEnPassantVulnerable()) {
          mat[right.getRow() - 1][right.getColumn()] = true;
        }

      }
    } else {
      p.setValues(position.getRow() + 1, position.getColumn());
      if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValues(position.getRow() + 2, position.getColumn());
      Position p2 = new Position(position.getRow() + 1, position.getColumn());
      if (getMoveCount() == 0 && getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)
          && !getBoard().thereIsAPiece(p2) && getBoard().positionExists(p2)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValues(position.getRow() + 1, position.getColumn() - 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValues(position.getRow() + 1, position.getColumn() + 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      // enpassant
      if (position.getRow() == 4) {
        Position left = new Position(position.getRow(), position.getColumn() - 1);
        Position right = new Position(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(left) && isThereOpponentPiece(left)
            && getBoard().piece(left) == cm.getEnPassantVulnerable()) {
          mat[left.getRow() + 1][left.getColumn()] = true;
        }
        if (getBoard().positionExists(right) && isThereOpponentPiece(right)
            && getBoard().piece(right) == cm.getEnPassantVulnerable()) {
          mat[right.getRow() + 1][right.getColumn()] = true;
        }

      }

    }
    return mat;
  }

  @Override
  public String toString() {
    return "P";
  }

}

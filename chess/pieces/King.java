package chess.pieces;

import boardGame.Board;
import chess.Color;
import chess.chessPiece;

public class King extends chessPiece {

  public King(Board board, Color color) {
    super(board, color);
  }

  @Override
  public String toString() {
    return "K";
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumn()];
    return mat;
  }

}

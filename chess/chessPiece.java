package chess;

import boardGame.Position;
import chess.ChessPosition;
import chess.Color;
import boardGame.Piece;
import boardGame.Board;

public abstract class chessPiece extends Piece {
  private Color color;
  private int moveCount;

  public ChessPosition getChessPosition() {
    return ChessPosition.fromPosition(position);
  }

  public chessPiece(Board board, Color color) {
    super(board);
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  protected boolean isThereOpponentPiece(Position position) {
    chessPiece p = (chessPiece) getBoard().piece(position);
    return p != null && p.getColor() != color;
  }

  protected void increaseMoveCount() {
    moveCount++;
  }

  protected void decreaseMoveCount() {
    moveCount--;
  }

  public int getMoveCount() {
    return moveCount;
  }
}

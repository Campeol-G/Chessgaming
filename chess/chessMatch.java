package chess;

import chess.chessPiece;
import chess.pieces.Rook;
import chess.Color;
import chess.pieces.King;

import boardGame.Board;
import boardGame.Position;

public class chessMatch {
  private Board board;

  public chessMatch() {
    board = new Board(8, 8);
    initialSetup();
  }

  public chessPiece[][] getPieces() {
    chessPiece[][] matriz = new chessPiece[board.getRows()][board.getColunm()];
    for (int i = 0; i < board.getRows(); i++) {
      for (int j = 0; j < board.getColunm(); j++) {
        matriz[i][j] = (chessPiece) board.piece(i, j);
      }
    }
    return matriz;
  }

  private void initialSetup() {
    board.placePiece(new Rook(board, Color.BLACK), new Position(1, 2));
    board.placePiece(new King(board, Color.WHITE), new Position(4, 0));
    board.placePiece(new King(board, Color.BLACK), new Position(4, 7));
  }

}

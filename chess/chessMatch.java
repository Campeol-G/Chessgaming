package chess;

import chess.pieces.Rook;
import chess.pieces.King;

import boardGame.Board;

public class chessMatch {
  private Board board;

  public chessMatch() {
    board = new Board(8, 8);
    initialSetup();
  }

  public chessPiece[][] getPieces() {
    chessPiece[][] matriz = new chessPiece[board.getRows()][board.getColumn()];
    for (int i = 0; i < board.getRows(); i++) {
      for (int j = 0; j < board.getColumn(); j++) {
        matriz[i][j] = (chessPiece) board.piece(i, j);
      }
    }
    return matriz;
  }

  private void initialSetup() {
    placeNewPiece('c', 1, new Rook(board, Color.WHITE));
    placeNewPiece('c', 2, new Rook(board, Color.WHITE));
    placeNewPiece('d', 2, new Rook(board, Color.WHITE));
    placeNewPiece('e', 2, new Rook(board, Color.WHITE));
    placeNewPiece('e', 1, new Rook(board, Color.WHITE));
    placeNewPiece('d', 1, new King(board, Color.WHITE));

    placeNewPiece('c', 7, new Rook(board, Color.BLACK));
    placeNewPiece('c', 8, new Rook(board, Color.BLACK));
    placeNewPiece('d', 7, new Rook(board, Color.BLACK));
    placeNewPiece('e', 7, new Rook(board, Color.BLACK));
    placeNewPiece('e', 8, new Rook(board, Color.BLACK));
    placeNewPiece('d', 8, new King(board, Color.BLACK));
  }

  private void placeNewPiece(char column, int row, chessPiece piece) {
    board.placePiece(piece, new ChessPosition(column, row).toPosition());
  }
}

package chess;

import chess.pieces.Rook;
import chess.ChessPosition;
import chess.chessPiece;
import chess.pieces.King;
import boardGame.Piece;
import boardGame.Board;
import boardGame.Position;

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

  public chessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();
    validateSourcePosition(source);
    validateTargetPosition(source, target);
    Piece capturedPiece = makeMove(source, target);
    return (chessPiece) capturedPiece;
  }

  public boolean[][] possibleMoves(ChessPosition sourcePosition) {
    Position p = sourcePosition.toPosition();
    validateSourcePosition(p);
    return board.piece(p).possibleMoves();
  }

  private void validateTargetPosition(Position source1, Position target1) {
    if (!board.piece(source1).possibleMove(target1)) {
      throw new ChessException("Invalid move! The chosen piece can't move to target position.");
    }
  }

  private void validateSourcePosition(Position position) {
    if (!board.thereIsAPiece(position)) {
      throw new ChessException("There is no piece on source position.");
    }
    if (!board.piece(position).isThereAnyPossibleMove()) {
      throw new ChessException("There is no possible moves for the chosen piece.");
    }
  }

  private Piece makeMove(Position source, Position target) {
    Piece p = board.removePiece(source);
    Piece capturedPiece = board.removePiece(target);
    board.placePiece(p, target);
    return capturedPiece;
  }

  private void placeNewPiece(char column, int row, chessPiece piece) {
    board.placePiece(piece, new ChessPosition(column, row).toPosition());
  }
}

package chess;

import chess.pieces.Rook;
import chess.ChessPosition;
import chess.chessPiece;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import boardGame.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardGame.Board;
import boardGame.Position;

public class chessMatch {
  private Board board;
  private Color currentPlayer;
  private int turn;
  private List<Piece> piecesOnTheBoard = new ArrayList<>();
  private List<Piece> capturedPieces = new ArrayList<>();
  private boolean check;
  private boolean checkMate;
  private chessPiece enPassantVunerable;
  private chessPiece promoted;

  public chessMatch() {
    board = new Board(8, 8);
    turn = 1;
    currentPlayer = Color.WHITE;
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

  public chessPiece getPromoted() {
    return promoted;
  }

  public chessPiece getEnPassantVunerable() {
    return enPassantVunerable;
  }

  public boolean getCheck() {
    return check;
  }

  public Color getCurrentPlayer() {
    return currentPlayer;
  }

  public int getTurn() {
    return turn;
  }

  public boolean getCheckMate() {
    return checkMate;
  }

  private void initialSetup() {
    placeNewPiece('e', 1, new King(board, Color.WHITE, this));
    placeNewPiece('a', 1, new Rook(board, Color.WHITE));
    placeNewPiece('h', 1, new Rook(board, Color.WHITE));
    placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('g', 1, new Knight(board, Color.WHITE));
    placeNewPiece('b', 1, new Knight(board, Color.WHITE));
    placeNewPiece('d', 1, new Queen(board, Color.WHITE));

    placeNewPiece('a', 8, new Rook(board, Color.BLACK));
    placeNewPiece('e', 8, new King(board, Color.BLACK, this));
    placeNewPiece('h', 8, new Rook(board, Color.BLACK));
    placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('g', 8, new Knight(board, Color.BLACK));
    placeNewPiece('b', 8, new Knight(board, Color.BLACK));
    placeNewPiece('d', 8, new Queen(board, Color.BLACK));

  }

  public chessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();
    validateSourcePosition(source);
    validateTargetPosition(source, target);
    Piece capturedPiece = makeMove(source, target);

    chessPiece movedPiece = (chessPiece) board.piece(target);

    // promotion
    promoted = null;
    if (movedPiece instanceof Pawn) {
      if (movedPiece.getColor() == Color.WHITE && target.getRow() == 0
          || movedPiece.getColor() == Color.BLACK && target.getRow() == 7) {
        promoted = (chessPiece) board.piece(target);
        promoted = replacePromotedPiece("Q");
      }
    }
    // especial move en en passant
    if (movedPiece instanceof Pawn && target.getRow() == source.getRow() - 2
        || target.getRow() == source.getRow() + 2) {
      enPassantVunerable = movedPiece;
    } else {
      enPassantVunerable = null;
    }

    if (testCheck(currentPlayer)) {
      undoMove(source, target, capturedPiece);
      throw new ChessException("You can't put yourself in check");
    }

    check = (testCheck(opponent(currentPlayer))) ? true : false;
    if (testCheckMate(opponent(currentPlayer))) {
      checkMate = true;
    } else {
      nextTurn();
    }
    return (chessPiece) capturedPiece;
  }

  public chessPiece replacePromotedPiece(String type) {
    if (promoted == null) {
      throw new IllegalStateException("There is no piece to be promoted");
    }
    if (!type.equals("B") && !type.equals("Q") && !type.equals("N") && type.equals("R")) {
      return promoted;
    }

    Position aux = promoted.getChessPosition().toPosition();

    Piece p = board.removePiece(aux);
    piecesOnTheBoard.remove(p);

    chessPiece newPiece = newPiece(type, promoted.getColor());
    board.placePiece(newPiece, aux);
    return newPiece;
  }

  private chessPiece newPiece(String type, Color color) {
    if (type.equals("B")) {
      return new Bishop(board, color);
    }
    if (type.equals("Q")) {
      return new Queen(board, color);
    }
    if (type.equals("N")) {
      return new Knight(board, color);
    }
    return new Rook(board, color);
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
    if (currentPlayer != ((chessPiece) board.piece(position)).getColor()) {
      throw new ChessException("The chosen piece is not yours");
    }
  }

  private void nextTurn() {
    turn++;
    currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  private Color opponent(Color color) {
    if (color == Color.WHITE) {
      return Color.BLACK;
    } else {
      return Color.WHITE;
    }
  }

  private chessPiece king(Color color) {
    List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((chessPiece) x).getColor() == color)
        .collect(Collectors.toList());
    for (Piece p : list) {
      if (p instanceof King) {
        return (chessPiece) p;
      }
    }
    throw new IllegalStateException("There is no " + color + " king on the board");
  }

  private boolean testCheck(Color color) {
    Position kingPosition = king(color).getChessPosition().toPosition();
    List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((chessPiece) x).getColor() == opponent(color))
        .collect(Collectors.toList());
    for (Piece p : opponentPieces) {
      boolean[][] mat = p.possibleMoves();
      if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
        return true;
      }
    }
    return false;
  }

  private boolean testCheckMate(Color color) {
    if (!testCheck(color)) {
      return false;
    }
    List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((chessPiece) x).getColor() == color)
        .collect(Collectors.toList());
    for (Piece p : list) {
      boolean[][] mat = p.possibleMoves();
      for (int i = 0; i < mat.length; i++) {
        for (int j = 0; j < mat.length; j++) {
          if (mat[i][j]) {
            Position source = ((chessPiece) p).getChessPosition().toPosition();
            Position target = new Position(i, j);
            Piece capturedPiece = makeMove(source, target);
            boolean testCheck = testCheck(color);
            undoMove(source, target, capturedPiece);
            if (!testCheck) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  private Piece makeMove(Position source, Position target) {
    chessPiece p = (chessPiece) board.removePiece(source);
    p.increaseMoveCount();
    Piece capturedPiece = board.removePiece(target);
    board.placePiece(p, target);
    if (capturedPiece != null) {
      piecesOnTheBoard.remove(capturedPiece);
      capturedPieces.add(capturedPiece);
    }

    // special move kingside Rook
    if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
      Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
      Position targetT = new Position(source.getRow(), source.getColumn() + 1);
      chessPiece rook = (chessPiece) board.removePiece(sourceT);
      board.placePiece(rook, targetT);
      rook.increaseMoveCount();
    }

    // special move queenside Rook
    if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
      Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
      Position targetT = new Position(source.getRow(), source.getColumn() - 1);
      chessPiece rook = (chessPiece) board.removePiece(sourceT);
      board.placePiece(rook, targetT);
      rook.increaseMoveCount();
    }

    // en passant
    if (p instanceof Pawn) {
      if (source.getColumn() != target.getColumn() && capturedPiece == null) {
        Position pawnPosition;
        if (p.getColor() == Color.WHITE) {
          pawnPosition = new Position(target.getRow() + 1, target.getColumn());
        } else {
          pawnPosition = new Position(target.getRow() - 1, target.getColumn());
        }
        capturedPiece = board.removePiece(pawnPosition);
        capturedPieces.add(capturedPiece);
        piecesOnTheBoard.remove(capturedPiece);
      }
    }

    return capturedPiece;
  }

  private void undoMove(Position source, Position target, Piece capturedPiece) {
    chessPiece p = (chessPiece) board.removePiece(target);
    p.decreaseMoveCount();
    board.placePiece(p, source);
    if (capturedPiece != null) {
      board.placePiece(capturedPiece, target);
      capturedPieces.remove(capturedPiece);
      piecesOnTheBoard.add(capturedPiece);
    }

    // special move kingside Rook
    if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
      Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
      Position targetT = new Position(source.getRow(), source.getColumn() + 1);
      chessPiece rook = (chessPiece) board.removePiece(targetT);
      board.placePiece(rook, sourceT);
      rook.decreaseMoveCount();
    }

    // special move queenside Rook
    if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
      Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
      Position targetT = new Position(source.getRow(), source.getColumn() - 1);
      chessPiece rook = (chessPiece) board.removePiece(targetT);
      board.placePiece(rook, sourceT);
      rook.decreaseMoveCount();
    }

    // en passant
    if (p instanceof Pawn) {
      if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVunerable) {
        chessPiece pawn = (chessPiece) board.removePiece(target);
        Position pawnPosition;
        if (p.getColor() == Color.WHITE) {
          pawnPosition = new Position(3, target.getColumn());
        } else {
          pawnPosition = new Position(4, target.getColumn());
        }
        board.placePiece(pawn, pawnPosition);
      }
    }

  }

  private void placeNewPiece(char column, int row, chessPiece piece) {
    board.placePiece(piece, new ChessPosition(column, row).toPosition());
    piecesOnTheBoard.add(piece);
  }
}

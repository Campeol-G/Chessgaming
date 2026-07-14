package chess;

import java.lang.RuntimeException;

import boardGame.BoardException;

public class ChessException extends BoardException {

  public ChessException(String msg) {
    super(msg);
  }
}

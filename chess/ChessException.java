package chess;

import java.lang.RuntimeException;

public class ChessException extends RuntimeException {

  public ChessException(String msg) {
    super(msg);
  }
}

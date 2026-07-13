package application;

import application.UI;
import boardGame.Board;
import boardGame.Position;
import chess.chessMatch;

public class Program {
  public static void main(String[] args) {
    chessMatch cm = new chessMatch();
    UI.printBoard(cm.getPieces());
  }
}

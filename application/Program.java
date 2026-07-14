package application;

import java.util.Scanner;

import application.UI;
import boardGame.Board;
import boardGame.Position;
import chess.ChessPosition;
import chess.chessMatch;
import chess.chessPiece;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    chessMatch cm = new chessMatch();
    while (true) {
      UI.printBoard(cm.getPieces());
      System.out.println();
      System.out.print("Source: ");
      ChessPosition source = UI.readChessPosition(sc);
      System.out.println();
      System.out.print("Target: ");
      ChessPosition target = UI.readChessPosition(sc);

      chessPiece capturedPiece = cm.performChessMove(source, target);
    }
  }
}

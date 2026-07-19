package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import application.UI;
import boardGame.Board;
import boardGame.Position;
import chess.ChessException;
import chess.ChessPosition;
import chess.chessMatch;
import chess.chessPiece;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    chessMatch cm = new chessMatch();
    List<chessPiece> capPieces = new ArrayList<>();

    while (!cm.getCheckMate()) {
      try {
        UI.clearScreen();
        UI.printMatch(cm, capPieces);
        System.out.println();
        System.out.print("Source: ");
        ChessPosition source = UI.readChessPosition(sc);
        System.out.println();
        UI.clearScreen();
        UI.printBoard(cm.getPieces(), cm.possibleMoves(source));
        System.out.print("Target: ");
        ChessPosition target = UI.readChessPosition(sc);

        chessPiece capturedPiece = cm.performChessMove(source, target);
        if (capturedPiece != null) {
          capPieces.add(capturedPiece);
        }

        if (cm.getPromoted() != null) {
          System.out.print("Enter piece for promotion (B/N/Q/R): ");
          String type = sc.nextLine().toUpperCase();
          while (!type.equals("B") && !type.equals("Q") && !type.equals("N") && !type.equals("R")) {
            System.out.println("Invalid value!");
            System.out.print("Enter piece for promotion (B/N/Q/R): ");
            type = sc.nextLine().toUpperCase();
          }
          cm.replacePromotedPiece(type);
        }

      } catch (ChessException e) {
        System.out.println(e.getMessage());
        sc.nextLine();
      } catch (InputMismatchException e) {
        System.out.println(e.getMessage());
        sc.nextLine();
      }
      UI.clearScreen();
      UI.printMatch(cm, capPieces);
    }
  }
}

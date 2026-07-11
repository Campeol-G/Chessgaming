package application;

import boardGame.Board;
import boardGame.Position;

public class Program {
  public static void main(String[] args) {
    Position p1 = new Position(1, 3);
    System.out.println(p1);
    Board board = new Board(8, 8);
  }
}

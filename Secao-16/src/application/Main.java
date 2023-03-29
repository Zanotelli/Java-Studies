package application;

import boardgame.Board;
import chess.ChessMatch;

public class Main {
    public static void main(String[] args) {

        ChessMatch match = new ChessMatch();

        UI.printBoard(match.getPieces());
    }
}
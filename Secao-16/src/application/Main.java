package application;

import boardgame.Board;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();

        while (true){
            UI.printBoard(match.getPieces());
            System.out.print("\nOrigem: ");
            ChessPosition origem = UI.readChessPosition(sc);

            System.out.print("Destino: ");
            ChessPosition destino = UI.readChessPosition(sc);

            ChessPiece pecaCapturada = match.performChessMove(origem,destino);
            System.out.println();
        }

    }
}
package application;

import boardgame.Board;
import boardgame.BoardException;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();

        int jogador = 0;

        while (true){
            try {
                UI.clearScreen();
                UI.printBoard(match.getPieces());

                System.out.println("\nJogada do jogador " + (jogador + 1));
                System.out.print("Origem: ");
                ChessPosition origem = UI.readChessPosition(sc);

                boolean[][] possibleMoves = match.possibleMoves(origem);
                UI.clearScreen();
                UI.printBoard(match.getPieces(), possibleMoves);

                System.out.print("Destino: ");
                ChessPosition destino = UI.readChessPosition(sc);

                ChessPiece pecaCapturada = match.performChessMove(origem,destino);

                jogador = (jogador + 1) % 2;
            } catch (BoardException e){
                System.out.println(e.toString());
                System.out.println("Pressione Enter para continuar...");
                sc.nextLine();
            }

        }

    }
}
package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.*;

public class UI {

    /* Estilizações */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    /**
     * Limpa a tela do jogador
     */
    public static void clearScreen(){
        System.out.flush();
    }


    /**
     * Lê uma entrada do usuário no formato 'a1' a 'h8' e a converte
     * para 'ChessPosition'
     * @param sc Um objeto 'Scanner', instanciado na Main, que lê as
     *           entradas d jogador
     * @return A posição passada pelo usuário como uma 'ChessPosition'
     */
    public static ChessPosition readChessPosition(Scanner sc){
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }catch (InputMismatchException e){
            throw new RuntimeException("Erro ao ler a posição. Valores válidos são de 'a1' até 'h8'");
        } catch (RuntimeException e) {
            throw new RuntimeException("Entrada incorreta ou imcompleta");
        }
    }

    /**
     * Imprime o tabuleiro da partida, assim como os dados dos jogadores
     *
     * @param match    Partida de xadrez
     * @param captured
     */
    public static void printMatch(ChessMatch match, LinkedList<ChessPiece> captured){
        printBoard(match.getPieces());
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("Turno " + match.getTurn() );
        System.out.println("Esperando jogador " + translateName(match.getCurrentPlayer()));
        System.out.println();
    }

    private static String translateName(Color color){
        if(color == Color.WHITE)
            return "Branco";
        return "Preto";
    }

    /**
     * Imprime o tabuleiro com as peças para a visualização
     * do usuáro
     * @param pieces Matriz com as peças no tabuleiro
     */
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    /**
     * Imprime o tabuleiro com as peças para a visualização
     * do usuáro, com os movimentos possíveis marcados
     * @param pieces Matriz com as peças no tabuleiro
     * @param possibleMoves Matriz de booleanos indicando as posições
     *                      para onde o usuário pode mover a peça
     *                      selecionada
     */
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    /**
     * Imprime um determinado espaço do tabuleiro
     * @param piece Peça a ser imprimida no tabuleiro
     * @param background Define o backgroud. Local vazio caso
     *                   falso, ou local de movimento possível
     *                   caso verdadeiro
     */
    private static void printPiece(ChessPiece piece, boolean background) {
        if(background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturedPieces(LinkedList<ChessPiece> captured){
        List<ChessPiece> white = captured.stream().filter(p -> p.getColor() == Color.WHITE).toList();
        List<ChessPiece> black = captured.stream().filter(p -> p.getColor() == Color.BLACK).toList();

        System.out.println();
        System.out.println("Peças capturadas");
        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }

}

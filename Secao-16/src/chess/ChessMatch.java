package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.LinkedList;
import java.util.List;

public class ChessMatch {
    private final Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;

    private LinkedList<Piece> piecesBoard = new LinkedList<>();
    private LinkedList<Piece> piecesCaptured = new LinkedList<>();

    public ChessMatch(){
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = false;
        initialSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i =0; i < board.getRows(); i++){
            for (int j =0; j < board.getColumns(); j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }


    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }


    /**
     *
     * @param soucePosition A posição de onde se deseja mover uma peça
     * @param targetPosition A posição final da peça que se encontrava
     *                       na posição de origem
     * @return A peça capturada, caso já houvesse uma peça na posição de
     *          destino, nulo caso o contrário
     */
    public ChessPiece performChessMove(ChessPosition soucePosition,
                                       ChessPosition targetPosition){
        Position source = soucePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);

        // Verifica se o jogador não se colocou em cheque
        if(testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("Você não pode se colocar em cheque!");
        }

        // Verifica se o jogador colocou o oponente em cheque
        check = testCheck(opponent(currentPlayer));

        nextTurn();
        return (ChessPiece) capturedPiece;
    }


    /**
     * Move uma peça de uma posição de origem à uma posição
     * de destino, campturando qualquer peça no destino
     * @param source Posição da peça de origem
     * @param target Posição de destino
     * @return A peça capturada, caso exista
     */
    private Piece makeMove(Position source, Position target){
        Piece movedPiece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);

        board.placePiece(movedPiece, target);

        if(capturedPiece != null){
            piecesBoard.remove(capturedPiece);
            piecesCaptured.add(capturedPiece);
        }

        return capturedPiece;
    }

    /**
     * Desfaz o movimento de um jogador, caso ele seja inválido
     * @param source Posição da peça de origem
     * @param target Posição de destino
     * @param captured Peça capturada no movimento
     */
    private void undoMove(Position source, Position target, Piece captured){
       Piece p = board.removePiece(target);
       board.placePiece(p, source);

       if(captured != null) {
           board.placePiece(captured, target);
           piecesCaptured.remove(captured);
           piecesBoard.add(captured);
       }
    }

    /**
     * Checa se existe uma peça na posição de origem
     * @param source Posição de origem
     * @throws ChessException Laça uma exceção caso não exista uma peça na
     * posição de origem
     */
    private void validateSourcePosition(Position source) {
        if(!board.thereIsAPiece(source))
            throw new ChessException("Não exite uma peça na posição de origem");
        if(currentPlayer != ((ChessPiece)board.piece(source)).getColor())
            throw new ChessException("A peça selecionada não pertence ao jogador");
        if(!board.piece(source).isThereAnyPossibleMove())
            throw new ChessException("A peça não tem movimentos possiveis");
    }


    private void validateTargetPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target))
            throw new ChessException("Movimento inválido para peça selecionada");
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesBoard.add(piece);
    }

    /**
     * Verifica a cor do jogador oponente
     * @param color Cor do jogador atual
     * @return Cor do oponente
     */
    private Color opponent(Color color){
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    /**
     * Encontra, dentre todas as peças no tabuleiro, o rei
     * de um determiado jogador
     * @param color Cor do jogado
     * @return O rei jogador
     */
    private ChessPiece king (Color color){
        List<Piece> pieces = piecesBoard.stream().filter(p -> ((ChessPiece)p).getColor() == color).toList();
        for(Piece p: pieces)
            if(p instanceof King)
                return (ChessPiece) p;
        throw new IllegalStateException("Não existe um rei " + translateName(color) + " no jogo!");
    }

    /**
     * Testa para todas as peças do oponente, se alguma coloca o rei do
     * jogador passado em cheque
     * @param color Cor do jogador atual
     * @return Verdadeiro, caso o jogador passado esteja em cheque, falso
     * caso o contrário
     */
    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesBoard.stream().filter(p -> ((ChessPiece)p).getColor() == opponent(color)).toList();

        for(Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()])
                return true;
        }
        return false;
    }

    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

    private static String translateName(Color color){
        if(color == Color.WHITE)
            return "Branco";
        return "Preto";
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isCheck() {
        return check;
    }
}

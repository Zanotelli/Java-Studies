package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch(){
        board = new Board(8,8);
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

        return (ChessPiece)     capturedPiece;
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
        return capturedPiece;
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
        if(!board.piece(source).isThereAnyPossibleMove())
            throw new ChessException("A peça não tem movimentos possiveis");
    }


    private void validateTargetPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target))
            throw new ChessException("Movimento inválido para peça selecionada");
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
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
}

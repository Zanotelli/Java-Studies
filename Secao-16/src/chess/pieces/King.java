package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return (piece == null) || (piece.getColor() != getColor());
    }

    @Override
    public boolean[][] possibleMoves() {
       boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

       Position p = new Position(0,0);

        // acima
        p.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);

        // abaixo
        p.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);

        // esquerda
        p.setValues(position.getRow(), position.getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);

        // direita
        p.setValues(position.getRow(), position.getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);


        // acima esquerda
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);

        // abaixo esquerda
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);

        // acima direita
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);

        // abaixo direita
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p))
            mat[p.getRow()][p.getColumn()] = canMove(p);

       return mat;
    }

}

package chess;

import boardgame.Position;

public class ChessPosition {

    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if(column < 'a' || column > 'h' || row < 1 || row > 8)
            throw new ChessException("Erro ao instanciar ChessPosition. Os valores válidos são de a1 à h8.");
        this.column = column;
        this.row = row;
    }

    /**
     * Converte a posição do tabuleiro para a forma matricial da
     * classe Position.
     * @return O objeto Position equivalente
     */
    protected Position toPosition(){
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position p){
        return new ChessPosition((char) ('a' - p.getColumn()), 8 - p.getRow());
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString(){ return "" + column + row; }
}

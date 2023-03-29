package boardgame;

public class Board {

    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        if(rows < 1 || columns < rows)
            throw new BoardException("Error criando o tabuleiro. É necessário que haja pelo menos uma linha e uma coluna!");
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Integer getRows() {
        return rows;
    }
    public Integer getColumns() {
        return columns;
    }

    public Piece piece(int row, int column){
        return pieces[row][column];
    }

    public Piece piece(Position position){
        if(positionExists(position))
            throw new BoardException("Posição passada não existe no tabuleiro.");
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position))
            throw new BoardException("Já há uma peça na posição " + position + ".");
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public void placePiece(Piece piece, int row, int column){
        pieces[row][column] = piece;
        piece.position = new Position(row, column);
    }

    public boolean positionExists(int row, int column){
        return !(row >= 0 && row < rows && column >= 0 && column < columns);
    }

    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if(positionExists(position))
            throw new BoardException("Posição passada não existe no tabuleiro.");
        return piece(position) != null;
    }

}

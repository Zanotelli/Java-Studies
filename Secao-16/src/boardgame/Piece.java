package boardgame;

public abstract class Piece {
    protected Position position;

    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    public abstract boolean[][] possibleMoves();

    /**
     * Verifica na matriz de 'movimentos possíveis' da peça se ela
     * pode mover para uma posição desejada. 'Hook method' que faz um
     * gancho com um método abstrado 'possibleMoves' impelemntado na
     * subclasse
     * @param position A posição de destino desejada
     * @return Verdadeiro, caso a peça possa mover para a
     * posição passada, falso caso o contrário
     */
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    /**
     * Verifica se a peça pode se mover para alguma posição.'Hook method'
     * que faz um gancho com um método abstrado 'possibleMoves' impelemntado
     * na subclasse
     * @return Verdadeiro, caso a peça possa mover para alguma
     * posição, falso caso o contrário
     */
    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for(int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat.length; j++){
                if(mat[i][j])
                    return true;
            }
        }
        return false;
    }

    protected Board getBoard() {
        return board;
    }
}

package chess;

public class Horse extends ChessPiece {
    Horse(String color) {
        super(color);
        this.symbol = String.valueOf(color.equalsIgnoreCase("white") ? '\u265e' : '\u2658');
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (ChessBoard.checkPos(toLine) && ChessBoard.checkPos(toColumn)) {
            boolean isLineLongMoving
                    = Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1,
                    isColumnLongMoving
                            = Math.abs(column - toColumn) == 2 && Math.abs(line - toLine) == 1;
            if (isLineLongMoving || isColumnLongMoving) return true;
            else return false;
        } else return false;
    }
}

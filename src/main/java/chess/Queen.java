package chess;

public class Queen extends ChessPiece {
    Queen(String color) {
        super(color);
        this.symbol = String.valueOf(color.equalsIgnoreCase("white") ? '\u265b' : '\u2655');
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (ChessBoard.checkPos(toLine) && ChessBoard.checkPos(toColumn)) {
            if (line != toLine && Math.abs(line - toLine) == Math.abs(column - toColumn)) return true;
            else return false;
        } else return false;
    }

}
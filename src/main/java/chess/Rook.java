package chess;

public class Rook extends ChessPiece {
    Rook(String color) {
        super(color);
        this.symbol = String.valueOf(color.equalsIgnoreCase("white") ? '\u265c' : '\u2656');
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (ChessBoard.checkPos(toLine) && ChessBoard.checkPos(toColumn)) {
            if ((line == toLine && column != toColumn) || (line != toLine && column == toColumn)) return true;
            else return false;
        } else return false;
    }
}

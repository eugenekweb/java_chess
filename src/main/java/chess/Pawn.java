package chess;

public class Pawn extends ChessPiece {
    private final int startLine;

    Pawn(String color) {
        super(color);
        this.startLine = color.equalsIgnoreCase("white") ? 1 : 6;
        this.symbol = String.valueOf(color.equalsIgnoreCase("white") ? '\u265f' : '\u2659');
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (ChessBoard.checkPos(toLine) && ChessBoard.checkPos(toColumn)) {
            int step = line == startLine ? 2 : 1;
            if (column == toColumn && line != toLine && (Math.abs(toLine - line) <= step)) return true;
            else return false;
        } else return false;
    }
}

package chess;

public abstract class ChessPiece {
    private final String color;
    protected String symbol;
    boolean check = true;

    ChessPiece(String color) {
        this.color = color;
    }

    public abstract boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn);

    public String getColor() {
        return this.color;
    }

    public String getSymbol() {
        return this.symbol;
    }
}


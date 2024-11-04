package chess;

public abstract class ChessPiece {
    private final String color;
    private final String name;
    protected String symbol;
    protected boolean check = true;

    ChessPiece(String color) {
        this.color = color;
        this.name = this.getClass().getSimpleName();
    }

    public abstract boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn);

    public String getColor() {
        return this.color;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }
}


package chess;

public class King extends ChessPiece {
    King(String color) {
        super(color);
        this.symbol = String.valueOf(color.equalsIgnoreCase("white") ? '\u265a' : '\u2654');
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (ChessBoard.checkPos(toLine) && ChessBoard.checkPos(toColumn)) {
            int steps = Math.abs(line - toLine) + Math.abs(column - toColumn);
            boolean isDiagonalMove = line != toLine && column != toColumn && (steps == 2);
            if (isDiagonalMove || steps == 1) return true;
            else return false;
        } else return false;
    }

    public boolean isNotUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece position = board.board[i][j];
                if (position == null || position.getColor().equalsIgnoreCase(this.getColor()))
                    continue;
                if (position.canMoveToPosition(board, i, j, line, column)) {
                    System.out.printf("Клетка [%d, %d] под боем фигуры %s [%d, %d]\n", line, column, position.getName(), i, j);
                    return false;
                }
            }
        }
        return true;
    }
}

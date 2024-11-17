package chess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public void checkCheck(ChessBoard board, int line, int column) {
        boolean isCheck = isUnderAttack(board, line, column);
        if (isCheck)
            System.out.println("Шах! Защищайтесь!");
        final int[][] kingMoves = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int count = 0;
        for (int[] move : kingMoves) {
            int toLine = line + move[0];
            int toColumn = column + move[1];
            if (!(ChessBoard.checkPos(toLine) && ChessBoard.checkPos(toColumn))) continue;
            if (board.board[toLine][toColumn] == null && !isUnderAttack(board, toLine, toColumn)) count++;
        }
        if (count == 0 && isCheck) {
            System.out.println("Нет ходов! Шах и мат!");
            System.out.println(board.nowPlayerColor() + " проиграли!");
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece position = board.board[i][j];
                if (position == null || position.getColor().equalsIgnoreCase(this.getColor()))
                    continue;
                if (position.canMoveToPosition(board, i, j, line, column) && board.isFreeWay(i, j, line, column)) {
                    System.out.printf("Клетка [%d, %d] под боем фигуры %s %s [%d, %d]\n", line, column, position.getName(), position.getColor(), i, j);
                    return true;
                }
            }
        }
        return false;
    }
}

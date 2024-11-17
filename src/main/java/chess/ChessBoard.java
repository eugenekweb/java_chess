package chess;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                if (board[startLine][startColumn].getName().equalsIgnoreCase("king")) {
                    if (((King) board[startLine][startColumn]).isUnderAttack(this, endLine, endColumn)) return false;
                }
                if (!isFreeWay(startLine, startColumn, endLine, endColumn)) return false;
                if (board[endLine][endColumn] != null) {
                    if (board[startLine][startColumn].getColor().equals(board[endLine][endColumn].getColor()))
                        return false;
                    else
                        System.out.printf("%s %s ест %s %s%n", board[startLine][startColumn].getColor(), board[startLine][startColumn].getName(), board[endLine][endColumn].getColor(), board[endLine][endColumn].getName());
                }
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[endLine][endColumn].check = false;
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println(" ".repeat(18) + "Player 2 (Black)");
//        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
//        System.out.println("\tA\tB\tC\tD\tE\tF\tG\tH");

        for (int i = 7; i > -1; i--) {
            System.out.print((i) + "\s\s\s");
//            System.out.print((i + 1) + "\s\s\s");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    char square = (i + j) % 2 == 0 ? '\u25A1' : '\u25A0';
                    System.out.print(square + "\s\s\s");
                } else {
                    System.out.print(board[i][j].getSymbol() + "\t"); // + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
//            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1 (White)");
        System.out.println("-".repeat(34));
    }

    public static boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equalsIgnoreCase("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getName().equalsIgnoreCase("Rook")
                    && board[0][4].getName().equalsIgnoreCase("King")
                    && board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                if (board[0][0].getColor().equalsIgnoreCase("White")
                        && board[0][4].getColor().equalsIgnoreCase("White")
                        && board[0][0].check && board[0][4].check
                        && !(new King("White").isUnderAttack(this, 0, 2))) {
                    board[0][4] = null;
                    board[0][2] = new King("White");
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");
                    board[0][3].check = false;
                    nowPlayer = "Black";
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getName().equalsIgnoreCase("Rook")
                    && board[7][4].getName().equalsIgnoreCase("King")
                    && board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                if (board[7][0].getColor().equalsIgnoreCase("Black")
                        && board[7][4].getColor().equalsIgnoreCase("Black")
                        && board[7][0].check && board[7][4].check
                        && !(new King("Black").isUnderAttack(this, 7, 2))) {
                    board[7][4] = null;
                    board[7][2] = new King("Black");
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");
                    board[7][3].check = false;
                    nowPlayer = "White";
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equalsIgnoreCase("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getName().equalsIgnoreCase("Rook")
                    && board[0][4].getName().equalsIgnoreCase("King")
                    && board[0][5] == null && board[0][6] == null) {
                if (board[0][7].getColor().equalsIgnoreCase("White")
                        && board[0][4].getColor().equalsIgnoreCase("White")
                        && board[0][7].check && board[0][4].check
                        && !(new King("White").isUnderAttack(this, 0, 6))) {
                    board[0][4] = null;
                    board[0][6] = new King("White");
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");
                    board[0][5].check = false;
                    nowPlayer = "Black";
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getName().equalsIgnoreCase("Rook")
                    && board[7][4].getName().equalsIgnoreCase("King")
                    && board[7][5] == null && board[7][6] == null) {
                if (board[7][7].getColor().equalsIgnoreCase("Black")
                        && board[7][4].getColor().equalsIgnoreCase("Black")
                        && board[7][7].check && board[7][4].check
                        && !(new King("Black").isUnderAttack(this, 7, 6))) {
                    board[7][4] = null;
                    board[7][6] = new King("Black");
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");
                    board[7][5].check = false;
                    nowPlayer = "White";
                    return true;
                } else return false;
            } else return false;
        }
    }

    private boolean isFreeWay(int line, int column, int toLine, int toColumn) {
        if (board[line][column].getName().equalsIgnoreCase("Horse")) return true;
        int lineStep = Integer.signum(toLine - line);
        int columnStep = Integer.signum(toColumn - column);

        int currentLine = line + lineStep;
        int currentColumn = column + columnStep;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += lineStep;
            currentColumn += columnStep;
        }
        return true;

    }
}
package chess;

public class Position {
    private int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public static Position fromAlgebraic(String notation) {
        if (notation.length() != 2) return null;
        char file = notation.charAt(0);
        char rank = notation.charAt(1);
        int col = file - 'a';
        int row = 8 - (rank - '0');
        return new Position(row, col);
    }

    public static boolean isValid(Position pos) {
        return pos != null && pos.row >= 0 && pos.row < 8 && pos.col >= 0 && pos.col < 8;
    }
}

package chess.pieces;

import chess.Board;
import chess.Position;

public class Rook extends Piece {
    public Rook(Color color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (from.equals(to)) return false;

        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        if (rowDiff != 0 && colDiff != 0) return false; // Rook moves straight

        int rowStep = Integer.signum(rowDiff);
        int colStep = Integer.signum(colDiff);

        int currentRow = from.getRow() + rowStep;
        int currentCol = from.getCol() + colStep;

        while (currentRow != to.getRow() || currentCol != to.getCol()) {
            if (board.getPiece(new Position(currentRow, currentCol)) != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }

        Piece target = board.getPiece(to);
        return target == null || target.getColor() != color;
    }

    @Override
    public String getSymbol() { return color == Color.WHITE ? "R" : "r"; }
}

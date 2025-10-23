package chess.pieces;

import chess.Board;
import chess.Position;

public class Bishop extends Piece {
    public Bishop(Color color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (from.equals(to)) return false;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        if (rowDiff != colDiff) return false;

        int rowStep = (to.getRow() > from.getRow()) ? 1 : -1;
        int colStep = (to.getCol() > from.getCol()) ? 1 : -1;

        int currentRow = from.getRow() + rowStep;
        int currentCol = from.getCol() + colStep;

        while (currentRow != to.getRow()) {
            if (board.getPiece(new Position(currentRow, currentCol)) != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }

        Piece target = board.getPiece(to);
        return target == null || target.getColor() != color;
    }

    @Override
    public String getSymbol() { return color == Color.WHITE ? "B" : "b"; }
}

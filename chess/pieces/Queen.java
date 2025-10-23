package chess.pieces;

import chess.Board;
import chess.Position;

public class Queen extends Piece {
    public Queen(Color color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (from.equals(to)) return false;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        int rowStep = Integer.signum(to.getRow() - from.getRow());
        int colStep = Integer.signum(to.getCol() - from.getCol());

        if (rowDiff == colDiff || rowDiff == 0 || colDiff == 0) {
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

        return false;
    }

    @Override
    public String getSymbol() { return color == Color.WHITE ? "Q" : "q"; }
}

package chess.pieces;

import chess.Board;
import chess.Position;

public class Pawn extends Piece {
    public Pawn(Color color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int direction = (color == Color.WHITE) ? -1 : 1;
        int startRow = (color == Color.WHITE) ? 6 : 1;
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        if (colDiff == 0) {
            if (rowDiff == direction && board.getPiece(to) == null) return true;
            if (from.getRow() == startRow && rowDiff == 2 * direction && board.getPiece(to) == null) return true;
        } else if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            return board.getPiece(to) != null && board.getPiece(to).getColor() != color;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "P" : "p";
    }
}

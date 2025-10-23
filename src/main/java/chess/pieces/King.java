package chess.pieces;

import chess.Board;
import chess.Position;

public class King extends Piece {
    public King(Color color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (rowDiff <= 1 && colDiff <= 1) {
            Piece target = board.getPiece(to);
            return target == null || target.getColor() != color;
        }

        return false;
    }

    @Override
    public String getSymbol() { return color == Color.WHITE ? "K" : "k"; }
}

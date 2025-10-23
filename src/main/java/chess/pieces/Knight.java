package chess.pieces;

import chess.Board;
import chess.Position;

public class Knight extends Piece {
    public Knight(Color color) { super(color); }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) return false;

        Piece target = board.getPiece(to);
        return target == null || target.getColor() != color;
    }

    @Override
    public String getSymbol() { return color == Color.WHITE ? "N" : "n"; }
}

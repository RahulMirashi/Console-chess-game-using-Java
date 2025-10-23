package chess.pieces;

import chess.Board;
import chess.Position;

public abstract class Piece {
    public enum Color { WHITE, BLACK }
    protected Color color;

    public Piece(Color color) { this.color = color; }

    public Color getColor() { return color; }

    public abstract boolean isValidMove(Board board, Position from, Position to);
    public abstract String getSymbol();
}

package chess;

import chess.pieces.Pawn;
import chess.pieces.Piece;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    @Test
    void testPawnForwardMove() {
        Board board = new Board();
        board.initialize();
        Position from = Position.fromAlgebraic("e2");
        Position to = Position.fromAlgebraic("e3");

        Piece pawn = board.getPiece(from);
        assertTrue(pawn.isValidMove(board, from, to), "Pawn should move from e2 to e3");
    }

    @Test
    void testPawnDoubleStep() {
        Board board = new Board();
        board.initialize();
        Position from = Position.fromAlgebraic("e2");
        Position to = Position.fromAlgebraic("e4");

        Piece pawn = board.getPiece(from);
        assertTrue(pawn.isValidMove(board, from, to), "Pawn should move two steps from e2 to e4 on first move");
    }

    @Test
    void testInvalidBackwardMove() {
        Board board = new Board();
        board.initialize();
        Position from = Position.fromAlgebraic("e2");
        Position to = Position.fromAlgebraic("e1");

        Piece pawn = board.getPiece(from);
        assertFalse(pawn.isValidMove(board, from, to), "Pawn cannot move backwards");
    }
}

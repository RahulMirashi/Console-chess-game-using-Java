package chess;

import chess.pieces.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testMovePieceSuccess() {
        Board board = new Board();
        board.initialize();

        Position from = Position.fromAlgebraic("e2");
        Position to = Position.fromAlgebraic("e4");

        boolean moved = board.movePiece(from, to, Piece.Color.WHITE);
        assertTrue(moved, "Pawn should move from e2 to e4");
        assertNull(board.getPiece(from), "Original square should be empty");
        assertNotNull(board.getPiece(to), "Destination should have the pawn");
    }

    @Test
    void testMoveWrongPlayerPiece() {
        Board board = new Board();
        board.initialize();

        Position from = Position.fromAlgebraic("e7");
        Position to = Position.fromAlgebraic("e5");

        boolean moved = board.movePiece(from, to, Piece.Color.WHITE);
        assertFalse(moved, "White cannot move black's pawn");
    }

    @Test
    void testIllegalMoveLeavesKingInCheck() {
        Board board = new Board();
        board.initialize();

        // Example move that causes check to itself
        board.movePiece(Position.fromAlgebraic("e2"), Position.fromAlgebraic("e4"), Piece.Color.WHITE);
        board.movePiece(Position.fromAlgebraic("d7"), Position.fromAlgebraic("d5"), Piece.Color.BLACK);

        boolean moved = board.movePiece(Position.fromAlgebraic("f1"), Position.fromAlgebraic("b5"), Piece.Color.WHITE);
        assertTrue(moved, "Bishop moves to give check");
        assertTrue(board.isKingInCheck(Piece.Color.BLACK), "Black king should be in check");
    }
}

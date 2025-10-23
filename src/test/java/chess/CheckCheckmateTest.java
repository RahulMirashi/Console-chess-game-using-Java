package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckCheckmateTest {

    @Test
    void testFoolsMateCheck() {
        Board board = new Board();
        board.initialize();

        // Fool's Mate
        board.movePiece(Position.fromAlgebraic("f2"), Position.fromAlgebraic("f3"), Piece.Color.WHITE);
        board.movePiece(Position.fromAlgebraic("e7"), Position.fromAlgebraic("e5"), Piece.Color.BLACK);
        board.movePiece(Position.fromAlgebraic("g2"), Position.fromAlgebraic("g4"), Piece.Color.WHITE);
        board.movePiece(Position.fromAlgebraic("d8"), Position.fromAlgebraic("h4"), Piece.Color.BLACK);

        assertTrue(board.isKingInCheck(Piece.Color.WHITE), "White king should be in check");
    }
}

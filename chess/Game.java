package chess;

import chess.pieces.*;
import chess.player.Player;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player white;
    private Player black;
    private Player currentPlayer;
    private Scanner scanner = new Scanner(System.in);

    public Game() {
        board = new Board();
        board.initialize();
        setupPlayers();
    }

    private void setupPlayers() {
        System.out.print("Enter White player name: ");
        white = new Player(scanner.nextLine(), Piece.Color.WHITE);

        System.out.print("Enter Black player name: ");
        black = new Player(scanner.nextLine(), Piece.Color.BLACK);

        currentPlayer = white;
    }

    public void start() {
        while (true) {
            board.display();
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getColor() + ")");
            System.out.print("Enter move (e.g., e2e4) or 'quit': ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit") || input.equals("q")) {
                System.out.println("Game over.");
                break;
            }

            if (input.length() == 4) {
                Position from = Position.fromAlgebraic(input.substring(0, 2));
                Position to = Position.fromAlgebraic(input.substring(2, 4));

                boolean isValid = board.movePiece(from, to, currentPlayer.getColor());

                if (isValid) {
                    // ✅ Check if opponent is in check after a valid move
                    if (board.isKingInCheck(getOpponent().getColor())) {
                        System.out.println("⚠ " + getOpponent().getName() + " is in CHECK!");
                    }

                    // ✅ Optional: Checkmate logic (basic version)
                    if (isCheckmate(getOpponent().getColor())) {
                        board.display();
                        System.out.println("✅ CHECKMATE! " + currentPlayer.getName() + " wins!");
                        break;
                    }

                    switchTurn();
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Invalid input format. Try again.");
            }
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == white) ? black : white;
    }

    private Player getOpponent() {
        return (currentPlayer == white) ? black : white;
    }

    // ✅ Basic Checkmate Detection (no legal moves + king in check)
    private boolean isCheckmate(Piece.Color defenderColor) {
        // If king isn't in check, not checkmate
        if (!board.isKingInCheck(defenderColor)) {
            return false;
        }

        // Try all possible moves to escape check
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                if (piece != null && piece.getColor() == defenderColor) {
                    for (int r = 0; r < 8; r++) {
                        for (int c = 0; c < 8; c++) {
                            Position from = new Position(row, col);
                            Position to = new Position(r, c);

                            // Try a move; if valid and doesn't leave king in check = not checkmate
                            if (board.movePiece(from, to, defenderColor)) {
                                // Undo move
                                board.movePiece(to, from, defenderColor);
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true; // No escape → checkmate
    }
}

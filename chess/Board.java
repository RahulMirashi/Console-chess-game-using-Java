package chess;

import chess.pieces.*;

public class Board {
    private Piece[][] squares = new Piece[8][8];

    public void initialize() {
        // Place pieces for both colors
        // White pieces
        squares[7][0] = new Rook(Piece.Color.WHITE);
        squares[7][1] = new Knight(Piece.Color.WHITE);
        squares[7][2] = new Bishop(Piece.Color.WHITE);
        squares[7][3] = new Queen(Piece.Color.WHITE);
        squares[7][4] = new King(Piece.Color.WHITE);
        squares[7][5] = new Bishop(Piece.Color.WHITE);
        squares[7][6] = new Knight(Piece.Color.WHITE);
        squares[7][7] = new Rook(Piece.Color.WHITE);
        for (int i = 0; i < 8; i++) squares[6][i] = new Pawn(Piece.Color.WHITE);

        // Black pieces
        squares[0][0] = new Rook(Piece.Color.BLACK);
        squares[0][1] = new Knight(Piece.Color.BLACK);
        squares[0][2] = new Bishop(Piece.Color.BLACK);
        squares[0][3] = new Queen(Piece.Color.BLACK);
        squares[0][4] = new King(Piece.Color.BLACK);
        squares[0][5] = new Bishop(Piece.Color.BLACK);
        squares[0][6] = new Knight(Piece.Color.BLACK);
        squares[0][7] = new Rook(Piece.Color.BLACK);
        for (int i = 0; i < 8; i++) squares[1][i] = new Pawn(Piece.Color.BLACK);
    }

    public Piece getPiece(Position pos) {
        return squares[pos.getRow()][pos.getCol()];
    }

    public boolean movePiece(Position from, Position to, Piece.Color playerColor) {
        if (!Position.isValid(from) || !Position.isValid(to)) return false;

        Piece piece = getPiece(from);
        if (piece == null || piece.getColor() != playerColor) return false;
        if (!piece.isValidMove(this, from, to)) return false;

        Piece target = getPiece(to);
        if (target != null && target.getColor() == playerColor) return false;

        // SIMULATE move
        squares[to.getRow()][to.getCol()] = piece;
        squares[from.getRow()][from.getCol()] = null;

        if (isKingInCheck(playerColor)) {
            // UNDO move
            squares[from.getRow()][from.getCol()] = piece;
            squares[to.getRow()][to.getCol()] = target;
            System.out.println("⚠ Illegal move! You cannot leave your King in check.");
            return false;
        }

        return true; // Move is valid & king is safe
    }

    // Silent move check for checkmate detection
    public boolean canMovePiece(Position from, Position to, Piece.Color playerColor) {
        Piece piece = getPiece(from);
        if (piece == null || piece.getColor() != playerColor) return false;
        if (!piece.isValidMove(this, from, to)) return false;

        Piece target = getPiece(to);

        // Simulate move
        squares[to.getRow()][to.getCol()] = piece;
        squares[from.getRow()][from.getCol()] = null;

        boolean kingSafe = !isKingInCheck(playerColor);

        // Undo move
        squares[from.getRow()][from.getCol()] = piece;
        squares[to.getRow()][to.getCol()] = target;

        return kingSafe;
    }

    public boolean isKingInCheck(Piece.Color color) {
        Position kingPos = null;

        outer: for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece instanceof King && piece.getColor() == color) {
                    kingPos = new Position(row, col);
                    break outer;
                }
            }
        }

        if (kingPos == null) return false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece enemy = squares[row][col];
                if (enemy != null && enemy.getColor() != color) {
                    if (enemy.isValidMove(this, new Position(row, col), kingPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void display() {
        System.out.println();
        System.out.println("    a  b  c  d  e  f  g  h");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + "  ");
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                System.out.print((piece != null ? piece.getSymbol() : ".") + "  ");
            }
            System.out.println(" " + (8 - row));
        }
        System.out.println("    a  b  c  d  e  f  g  h\n");
    }
}

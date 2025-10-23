public boolean movePiece(Position from, Position to, Piece.Color playerColor) {
    if (!Position.isValid(from) || !Position.isValid(to)) {
        System.out.println("⚠ Invalid board coordinates.");
        return false;
    }

    Piece piece = getPiece(from);
    if (piece == null) {
        System.out.println("⚠ No piece at source square.");
        return false;
    }
    if (piece.getColor() != playerColor) {
        System.out.println("⚠ That's not your piece!");
        return false;
    }

    if (!piece.isValidMove(this, from, to)) {
        System.out.println("⚠ Illegal move for " + piece.getClass().getSimpleName());
        return false;
    }

    Piece target = getPiece(to);
    if (target != null && target.getColor() == playerColor) {
        System.out.println("⚠ You cannot capture your own piece.");
        return false;
    }

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

public boolean isKingInCheck(Piece.Color color) {
    Position kingPos = null;

    // Find the king
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

    // Check if any opponent piece can attack king
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece enemy = squares[row][col];
            if (enemy != null && enemy.getColor() != color) {
                if (enemy.isValidMove(this, new Position(row, col), kingPos)) {
                    return true; // ✅ Just return true immediately, no prints here
                }
            }
        }
    }

    return false;
}

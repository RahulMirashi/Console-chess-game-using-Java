♟️ Java Chess Game
A fully object-oriented chess game implemented in Java, playable through the command line. The project models all core chess mechanics — piece movement, check, checkmate, and turn management — using clean and modular class design.

Features:
Complete rule-based move validation for all pieces
Real-time check and checkmate detection
Turn-based player interaction via console
JUnit 5 test coverage for key components (Board, Piece, Game)
Maven project structure for easy build and testing

Architecture:
chess → Core game logic and flow
chess.pieces → Individual piece behavior
chess.player → Player representation

Testing:
Includes unit and system tests to ensure rule correctness and stability.

Technologies:
Java 17 • Maven • JUnit 5 • OOP Principles

📦 Run the game:
mvn clean package
java -jar target/ChessGame-1.0-SNAPSHOT.jar




🎓 Developed as part of the Software Engineering project at UCD.

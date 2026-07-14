# Kwazam Chess

Java implementation of Kwazam Chess, a Malaysian chess variant, built with a clean MVC architecture.

## Structure
- `Board.java` — board state and rules
- `GameModel.java` — game logic and state management
- `GameController.java` — input handling, connects model and view
- `GameView.java` — Swing-based GUI
- `Main.java` — entry point
- `Biz.java`, `Piece.java`, `Ram.java`, `Sau.java`, `Tor.java`, `Xor.java` — piece definitions and supporting game logic

## How to Run
Make sure you have Java installed (JDK 8+), then from the folder containing all the `.java` files:

```bash
javac *.java
java Main
```

This compiles the project and launches the Swing-based GUI window.

## Stack
Java, Swing, MVC architecture, BlueJ

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

public class GameView {
    private JFrame frame;
    private JButton[][] buttons;
    private JButton save;
    private GameModel model;
    private JLabel currentPlayerLabel;
    private int selectedX = -1;
    private int selectedY = -1;
    private JButton backToMenuButton;
    private int colourboard = 0;
    public GameView(GameModel model) {
        this.model = model;
        frame = new JFrame("Kwazam Chess");
        buttons = new JButton[8][5];
        frame.setLayout(new BorderLayout());
        initializeMenu();
        initializeButtons();
        initializeCurrentPlayerLabel();
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu gameMenu = new JMenu("Game");
        
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        
        JMenuItem loadGameItem = new JMenuItem("Load Game");
        loadGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        
        gameMenu.add(newGameItem);
        gameMenu.add(loadGameItem);
        menuBar.add(gameMenu);
        
        frame.setJMenuBar(menuBar);
    }
    
    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 5));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.LIGHT_GRAY);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                buttonPanel.add(buttons[i][j]);
            }
        }
        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    private void initializeCurrentPlayerLabel() {
        save = new JButton("Save");
        currentPlayerLabel = new JLabel("Current Player: " + model.getCurrentPlayer(), SwingConstants.CENTER);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(); 
            }
        });
        frame.add(currentPlayerLabel, BorderLayout.SOUTH);
        frame.add(save, BorderLayout.NORTH);
    }
    
    private void startNewGame() {
        // Reset the game model and update the view
        model = new GameModel(); // Create a new game model
        updateBoard(); // Refresh the board
    }
    
    private void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader("save.txt"))) {
            String line;
            // Read the current player
            line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(": ");
                if (parts.length > 1) {
                    model.setCurrentPlayer(parts[1].trim()); // Set the current player
                }
            }
            // Clear the board
            model.getBoard().clearBoard(); // Clear the current board state
    
            // Read the board state
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String pieceType = parts[0].trim();
                    String color = parts[1].trim();
                    int row = Integer.parseInt(parts[2].trim());
                    int col = Integer.parseInt(parts[3].trim());
    
                    // Create the piece based on type and color
                    Piece piece = createPiece(pieceType, color, row, col);
                    if (piece != null) {
                        model.getBoard().setPiece(row, col, piece); // Place the piece on the board
                    }
                }
            }
            updateBoard(); // Refresh the board to show loaded state
            //backToMenuButton.setVisible(true); // Show the back to menu button
            JOptionPane.showMessageDialog(frame, "Game loaded successfully!", "Load Game", JOptionPane.INFORMATION_MESSAGE);
            printBoardState();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading game!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void printBoardState() {
        System.out.println("Current Player: " + model.getCurrentPlayer());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                Piece piece = model.getBoard().getPiece(i, j);
                if (piece != null) {
                    System.out.print(piece.getClass().getSimpleName() + "(" + piece.getColor() + ") ");
                } else {
                    System.out.print("Empty ");
                }
            }
            System.out.println();
        }
    }
    
    private void initializeBackToMenuButton() {
        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToMenu();
            }
        });
        frame.add(backToMenuButton, BorderLayout.NORTH);
        backToMenuButton.setVisible(false); // Initially hide the button
    }

    public void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                Piece piece = model.getBoard().getPiece(i, j);
                if (piece != null) {
                    buttons[i][j].setIcon(loadImage(piece));
                } else {
                    buttons[i][j].setIcon(null); // Clear the icon if no piece
                }
            }
        }
        currentPlayerLabel.setText("Current Player: " + model.getCurrentPlayer()); // Update current player label
    }

    private ImageIcon loadImage(Piece piece) {
        String imagePath = "";
        String imageName = piece.getClass().getSimpleName().toLowerCase() + piece.getColor().toLowerCase() + ".png";
        try {
            Image img = ImageIO.read(new File(imagePath + imageName));
            return new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH)); // Resize image
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if image not found
        }
    }
    
    private void saveGame() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))) {
            // Save current player
            writer.write("Current Player: " + model.getCurrentPlayer() + "\n");
            // Save board state
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 5; j++) {
                    Piece piece = model.getBoard().getPiece(i, j);
                    if (piece != null) {
                        writer.write(piece.getClass().getSimpleName() + ";" + piece.getColor() + ";" + i + ";" + j + "\n");
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Game saved successfully!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving game!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void backToMenu() {
        // Reset the game model and hide the back to menu button
        model = new GameModel(); // Reset to a new game model
        updateBoard(); // Refresh the board
        backToMenuButton.setVisible(false); // Hide the back to menu button
    }
     
    private Piece createPiece(String pieceType, String color, int row, int col) {
        switch (pieceType.toLowerCase()) {
            case "ram":
                return new Ram(color, row, col);
            case "biz": 
                return new Biz(color, row, col);
            case "tor":
                return new Tor(color, row, col);
            case "xor":
                return new Xor(color, row, col);
            case "sau":
                return new Sau(color, row, col);
            default:
                return null; // Unknown piece type
        }
    }

    public JFrame getFrame() {
        return frame;
    }
    private class ButtonClickListener implements ActionListener {
        private int x;
        private int y;
    
        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            Piece clickedPiece = model.getBoard().getPiece(x, y);
    
            // Check if the clicked piece is the same as the selected piece
            if (selectedX == x && selectedY == y) {
                // Unselect the piece
                buttons[selectedX][selectedY].setBackground(Color.LIGHT_GRAY); // Reset background color
                resetHighlights(); // Clear highlights
                selectedX = -1; // Reset selection
                selectedY = -1; // Reset selection
            } else if (clickedPiece != null && clickedPiece.getColor().equals(model.getCurrentPlayer())) {
                // If a different piece of the same color is clicked, unselect the previous selection
                if (selectedX != -1 && selectedY != -1) {
                    buttons[selectedX][selectedY].setBackground(Color.LIGHT_GRAY); // Reset previous selection
                }
                // Select the new piece
                selectedX = x;
                selectedY = y;
                buttons[x][y].setBackground(Color.YELLOW); // Highlight selected piece
                highlightValidMoves(clickedPiece); // Highlight valid moves
            } else {
                // Move the selected piece if a valid move is attempted
                if (selectedX != -1 && selectedY != -1) {
                    if (model.movePiece(selectedX, selectedY, x, y)) {
                        buttons[selectedX][selectedY].setBackground(Color.LIGHT_GRAY); // Reset previous selection
                        resetHighlights(); // Clear highlights
                        selectedX = -1; // Reset selection
                        selectedY = -1; // Reset selection
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            updateBoard(); // Refresh the board to show the current state
            printBoardState();
        }
    }
    private void highlightValidMoves(Piece piece) {
        // Get the current position of the selected piece
        int currentX = piece.getX();
        int currentY = piece.getY();
    
        // Check all possible moves on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                if (piece.isValidMove(i, j, model.getBoard())) {
                    buttons[i][j].setBackground(Color.GREEN); // Highlight valid move
                }
            }
        }
    }
    private void resetHighlights() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setBackground(Color.LIGHT_GRAY); // Reset to default color
            }
        }
    }
}
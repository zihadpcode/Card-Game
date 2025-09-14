import java.awt.*;
//Manages the JFrame layout, the card dimensions, and grid layout
import java.util.ArrayList;  // For dynamic arrays
import javax.swing.*; // Essentially turns images to objects, lets me use buttons, etc.

public class MatchCards {

    // Represents an individual card (name and image)
    static class Card {
        String cardName;
        ImageIcon cardImageIcon;

        Card(String cardName, ImageIcon cardImageIcon) {
            this.cardName = cardName;
            this.cardImageIcon = cardImageIcon;
        }

        public String toString() {
            return cardName;
        }
    }

    // List of unique card names (each will be duplicated to form pairs)
    String[] cardList = {
            "Ace-of-clubs",
            "Ace-of-diamonds",
            "Ace-of-hearts",
            "Ace-of-spade",
            "Jack-of-Hearts",
            "Jack-of-Spades",
            "King-of-diamonds",
            "King-of-spades",
            "Queen-of-clubs",
            "Queen-of-diamonds",
    };

    // Board dimensions
    int rows = 4;
    int columns = 5;
    int cardWidth = 120;
    int cardHeight = 164;

    // Game data
    ArrayList<Card> cardSet;
    ImageIcon cardBackImageIcon;

    // GUI components
    JFrame frame = new JFrame("Match Cards");
    JLabel textLabel = new JLabel();  // Error counter
    JLabel timerLabel = new JLabel(); // Timer
    JPanel textPanel = new JPanel();  // Top panel
    JPanel boardPanel = new JPanel(); // Game grid
    JPanel restartGamePanel = new JPanel(); // Bottom restart area
    JButton restartButton = new JButton();  // Restart button

    // Game state
    int errorCount = 0;
    int secondsElapsed = 0;
    Timer gameTimer;
    ArrayList<JButton> board;
    Timer hideCardTimer;
    boolean gameReady = false;
    JButton card1Selected;
    JButton card2Selected;

    MatchCards() {
        setupCards();
        shuffleCards();

        frame.setLayout(new BorderLayout());
        frame.setSize(columns * cardWidth, rows * cardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top panel with timer and error counter
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Errors: " + errorCount);

        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setText("Time: 0s");

        textPanel.setLayout(new GridLayout(1, 2));
        textPanel.setPreferredSize(new Dimension(columns * cardWidth, 30));
        textPanel.add(textLabel);
        textPanel.add(timerLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Game board grid
        board = new ArrayList<>();
        boardPanel.setLayout(new GridLayout(rows, columns));
        for (Card card : cardSet) {
            JButton tile = getJButton(card);
            board.add(tile);
            boardPanel.add(tile);
        }
        frame.add(boardPanel);

        // Restart button
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setText("Restart Game");
        restartButton.setPreferredSize(new Dimension(columns * cardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.setEnabled(false);
        restartButton.addActionListener(e -> {
            if (!gameReady) return;

            gameReady = false;
            restartButton.setEnabled(false);
            card1Selected = null;
            card2Selected = null;
            shuffleCards();

            for (int i = 0; i < board.size(); i++) {
                board.get(i).setIcon(cardSet.get(i).cardImageIcon);
            }

            errorCount = 0;
            secondsElapsed = 0;
            textLabel.setText("Errors: " + errorCount);
            timerLabel.setText("Time: 0s");
            hideCardTimer.start(); // Restart by flipping all back down
        });

        restartGamePanel.add(restartButton);
        frame.add(restartGamePanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        // Hides cards after previewing at game start
        hideCardTimer = new Timer(1500, e -> hideCards());
        hideCardTimer.setRepeats(false);
        hideCardTimer.start();

        // Game timer (ticks every second)
        gameTimer = new Timer(1000, e -> {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed + "s");
        });
        gameTimer.start();
    }

    // Creates a card tile as a JButton
    private JButton getJButton(Card card) {
        JButton tile = new JButton();
        tile.setPreferredSize(new Dimension(cardWidth, cardHeight));
        tile.setOpaque(true);
        tile.setIcon(card.cardImageIcon);
        tile.setFocusable(false);

        tile.addActionListener(e -> {
            if (!gameReady) return;

            JButton tile1 = (JButton) e.getSource();

            // Reveal only if the card is face-down
            if (tile1.getIcon() == cardBackImageIcon) {
                if (card1Selected == null) {
                    card1Selected = tile1;
                    int index = board.indexOf(card1Selected);
                    card1Selected.setIcon(cardSet.get(index).cardImageIcon);
                } else if (card2Selected == null) {
                    card2Selected = tile1;
                    int index = board.indexOf(card2Selected);
                    card2Selected.setIcon(cardSet.get(index).cardImageIcon);

                    // Check for match
                    if (card1Selected.getIcon() != card2Selected.getIcon()) {
                        errorCount++;
                        textLabel.setText("Errors: " + errorCount);
                        hideCardTimer.start(); // Show cards for short delay
                    } else {
                        // Match found — keep them face-up
                        card1Selected = null;
                        card2Selected = null;
                        checkWin(); // ✅ Check if all cards are matched
                    }
                }
            }
        });

        return tile;
    }

    // Loads all card images and prepares duplicates
    void setupCards() {
        cardSet = new ArrayList<>();

        for (String cardName : cardList) {
            java.net.URL imageUrl = getClass().getResource("/img/" + cardName + ".jpg");
            if (imageUrl == null) {
                throw new RuntimeException("Image not found: /img/" + cardName + ".jpg");
            }

            Image cardImg = new ImageIcon(imageUrl).getImage();
            ImageIcon cardImageIcon = new ImageIcon(
                    cardImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH)
            );

            cardSet.add(new Card(cardName, cardImageIcon));
        }

        // Duplicate for matching pairs
        cardSet.addAll(cardSet);

        // Load card back
        java.net.URL backImageUrl = getClass().getResource("/img/Playing-card-back-cover.jpg");
        if (backImageUrl == null) {
            throw new RuntimeException("Image not found: /img/Playing-card-back-cover.jpg");
        }

        Image cardBackImg = new ImageIcon(backImageUrl).getImage();
        cardBackImageIcon = new ImageIcon(
                cardBackImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH)
        );
    }

    // Shuffle cards randomly
    void shuffleCards() {
        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int) (Math.random() * cardSet.size());
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
    }

    // Hide unmatched cards or start the game
    void hideCards() {
        if (gameReady && card1Selected != null && card2Selected != null) {
            card1Selected.setIcon(cardBackImageIcon);
            card1Selected = null;
            card2Selected.setIcon(cardBackImageIcon);
            card2Selected = null;
        } else {
            for (JButton jButton : board) {
                jButton.setIcon(cardBackImageIcon);
            }
            gameReady = true;
            restartButton.setEnabled(true);
        }
    }

    // Check if all cards are matched; if so, stop the timer
    void checkWin() {
        boolean allMatched = true;

        for (JButton tile : board) {
            if (tile.getIcon() == cardBackImageIcon) {
                allMatched = false;
                break;
            }
        }

        if (allMatched) {
            gameTimer.stop();
            JOptionPane.showMessageDialog(frame, "🎉 You won!\nTime: " + secondsElapsed + "s\nErrors: " + errorCount);
        }
    }
}




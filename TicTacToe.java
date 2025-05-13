// Tic Tac Toe is fun!

import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    private Button[] buttons = new Button[9];
    private Button newGameButton;
    private boolean isXTurn = true;

    public TicTacToe() {
        setLayout(new BorderLayout());
        
        setTitle("AWT Tic Tac Toe Game");

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(3, 3));
        
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        
        add(panel, BorderLayout.CENTER);
        
        newGameButton = new Button("New Game");
        newGameButton.addActionListener(e -> resetGame());
        add(newGameButton, BorderLayout.SOUTH);

        setSize(400, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        
        if (clickedButton.getLabel().equals("")) {
            clickedButton.setLabel(isXTurn ? "X" : "O");
            isXTurn = !isXTurn;
            checkWinner();
        }
    }

    private void checkWinner() {
        String[][] board = new String[3][3];
        
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getLabel();
        }

        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                announceWinner(board[i][0]);
                return;
            }
            if (!board[0][i].equals("") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                announceWinner(board[0][i]);
                return;
            }
        }
        
        if (!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            announceWinner(board[0][0]);
            return;
        }
        
        if (!board[0][2].equals("") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            announceWinner(board[0][2]);
            return;
        }
    }

    private void announceWinner(String winner) {
        Dialog d = new Dialog(this, "Game Over", true);
        d.setLayout(new FlowLayout());
        Label l = new Label(winner + " wins!");
        d.add(l);
        Button ok = new Button("OK");
        ok.addActionListener(e -> {
            d.setVisible(false);
            resetGame();
        });
        d.add(ok);
        d.setSize(200, 100);
        d.setVisible(true);
    }

    private void resetGame() {
        for (Button button : buttons) {
            button.setLabel("");
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

package xogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * name : samaa shaban eissa
 * ID: 1700004
 * center: ain_shams
 */
public class Game extends JFrame implements ActionListener {

    private JPanel panel1;
    private JPanel panel2;
    private JLabel label;
    private JButton[][] buttons;
    private GridLayout gridLayout;
    private GridLayout gridLayout2;
    private int width = 400;
    private int height = 300;
    private int count = 0;
    private int Player1Count = 0;
    private int Player2Count = 0;
    private int GameSize=3;

    public Game() {       
        label = new JLabel("Player : ");
        panel1 = new JPanel();
        panel2 = new JPanel();
        buttons = new JButton[GameSize][GameSize];
        panel1.add(label);
        gridLayout = new GridLayout(GameSize, GameSize);
        panel2.setLayout(gridLayout);
        for (int i = 0; i < GameSize; i++) {
            for (int j = 0; j < GameSize; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                panel2.add(buttons[i][j]);
            }
        }
        gridLayout2 = new GridLayout(2, 1);
        setLayout(gridLayout2);
        add(panel1);
        add(panel2);
        setTitle("XO Game");
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        //pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText() != null && button.getText().length() > 0) {
            return;
        }
        if (count % 2 == 0) {
            label.setText("Player 1 turn");
            label.setFont(new Font("Arial", Font.PLAIN, 40));
            label.setForeground(Color.red);
            button.setText("X");
            button.setFont(new Font("Arial", Font.PLAIN, 40));
            button.setForeground(Color.red);
            count++;
            Player1Count++;
            if (Player1Count >= GameSize) {
                boolean isWin = IsPlayerWin("X");
                if (isWin) {
                    label.setText("Player 1 Won!!");
                    disableButtons();

                }
            }
        } else if (count % 2 == 1) {
            label.setText("Player 2 turn");
            label.setFont(new Font("Arial", Font.PLAIN, 40));
            label.setForeground(Color.blue);
            button.setText("O");
            button.setFont(new Font("Arial", Font.PLAIN, 40));
            button.setForeground(Color.blue);
            count++;
            Player2Count++;
            if (Player2Count >= GameSize) {
                boolean isWin = IsPlayerWin("O");
                if (isWin) {
                    label.setText("Player 2 Won!!");
                    disableButtons();
                }
            }
        }
        if (count == (GameSize * GameSize)) {
            count = 0;
            label.setText("The game is a draw");
            disableButtons();
        }
    }

    private boolean IsPlayerWin(String symbol) {
        boolean isWin = false;
        //Check Rows
        for (int row = 0; row < GameSize; row++) {
            int count = 0;
            for (int col = 0; col < GameSize; col++) {
                JButton button = buttons[row][col];
                if (!button.getText().equals(symbol)) {
                    count = 0;
                } else {
                    count++;
                    if (count == GameSize) {
                        isWin = true;
                        return isWin;
                    }
                }
            }
        }
        //Check cols
        for (int col = 0; col <GameSize ; col++) {
            int count = 0;
            for (int row = 0; row < GameSize; row++) {
                JButton button = buttons[row][col];
                if (!button.getText().equals(symbol)) {
                    count = 0;
                } else {
                    count++;
                    if (count == GameSize) {
                        isWin = true;
                        return isWin;
                    }
                }
            }
        }
        //Check left digonal
        int count = 0;
        for (int row = 0, col = 0; row < GameSize; row++, col++) {
            JButton button = buttons[row][col];
            if (!button.getText().equals(symbol)) {
                count = 0;
            } else {
                count++;
                if (count == GameSize) {
                    isWin = true;
                    return isWin;
                }
            }
        }

        //Check right digonal
        count = 0;
        for (int row = 0, col = GameSize-1; row < GameSize; row++, col--) {
            JButton button = buttons[row][col];
            if (!button.getText().equals(symbol)) {
                count = 0;
            } else {
                count++;
                if (count == GameSize) {
                    isWin = true;
                    return isWin;
                }
            }
        }
        return isWin;
    }
    private void disableButtons() {
        for (int row = 0; row < GameSize; row++) {
            for (int col = 0; col < GameSize; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }
}

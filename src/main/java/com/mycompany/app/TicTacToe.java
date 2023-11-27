/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; // 50px para o painel no topo

    JFrame frame = new JFrame("Jogo da Velha");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    
    JButton[][] board = new JButton[3][3];
    private JButton[][] winningButtons = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    PlayerName playerName = new PlayerName();
    

    boolean gameOver = false;
    int turnos = 0; //Inicia os turnos em 0, quando chegar em 9 o jogo termina.
    

    TicTacToe() {       
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Jogo da Velha");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
        
        String playerXName = "";
        String playerOName = "";
        // Lógica para tornar a escolha de nomes obrigatória
        while (playerXName.isEmpty() || playerOName.isEmpty()) {
            playerXName = showCustomInputDialog("Jogador 1");
            if (playerXName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, forneça um nome para o Jogador 1.");
                continue;
            }
    
        playerOName = showCustomInputDialog("Jogador 2");
            if (playerOName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, forneça um nome para o Jogador 2.");
            }
        }

        playerName.setPlayerXName(playerXName);
        playerName.setPlayerOName(playerOName);
        
        //Adiciona os botões ao painel.
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                JButton telha = new JButton();
                board[l][c] = telha;
                boardPanel.add(telha);

                telha.setBackground(Color.darkGray);
                telha.setForeground(Color.white);
                telha.setFont(new Font("Arial", Font.BOLD, 120));
                telha.setFocusable(false);

                telha.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton telha = (JButton) e.getSource();
                        if (telha.getText().equals("")) {
                            telha.setText(currentPlayer);
                            turnos++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText("Vez de " + (currentPlayer) + " (" + (currentPlayer.equals(playerX) ? playerName.getPlayerXName() : playerName.getPlayerOName()) + ")");
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        // horizontal
        for (int l = 0; l < 3; l++) {
            if (board[l][0].getText().equals("")) continue;

            if (board[l][0].getText().equals(board[l][1].getText()) &&
                board[l][1].getText().equals(board[l][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[l][i]);
                }
                gameOver = true;
                return;
                
            }
        }

        // vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals("")) continue;

            if (board[0][c].getText().equals(board[1][c].getText()) &&
                board[1][c].getText().equals(board[2][c].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        
        //diagonal
        if (board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) &&
            board[0][0].getText() != ""){
            for(int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        
        //diagonal reversa
        if (board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) &&
            board[0][2].getText() != ""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }
        if (turnos == 9){
            for (int l = 0; l < 3; l++){
                for(int c = 0; c < 3; c++){
                    setVelha(board[l][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton telha) {
    if (!gameOver) {
        textLabel.setText("O vencedor é: " + (currentPlayer.equals(playerX) ? playerName.getPlayerXName() : playerName.getPlayerOName()));

        if (currentPlayer.equals(playerX)) {
            playerName.incrementPlayerXScore();
        } else {
            playerName.incrementPlayerOScore();
        }

        createRestartButton();
        createScoreButton();
        // Marca o jogo como encerrado para evitar múltiplos incrementos de pontuação
        gameOver = true;

        // Armazena os botões vencedores
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (board[l][c].getText().equals(telha.getText())) {
                    winningButtons[l][c] = board[l][c];
                }
            }
        }

        // Atualiza todos os botões vencedores
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (winningButtons[l][c] != null) {
                    winningButtons[l][c].setForeground(Color.green);
                    winningButtons[l][c].setBackground(Color.gray);
                }
            }
        }
    }
}

    
    void setVelha(JButton telha){
        telha.setForeground(Color.orange);
        telha.setBackground(Color.gray);
        textLabel.setText("O jogo deu Velha!");
        createRestartButton();
        createScoreButton();
    }
    
    private void showScoreBoard() {
        JFrame scoreFrame = new JFrame("Placar");
        JTextArea scoreTextArea = new JTextArea(getScoreBoard());
        scoreTextArea.setBackground(Color.DARK_GRAY);
        scoreTextArea.setForeground(Color.WHITE);
        Font monospacedFont = new Font("Monospaced", Font.PLAIN, 12); // Ajuste o tamanho da fonte conforme necessário
        scoreTextArea.setFont(monospacedFont);
        scoreFrame.add(new JScrollPane(scoreTextArea));
        scoreFrame.setSize(300, 200);
        scoreFrame.setLocationRelativeTo(null);
        scoreFrame.setVisible(true);
    }
    
     private String getScoreBoard() {
    return "Placar:\n" +
           playerName.getPlayerXName() + " (" + playerX + "): " + playerName.getPlayerXScore() + " vitórias\n" +
           playerName.getPlayerOName() + " (" + playerO + "): " + playerName.getPlayerOScore() + " vitórias";
}
     
     private void createScoreButton() {
    JButton scoreButton = new JButton("Placar");
    scoreButton.setBackground(Color.ORANGE);
    scoreButton.setForeground(Color.white);
    scoreButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showScoreBoard();
        }
    });
    textPanel.add(scoreButton, BorderLayout.WEST);
}
    
    void createRestartButton() {
        JButton restartButton = new JButton("Restart");
        restartButton.setBackground(Color.ORANGE);
        restartButton.setForeground(Color.white);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
                textPanel.remove(restartButton);
                frame.revalidate();
                frame.repaint();
            }
        });
        textPanel.add(restartButton, BorderLayout.EAST);
    }
    
    void removeScoreButton() {
        for (Component component : textPanel.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals("Placar")) {
                textPanel.remove(component);
                break;
            }
        }
        frame.revalidate();
        frame.repaint();
    }

    void resetGame() {
        // Remove a marcação dos botões vencedores
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                winningButtons[l][c] = null;
            }
        }

        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                board[l][c].setText("");
                board[l][c].setBackground(Color.darkGray);
                board[l][c].setForeground(Color.white);
            }
        }
        gameOver = false;
        turnos = 0;
        currentPlayer = playerX;
        textLabel.setText("Jogo da Velha");
        MenuComponent restartButton = null;
        textPanel.remove(restartButton);
        frame.revalidate();
        frame.repaint();
        removeScoreButton();
    }
    
    private String showCustomInputDialog(String playerName) {
    JDialog dialog = new JDialog();
    dialog.setTitle("Digite o nome do " + playerName);

    JPanel panel = new JPanel();
    
    JLabel label = new JLabel("Nome do " + playerName + ":");
    panel.add(label);
    
    JTextField textField = new JTextField(20);
    panel.add(textField);
    
    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> {
        dialog.dispose();
    });
    panel.add(okButton);
    
    dialog.add(panel);
    
    dialog.setSize(300, 125);
    dialog.setLocationRelativeTo(null);
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setModal(true);
    
    label.setForeground(Color.WHITE);
    panel.setBackground(Color.DARK_GRAY);
    textField.setBackground(Color.LIGHT_GRAY);
    okButton.setBackground(Color.ORANGE);
    okButton.setForeground(Color.WHITE);
    
    dialog.setVisible(true);
    
    return textField.getText();
}


    
}
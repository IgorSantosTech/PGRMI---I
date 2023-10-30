/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.app;

/**
 *
 * @author USUARIO
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {

   public static void main(String[] args) {
        //Menu Frame
        JFrame startFrame = new JFrame("Menu");
        startFrame.setSize(600, 650);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLayout(new BorderLayout());
        startFrame.setLocationRelativeTo(null);

        //Label do titulo
        JLabel textLabel = new JLabel();
        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Jogo da Velha");
        textLabel.setOpaque(true);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.NORTH);
        textPanel.setBackground(Color.DARK_GRAY);

        startFrame.add(textPanel, BorderLayout.CENTER);
        
        //Imagem do Menu
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("E:\\JavaProjects\\App\\resources\\tictactoeicon.png");
        imageLabel.setIcon(imageIcon);
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS)); // Usar BoxLayout para centralizar
        imagePanel.add(Box.createHorizontalGlue()); // Espaço à esquerda para centralizar
        imagePanel.add(imageLabel);
        imagePanel.add(Box.createHorizontalGlue()); // Espaço à direita para centralizar
        imagePanel.setBackground(Color.DARK_GRAY);
        

        textPanel.add(imagePanel, BorderLayout.CENTER);
        

        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.ORANGE);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                TicTacToe ticTacToe = new TicTacToe();
            }
        });

        textPanel.add(startButton, BorderLayout.SOUTH);

        startFrame.setVisible(true);
    }
}

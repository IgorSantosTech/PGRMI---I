/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */
public class PlayerName implements Serializable {
    private static final long serialVersionUID = 1L;

    private String playerXName;
    private String playerOName;
    private int playerXScore;
    private int playerOScore;

     public String getPlayerXName() {
        return playerXName;
    }

    public void setPlayerXName(String playerXName) {
        this.playerXName = playerXName;
    }

    public String getPlayerOName() {
        return playerOName;
    }

    public void setPlayerOName(String playerOName) {
        this.playerOName = playerOName;
    }

    public int getPlayerXScore() {
        return playerXScore;
    }

    public void setPlayerXScore(int playerXScore) {
        this.playerXScore = playerXScore;
    }

    public int getPlayerOScore() {
        return playerOScore;
    }

    public void setPlayerOScore(int playerOScore) {
        this.playerOScore = playerOScore;
    }

    public void incrementPlayerXScore() {
        playerXScore++;
    }

    public void incrementPlayerOScore() {
        playerOScore++;
    }
}


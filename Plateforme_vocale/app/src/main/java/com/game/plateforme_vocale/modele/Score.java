package com.game.plateforme_vocale.modele;

import java.io.Serializable;

public class Score implements Serializable {
    private int score;
    private String joueur;

    /**
     * constructeur d'un score
     * @param score => score du joueur
     * @param joueur => nom du joueur
     */
    public Score(int score, String joueur) {
        this.score = score;
        this.joueur = joueur;
    }

    public int getScore() {
        return score;
    }

    public String getJoueur() {
        return joueur;
    }
}

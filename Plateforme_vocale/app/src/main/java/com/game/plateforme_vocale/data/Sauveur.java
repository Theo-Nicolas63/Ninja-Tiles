package com.game.plateforme_vocale.data;


import com.game.plateforme_vocale.modele.Score;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Gere la serialization d'une collection de score
 */
public abstract class Sauveur {

    /**
     * Sauvegarde une collection de score
     *
     * @param lesScores La collection a sauvegarder
     */
    public abstract void sauvegarderStats(List<Score> lesScores, FileOutputStream file);

    /**
     * Charge une collection de score
     *
     * @return La collection de score
     */
    public abstract List<Score> chargerStats(FileInputStream file);
}


package com.game.plateforme_vocale.data.ser;

import com.game.plateforme_vocale.data.Sauveur;
import com.game.plateforme_vocale.modele.Score;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Permet la serialization d'une collection de score dans un fichier
 */
public class SauveurSer extends Sauveur {

    /**
     * Sauvegarde une collection de score
     *
     * @param historique La collection a sauvegarder
     */
    @Override
    public void sauvegarderStats(List<Score> historique, FileOutputStream file) {
        serialiser(historique, file);
    }

    /**
     * Charge une collection de score
     *
     * @return La collection de score
     */
    @Override
    public List<Score> chargerStats(FileInputStream file) {
        return deserialiser(file);
    }


    /**
     * sérialise la liste de score
     *
     * @param lesScores Collection<score>
     */
    private void serialiser(List<Score> lesScores, FileOutputStream file) {
        try {
            ObjectOutputStream oout = new ObjectOutputStream(file);
            oout.writeObject(lesScores);
            oout.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * désérialise la liste de score
     *
     * @return List<Partie>
     */
    private List<Score> deserialiser(FileInputStream file) {
        List<Score> historique = new ArrayList<>();
        try {
            ObjectInputStream oin = new ObjectInputStream(file);
            historique = (ArrayList<Score>) oin.readObject();
            oin.close();
        } catch (ClassNotFoundException | IOException nfe) {
            nfe.printStackTrace();
        }
        return historique;
    }

}
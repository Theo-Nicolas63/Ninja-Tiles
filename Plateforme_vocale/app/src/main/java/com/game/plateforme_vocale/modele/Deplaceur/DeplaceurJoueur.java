package com.game.plateforme_vocale.modele.Deplaceur;

import com.game.plateforme_vocale.modele.entites.Entite;

public class DeplaceurJoueur {

    private int ratio = 0;

    public int getRatio() {
        return ratio;
    }

    public void addRatio(int add){
        ratio += add;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public void gravite(Entite e){
        e.setPosY(e.getPosY()+ratio);
    }
}

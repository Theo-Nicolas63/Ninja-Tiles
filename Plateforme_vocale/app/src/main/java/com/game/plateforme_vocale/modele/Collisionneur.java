package com.game.plateforme_vocale.modele;

import android.graphics.Rect;

import com.game.plateforme_vocale.modele.entites.Personnage;
import com.game.plateforme_vocale.modele.entites.Plateforme;

import java.util.ArrayList;

public class Collisionneur {
    /**
     * permet de détecter une collision en dessous du joueur (utilisé pour la gravité)
     * @param plateformes => collection de plateformes
     * @param joueur => joueur courant
     * @param speed => vitesse
     * @return true si il y a collision et false si il n'y en a pas
     */
    public boolean isPresentUnder(ArrayList<Plateforme> plateformes, Personnage joueur, int speed){

        Rect r1;
        Rect r2;
        r1 = new Rect((int)joueur.getPosX(), (int)joueur.getPosY()+joueur.getHeight()-20, (int)(joueur.getPosX() + joueur.getWidth())/2, (int)joueur.getPosY()+joueur.getHeight());
        for (Plateforme p : plateformes) {
            r2 = new Rect((int)p.getPosX(), (int)p.getPosY(), (int)p.getPosX() + p.getWidth(), (int)p.getPosY()+p.getHeight());
            if (r1.intersect(r2))
                return true;
        }
        return false;
    }

    /**
     * permet de détecter une collision (utilisé pour les mouvement horizontal du joueur)
     * @param plateformes => collection de plateformes
     * @param joueur => joueur courant
     * @return true si il y a collision et false si il n'y en a pas
     */
    public boolean isPresentForward(ArrayList<Plateforme> plateformes, Personnage joueur){
        Rect r1;
        Rect r2;
        r1 = new Rect((int)joueur.getPosX(), (int)joueur.getPosY(), (int)joueur.getPosX() + joueur.getWidth(), (int)joueur.getPosY()+joueur.getHeight());
        for (Plateforme p : plateformes) {
            r2 = new Rect((int)p.getPosX()+joueur.getWidth(), (int)p.getPosY(), (int)p.getPosX() + p.getWidth(), (int)p.getPosY()+p.getHeight());
            if (r1.intersect(r2))
                return true;
        }
        return false;
    }

    /**
     * permet de détecter une collision
     * @param plateformes => collection de plateformes
     * @param joueur => joueur courant
     * @param speed => vitesse
     * @return true si il y a collision et false si il n'y en a pas
     */
    public boolean isPresentUnderOrForward(ArrayList<Plateforme> plateformes, Personnage joueur, int speed){
        if (isPresentUnder(plateformes, joueur, speed) || isPresentForward(plateformes, joueur))
            return true;
        else return false;
    }

}

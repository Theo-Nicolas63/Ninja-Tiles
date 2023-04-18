package com.game.plateforme_vocale.data;

import com.game.plateforme_vocale.modele.entites.Personnage;
import com.game.plateforme_vocale.modele.entites.Plateforme;

import java.util.ArrayList;

public class Stub {

    public static ArrayList<Plateforme> loadPlateform(){
        ArrayList<Plateforme> lesPlateformes = new ArrayList<>();
        Plateforme r1 = new Plateforme(50, 1000, 230, 35);
        Plateforme r2 = new Plateforme(500, 1000, 230, 35);
        Plateforme r3 = new Plateforme(850, 1000, 230, 35);
        lesPlateformes.add(r1);
        lesPlateformes.add(r2);
        lesPlateformes.add(r3);
        return lesPlateformes;
    }

    public static Personnage loadJoueur(){
        return new Personnage(70, 800, 150, 150);
    }
}


package com.game.plateforme_vocale.modele.Deplaceur;

import com.game.plateforme_vocale.modele.entites.Plateforme;
import com.game.plateforme_vocale.modele.Parametre;

import java.util.Random;

public class DeplaceurPlateforme {
    /**
     * permet de déplacer une plateforme
     * @param p plateforme à déplacer
     * @param z déplacement
     */
    public void avancer(Plateforme p, float z) {

            if (p.getPosX() <= -250){
                p.setPosX(1200);
                p.setPosY(getRandomY());
            }
            else if (p.getPosX() > 1200){
                p.setPosX(-200);
            }
            else if (z < 0){
                if (z<-6)
                    p.setPosX(p.getPosX() + Parametre.forward*-6);
                else p.setPosX(p.getPosX() + Parametre.forward*z);
            }
            else
                p.setPosX(p.getPosX() + Parametre.backward*z);
        }


    /**
     * permet de générer une hauteur Y aléatoire entre 1000 et 1500
     * @return un entier aléatoire entre et 1000 et 1500
     */
        private int getRandomY(){
            int max = 1500;
            int min = 1000;
            Random rd = new Random();
            return rd.nextInt((max - min) +1 ) + min;
        }
}

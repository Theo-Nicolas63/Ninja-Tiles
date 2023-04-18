package com.game.plateforme_vocale.modele.Boucleur;

public class BoucleurSimple extends Boucleur {
    /**
     * Boucle de jeu
     */
    @Override
    public void run() {
        while (running) {
            setChanged();
            notifyObservers();

            try {
                Thread.sleep(1000/30);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

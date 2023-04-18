package com.game.plateforme_vocale.modele.Boucleur;

import java.util.Observable;

public abstract class Boucleur extends Observable implements Runnable {

    protected boolean running = false;

    public void setRunning(boolean running){
        this.running = running;
    }

}

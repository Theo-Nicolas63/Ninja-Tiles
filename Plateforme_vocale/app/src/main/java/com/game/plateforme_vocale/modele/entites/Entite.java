package com.game.plateforme_vocale.modele.entites;

import android.graphics.Paint;

public class Entite {
    private float posX;
    private float posY;
    private int width;
    private int height;
    private Paint paint;


    public Entite(int x, int y, int width, int height){
        this.height = height;
        this.posX = x;
        this.posY = y;
        this.width = width;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

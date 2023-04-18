package com.game.plateforme_vocale.view.codeBehind;

import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.game.plateforme_vocale.R;
import com.game.plateforme_vocale.modele.Manager;
import com.game.plateforme_vocale.modele.entites.Plateforme;
import com.game.plateforme_vocale.modele.Parametre;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameActivity extends AppCompatActivity implements Observer {

    private Manager monManager = Manager.getInstance();
    private ArrayList<ImageView> plateformes = new ArrayList<>();
    private ImageView joueur;
    private View layout;
    private Point point = new Point();
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private float[] zValeurs = new float[3];;
    private boolean canJump;
    private TextView TextViewscore;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        layout = findViewById(R.id.Linear);
        monManager.getBoucleur().addObserver(this);
        plateformes.add(findViewById(R.id.plateforme1));
        plateformes.add(findViewById(R.id.plateforme2));
        plateformes.add(findViewById(R.id.plateforme3));
        joueur = findViewById(R.id.perso);
        TextViewscore = findViewById(R.id.score);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ninja_theme);
        mediaPlayer.setLooping(true);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        initPosition();
        getWindowManager().getDefaultDisplay().getSize(point);
        joueur.setTranslationX(monManager.getJoueurCourant().getPosX());
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (canJump){
                    int i = 0;
                    monManager.getMonDeplaceurJoueur().setRatio(Parametre.jump);
                    monManager.getMonDeplaceurJoueur().gravite(monManager.getJoueurCourant());
                    joueur.setTranslationY(monManager.getJoueurCourant().getPosY());
                    canJump = false;
                }

                return false;
            }
        });
        sensorEventListener =new SensorEventListener() {
            @Override
            public void onSensorChanged (SensorEvent sensorEvent) {
                zValeurs = sensorEvent.values.clone();

                }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    /**
     * Boucle de jeu
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

        runOnUiThread(()->{
            if (!monManager.isPresentUnder()){
                monManager.getMonDeplaceurJoueur().addRatio(Parametre.gravity);
                monManager.getMonDeplaceurJoueur().gravite(monManager.getJoueurCourant());
                joueur.setTranslationY(monManager.getJoueurCourant().getPosY());
                if (monManager.getJoueurCourant().getPosY() >= point.y){
                    monManager.pausePartie();
                    finDePartie();
                }

            }
            else {
                monManager.getMonDeplaceurJoueur().setRatio(0);
                canJump = true;
            }

            int i = 0;
            if (!monManager.isPresentUnderOrForward()){
                for (Plateforme p : monManager.getLesPlateformes()){
                    monManager.avancer(p, zValeurs[0]*2);
                    plateformes.get(i).setTranslationX(p.getPosX());
                    plateformes.get(i).setTranslationY(p.getPosY());
                    i = i+1;
                }
            }
            this.TextViewscore.setText(String.valueOf(monManager.getCurrentScore()));
    });
    }

    /**
     * permet d'initialisé la position de tous les éléments de la partie
     */
    public void initPosition(){
        int i = 0;
        for (Plateforme p : monManager.getLesPlateformes()){
            plateformes.get(i).setTranslationX(p.getPosX());
            plateformes.get(i).setTranslationY(p.getPosY());
            i = i+1;
        }
        joueur.setTranslationY(monManager.getJoueurCourant().getPosY());
        joueur.setTranslationX(monManager.getJoueurCourant().getPosX());
    }

    /**
     * fin de partie => appel la vue de fin de partie
     */
    public void finDePartie(){
        Intent intent = new Intent(this, FinDePartieActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        sensorManager.registerListener(sensorEventListener, sensor,  SensorManager.SENSOR_DELAY_NORMAL);
        monManager.lancerPartie();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        monManager.pausePartie();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        monManager.getBoucleur().deleteObserver(this);
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

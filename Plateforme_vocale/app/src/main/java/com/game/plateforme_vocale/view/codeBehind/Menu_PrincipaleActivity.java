package com.game.plateforme_vocale.view.codeBehind;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.game.plateforme_vocale.R;
import com.game.plateforme_vocale.modele.Manager;

import java.io.FileNotFoundException;

public class Menu_PrincipaleActivity extends AppCompatActivity {
    private Button lancementJeu;
    private Button score;
    private Animation scaleUp, scaleDown;
    private Manager monManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principale);
        lancementJeu = (Button) findViewById(R.id.lancementJeu);
        score = (Button) findViewById(R.id.lancement_score);
        monManager = Manager.getInstance();
        try {
            monManager.chargerDonnees(openFileInput(Manager.NAME_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        lancementJeu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    lancementJeu.startAnimation(scaleUp);
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    lancementJeu.startAnimation(scaleDown);
                }
                openJeu();
                return true;
            }
        });

        score = (Button) findViewById(R.id.lancement_score);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScore();
            }
        });
        score.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    score.startAnimation(scaleUp);
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    score.startAnimation(scaleDown);
                }
                openScore();
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d("TOTO", "on passe dans onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void openJeu() {
        Intent intent = new Intent(Menu_PrincipaleActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void openScore(){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
        finish();
    }



}

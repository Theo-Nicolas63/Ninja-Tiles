package com.game.plateforme_vocale.view.codeBehind;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.game.plateforme_vocale.R;
import com.game.plateforme_vocale.modele.Manager;
import com.game.plateforme_vocale.modele.Score;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    TextView tv_score;
    Button buttonMenu;
    int score;
    private Manager monManager;
    private RecyclerView laListView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        monManager = Manager.getInstance();
        tv_score = (TextView) findViewById(R.id.score);
        buttonMenu = (Button) findViewById(R.id.buttonMenu);
        laListView = findViewById(R.id.laListView);
        laListView.setLayoutManager(new LinearLayoutManager(this));
        laListView.setAdapter(new MonAdaptateur((ArrayList<Score>) monManager.getLesScores()));
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        score = preferences.getInt("lastScore", 0);
        buttonMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                monManager.restart();
                openMenu();
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FinDePartieActivity.class);
        startActivity(intent);
        finish();
    }

    public void openMenu(){
        Intent intent = new Intent(this, Menu_PrincipaleActivity.class);
        startActivity(intent);
        finish();
    }

}


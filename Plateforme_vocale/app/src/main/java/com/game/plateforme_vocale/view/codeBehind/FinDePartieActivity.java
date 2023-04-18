package com.game.plateforme_vocale.view.codeBehind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.game.plateforme_vocale.R;
import com.game.plateforme_vocale.modele.Manager;

import java.io.FileNotFoundException;


public class FinDePartieActivity<Validation> extends AppCompatActivity {
    private Button tapHere, btSubmit, defeat;
    private TextView gameScoreTextView;
    private EditText pseudo;
    private static final String KEY_COUNTER = "COUNTER";
    private LinearLayout form;
    private Manager monManager;

    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin);
        init();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        int defaultCounter = 0;
        count = settings.getInt(KEY_COUNTER, defaultCounter);
        monManager = Manager.getInstance();
        gameScoreTextView.setText("Votre Score: " + String.valueOf(monManager.getCurrentScore()));


        //validation formulaire
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pseudo.getText().toString().equals("")){
                    monManager.addScore(pseudo.getText().toString());
                    try {
                        monManager.sauvegarderDonnees(openFileOutput(Manager.NAME_FILE, MODE_PRIVATE));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("score", count);
                    editor.apply();
                    openScore();
                }
                else {
                    Toast.makeText(FinDePartieActivity.this, R.string.erreur_pseudo_non_rentre, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void init(){
        gameScoreTextView = findViewById(R.id.afficheScore);
        pseudo = findViewById(R.id.pseudo);
        btSubmit = findViewById(R.id.bt_submit);
        form = findViewById(R.id.form);
    }


    @Override
    protected void onDestroy() {
        try {
            monManager.sauvegarderDonnees(openFileOutput(monManager.NAME_FILE, MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY_COUNTER, count);
        editor.commit();
    }

    public void openScore(){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
        finish();
    }

}


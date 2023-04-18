package com.game.plateforme_vocale.view.codeBehind;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.game.plateforme_vocale.R;
import com.game.plateforme_vocale.modele.Score;

import java.util.ArrayList;
import java.util.List;

public class MonAdaptateur extends RecyclerView.Adapter<MonViewHolder> {

    private final List<Score> lesScores;

    public MonAdaptateur(ArrayList<Score> lesScores) {
        this.lesScores = lesScores;
    }

    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout lelayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cellule_score, parent, false);
        return new MonViewHolder(lelayout);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        holder.getMonTextView().setText("  Joueur : " + lesScores.get(position).getJoueur() + " Score : "+ lesScores.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return lesScores.size();
    }
}

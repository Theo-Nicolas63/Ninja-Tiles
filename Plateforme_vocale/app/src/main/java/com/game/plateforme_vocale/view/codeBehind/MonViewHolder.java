package com.game.plateforme_vocale.view.codeBehind;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.game.plateforme_vocale.R;

public class MonViewHolder extends RecyclerView.ViewHolder {

    private final TextView monTextView;

    public MonViewHolder(@NonNull View itemView) {
        super(itemView);
        monTextView = itemView.findViewById(R.id.leTextView);
    }

    public TextView getMonTextView() {
        return monTextView;
    }
}

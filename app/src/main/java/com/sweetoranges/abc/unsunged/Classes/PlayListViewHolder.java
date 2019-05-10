package com.sweetoranges.abc.unsunged.Classes;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.sweetoranges.abc.unsunged.R;

public class PlayListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

public PlayListViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        }
        }
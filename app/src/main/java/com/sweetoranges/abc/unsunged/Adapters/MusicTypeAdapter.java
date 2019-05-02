package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.ViewHolder> {
    public Context context;
    private ViewHolder viewHolder;
    private String[] musictype;
    List<String[]> dataList = new ArrayList<String[]>();
    public MusicTypeAdapter(FragmentActivity activity,String[] musictype) {this.context = activity;this.musictype = musictype; }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView listText;
        private MediaPlayer mMediaPlayers;
        int x=-1;
        private ViewHolder(final View v){
            super(v);
            listText=(TextView)v.findViewById(R.id.listText);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.listText.setText(musictype[position]);
        holder.listText.setOnClickListener(view -> {
        });
    }


    @Override public MusicTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.play, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return musictype.length;}
}

//
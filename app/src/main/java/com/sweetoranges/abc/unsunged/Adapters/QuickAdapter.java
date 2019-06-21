package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.utils.MyBounceInterpolator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickAdapter extends RecyclerView.Adapter<QuickAdapter.ViewHolder> {
    public Context context;
    private ViewHolder viewHolder;
boolean play =false;
List<String>songs=new ArrayList<>();
    public QuickAdapter(Context context, int[] id, List<String> songs) {this.context = context; this.songs=songs; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView listType;
        FloatingActionButton playsearch;
        private ImageView likesearch;
        ImageView background;
        public MediaPlayer mediaPlayer = new MediaPlayer();
        //        private View Quickback;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.quickname);
            playsearch=(FloatingActionButton)v.findViewById(R.id.playsearch);
            likesearch=(ImageView)v.findViewById(R.id.likesearch);
            background=(ImageView)v.findViewById(R.id.background);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.listType.setText("Better Not");
        holder.likesearch.setOnClickListener(v -> {
            bounceButton(v);
            holder.likesearch.setBackgroundResource(R.drawable.heart);
        });
        holder.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        holder.mediaPlayer.setOnPreparedListener(mp -> { });
        holder.mediaPlayer.setOnCompletionListener(mp -> {});
        try {
            holder.mediaPlayer.setDataSource(songs.get(position));
            holder.mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.playsearch.setOnClickListener(v->{
            if(holder.mediaPlayer.isPlaying()){
                holder.mediaPlayer.pause();
                holder.background.setVisibility(View.GONE);
                holder.playsearch.setImageResource(R.drawable.ic_play_arrow);
            }
            else{
                holder.mediaPlayer.start();
                holder.background.setVisibility(View.VISIBLE);
                holder.playsearch.setImageResource(R.drawable.ic_pause_black);
                Glide.with(context).load(R.drawable.musicv).into(new GlideDrawableImageViewTarget(holder.background));
            }
            });
    }

    private void bounceButton(View view) {
        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        view.startAnimation(myAnim);
    }


    @Override public QuickAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.quicksearch, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }

    @Override public int getItemCount(){ return 3;  }

}

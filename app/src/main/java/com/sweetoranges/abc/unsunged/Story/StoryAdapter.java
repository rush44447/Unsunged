package com.sweetoranges.abc.unsunged.Story;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sweetoranges.abc.unsunged.BingeFragment.BingeFragment;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.Model.Story;
import com.sweetoranges.abc.unsunged.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private  Context context;
    private MediaPlayer mMediaPlayer;
    private List<Story> followings;
    public StoryAdapter(FragmentActivity activity, List<Story> followings) {
        this.followings=followings;
        this.context=activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView Album;
        RelativeLayout Listen;
        CardView mystory;
        private ViewHolder(final View v){
            super(v);
            Album=(AppCompatImageView) v.findViewById(R.id.firstSong);
            Listen=(RelativeLayout)v.findViewById(R.id.storyspace);
            mystory=(CardView)v.findViewById(R.id.mystory);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        if(position<=0) {holder.Listen.setVisibility(View.VISIBLE);holder.mystory.setVisibility(View.VISIBLE);} else {holder.Listen.setVisibility(View.GONE);holder.mystory.setVisibility(View.GONE);}
        holder.Album.setOnClickListener(v -> {
        });

    }

        private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        } else {
            mMediaPlayer.start();
        }
    }

    @Override public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.story, parent, false);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(mp -> { });
        mMediaPlayer.setOnCompletionListener(mp -> {});
        return new ViewHolder(v); }
    @Override public int getItemCount(){ return 4;  }


}
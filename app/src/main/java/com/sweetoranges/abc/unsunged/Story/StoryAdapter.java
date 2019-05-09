package com.sweetoranges.abc.unsunged.Story;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.Model.Story;
import com.sweetoranges.abc.unsunged.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    //  firstImage.setColorFilter(Color.rgb(123, 123, 123), android.graphics.PorterDuff.Mode.MULTIPLY);
    //imageView.clearColorFilter();
    // return image.getColorFilter() != null;
    private  Context context;
    private ViewHolder viewHolder;
    private MediaPlayer mMediaPlayer;
    private List<StreamingRequest> followings;
    public StoryAdapter(FragmentActivity activity, List<StreamingRequest> followings) {
        this.followings=followings;
        this.context=activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView Album;
        RelativeLayout Listen;
        private ViewHolder(final View v){
            super(v);
            Album=(AppCompatImageView) v.findViewById(R.id.firststory);
            Listen=(RelativeLayout)v.findViewById(R.id.storyspace);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        if(position<=0) holder.Listen.setVisibility(View.VISIBLE); else holder.Listen.setVisibility(View.GONE);
        holder.Album.setOnClickListener(v -> {
           // togglePlayPause();
        });
       // holder.categorytext.setText(followings.get(0).getTitle());
//        holder.Titletext.setText(followings.get(position).getTitle());
//        holder.dateCreated.setText(followings.get(position).getDateCreated());
//        Picasso.with(context)
//                .load(followings.get(position).getCover())
//                .placeholder(R.drawable.imgview)
//                .into(viewHolder.storyImage);
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
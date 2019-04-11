package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInterface;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Response;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    public Context context;
    private ViewHolder viewHolder;
    List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};

    private String types;
    public PlaylistAdapter(FragmentActivity activity,String Types) { this.context = activity;this.types=Types; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView listType;
        private MediaPlayer mMediaPlayers;
        private ImageView mPlayerControls;
        int x=-1;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.listType);
            mPlayerControls = (ImageView)v.findViewById(R.id.clickplay);
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
           try{ callMusicDetail(apiService);}catch (Exception e){ e.printStackTrace();}
            mPlayerControls.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) { togglePlayPause();x = getAdapterPosition(); }});
            mMediaPlayers = new MediaPlayer();
            mMediaPlayers.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayers.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override public void onPrepared(MediaPlayer mp) { togglePlayPause(); }});
            mMediaPlayers.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override public void onCompletion(MediaPlayer mp) {
                    mPlayerControls.setImageResource(R.drawable.ic_play_arrow_black_24dp); }});

        }
        private void callMusicDetail(ApiInterface apiService) {
             String playlist[] = new String[]{"idshnmkl","idshnmkl","idshnmkl","idshnmkl"};
            Call<StreamingRequest> call = apiService.getStreaming(playlist[getAdapterPosition()]);//this is added to baseurl and data is  retrieved
            call.enqueue(new retrofit2.Callback<StreamingRequest>() {
                @Override
                public void onResponse(Call<StreamingRequest> call, Response<StreamingRequest> response) {
                    // handleResponse(response);
                    try {
                        mMediaPlayers.setDataSource(response.body().getMp3Url());//here mp3 file is loaded using retrieved url and fed into mMediaPlayer
                        mMediaPlayers.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<StreamingRequest> call, Throwable t) {
                    System.out.println("FAILED " + t.toString());
                }
            });
        }


        private void togglePlayPause() {
            if (mMediaPlayers.isPlaying()) {
                mMediaPlayers.pause();
                mPlayerControls.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            } else {
                mMediaPlayers.start();
                mPlayerControls.setImageResource(R.drawable.ic_pause_black_24dp);
            }
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.listType.setText(name[position]);
    }


    @Override public PlaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.playlist, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }
}
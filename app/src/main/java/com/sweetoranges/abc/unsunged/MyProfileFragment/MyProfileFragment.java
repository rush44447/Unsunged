package com.sweetoranges.abc.unsunged.MyProfileFragment;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInterface;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;

import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.R;

import java.io.IOException;

public class MyProfileFragment extends Fragment {
    ImageView circularImageView;
    RecyclerView playlistRecycle;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);
        circularImageView = (ImageView)view.findViewById(R.id.circleView);
        playlistRecycle = (RecyclerView)view.findViewById(R.id.playlistRecycle);
        circularImageView.setImageBitmap(ImageConverter.getRoundedCornerBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.imgview), 100));

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        });

        mPlayerControl = (ImageView)view.findViewById(R.id.player_control);
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        callMusicDetail();
        return view;
    }
    private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mMediaPlayer.start();
            mPlayerControl.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }
//

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        if (mMediaPlayer != null) {
//            if (mMediaPlayer.isPlaying()) {
//                mMediaPlayer.stop();
//            }
//            mMediaPlayer.release();
//            mMediaPlayer = null;
//        }
//    }
    private void callMusicDetail() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StreamingRequest> call = apiService.getStreaming("idshnmkl");
        call.enqueue(new retrofit2.Callback<StreamingRequest>() {
            @Override
            public void onResponse(Call<StreamingRequest> call, Response<StreamingRequest> response) {
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<StreamingRequest> call, Throwable t) {
                System.out.println("FAILED " + t.toString());
            }
        });
    }

    private void handleResponse(Response<StreamingRequest> response) {
        try {
            mMediaPlayer.setDataSource(response.body().getMp3Url());
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

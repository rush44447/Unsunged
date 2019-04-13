package com.sweetoranges.abc.unsunged;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sweetoranges.abc.unsunged.ChallengeFragment.ChallengeFragment;
import com.sweetoranges.abc.unsunged.BingeFragment.BingeFragment;
import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInterface;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.MyProfileFragment.MyProfileFragment;
import com.sweetoranges.abc.unsunged.SearchFragment.SearchFragment;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        context = getApplicationContext();
        mPlayerControl = (ImageView) findViewById(R.id.player_control);
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //  Toast.makeText(context, "prepared", Toast.LENGTH_SHORT).show();
                //  togglePlayPause();
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        });


        callMusicDetail();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.binge:
                    loadFragment(new BingeFragment());
                    return true;
                case R.id.search:
                    loadFragment(new SearchFragment());
                    return true;
                case R.id.type:
                    loadFragment(new ChallengeFragment());
                    return true;
                case R.id.profile:
                    loadFragment(new MyProfileFragment());
                    return true;
            }
            return false; }};

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
    private void callMusicDetail() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);//connection is built
        Call<StreamingRequest> call = apiService.getStreaming("idshnmkl");//this is added to baseurl and data is  retrieved
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

    @Override protected void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    private void handleResponse(Response<StreamingRequest> response) {
        try { mMediaPlayer.setDataSource(response.body().getMp3Url());//here mp3 file is loaded using retrieved url and fed into mMediaPlayer
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override public void onBackPressed() { super.onBackPressed(); }
}

//@Override
//public void prepare() {
//  try {
//    mediaPlayer = new MediaPlayer();
//    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//    mediaPlayer.setOnPreparedListener(CustomMediaPlayerAssertFolder.this);
//    mediaPlayer.setOnCompletionListener(CustomMediaPlayerAssertFolder.this);
//    mediaPlayer.setOnBufferingUpdateListener(CustomMediaPlayerAssertFolder.this);
//    mediaPlayer.setScreenOnWhilePlaying(true);
//    mediaPlayer.setOnSeekCompleteListener(CustomMediaPlayerAssertFolder.this);
//    mediaPlayer.setOnErrorListener(CustomMediaPlayerAssertFolder.this);
//    mediaPlayer.setOnInfoListener(CustomMediaPlayerAssertFolder.this);
//    mediaPlayer.setOnVideoSizeChangedListener(CustomMediaPlayerAssertFolder.this);
//    AssetFileDescriptor assetFileDescriptor = (AssetFileDescriptor) jzDataSource.getCurrentUrl();
//    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
//    mediaPlayer.prepareAsync();
//  } catch (Exception e) {
//    e.printStackTrace();
//  }
//}

package com.sweetoranges.abc.unsunged;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
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
    private ImageView mPlayerControl,Previous,Next;
    float x1,y1;
    float x2,y2;
    Context context;
    RelativeLayout Controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        context = getApplicationContext();
        loadFragment(new BingeFragment());
        mPlayerControl = (ImageView) findViewById(R.id.player_control);
        Previous = (ImageView) findViewById(R.id.previous);
        Next = (ImageView) findViewById(R.id.next);
        Controller=(RelativeLayout) findViewById(R.id.smallcontroller);

        LinearLayout bottomSheet= (LinearLayout) findViewById(R.id.bottom_sheet);
        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setHideable(false);
      //  bottomSheetBehavior.setPeekHeight(1000);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

//// set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
//
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();            // start playing
            }
        });
        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override public void onPrepared(MediaPlayer mp) { }});

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
                default: loadFragment(new BingeFragment());
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
    @Override public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);}

}

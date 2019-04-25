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
    private ImageView mPlayerControl,Previous,Next;
    float x1,y1;
    float x2,y2;
    Context context;
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

        findViewById(R.id.linear).setOnTouchListener(new View.OnTouchListener()
        {int prevX,prevY;
            @Override
            public boolean onTouch(final View v,final MotionEvent event)
            {
                final FrameLayout.LayoutParams par=(FrameLayout.LayoutParams)v.getLayoutParams();
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_MOVE:
                    {
                        par.topMargin+=(int)event.getRawY()-prevY;
                        prevY=(int)event.getRawY();
                        v.setLayoutParams(par);
                        return true;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        par.topMargin+=(int)event.getRawY()-prevY;
                        v.setLayoutParams(par);
                        return true;
                    }
                }
                return false;
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
    @Override public void onBackPressed() { super.onBackPressed(); }

    public boolean onTouchEvent(MotionEvent touchevent)
    { switch (touchevent.getAction())
        { case MotionEvent.ACTION_UP:
            { x2 = touchevent.getX();
              y2 = touchevent.getY();
                if (y1 < y2+30.00)
                { Intent i = new Intent(MainActivity.this,Dashboard.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_from_upwards, R.anim.slide_down);}
                break; }
                default:
                    Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }return false; }

}

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
import android.widget.TextView;
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
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public static ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl,Previous,Next,mPlayerControlBig;
    Context context;
    RelativeLayout Controller;
    View Hider;
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
        mPlayerControlBig = (ImageView) findViewById(R.id.player_control_p);

        Controller=(RelativeLayout) findViewById(R.id.smallcontroller);
        TextView tv = (TextView) this.findViewById(R.id.nameOfSong);
        Hider=(View)findViewById(R.id.hiderView);
        tv.setSelected(true);
        LinearLayout bottomSheet= (LinearLayout) findViewById(R.id.bottom_sheet);
        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setPeekHeight(100);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

//// set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            int Sheetstate;
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
               Sheetstate = getStateAsString(newState);
               if(Sheetstate==0){//collapsed

               }
                if(Sheetstate==2){//expanded
                    if (mMediaPlayer.isPlaying()) {
                        mPlayerControlBig.setImageResource(R.drawable.ic_pause_black_24dp);
                    } else {
                        mPlayerControlBig.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Hider.setAlpha(slideOffset);
//               if(Sheetstate==4){Hider.setAlpha(slideOffset);}
//                if(Sheetstate==1){Hider.setAlpha(Float.parseFloat("1")-slideOffset);}
            }
        });
//
        findViewById(R.id.player_control).setOnClickListener(smallPlay);
        findViewById(R.id.previous).setOnClickListener(smallPrevious);
        findViewById(R.id.next).setOnClickListener(smallNext);

        findViewById(R.id.player_control_p).setOnClickListener(bigPlay);
        findViewById(R.id.previous_p).setOnClickListener(bigPrevious);
        findViewById(R.id.next_p).setOnClickListener(bigNext);

        findViewById(R.id.ToVideo).setOnClickListener(startVideo);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(mp -> { });

        mMediaPlayer.setOnCompletionListener(mp -> mPlayerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp));
        callMusicDetail();
    }

    final View.OnClickListener smallPlay = v -> { togglePlayPause(); };
    final View.OnClickListener smallPrevious = v -> { togglePlayPause(); };
    final View.OnClickListener smallNext = v -> { togglePlayPause(); };

    final View.OnClickListener bigPlay = v -> { togglePlayPause(); };
    final View.OnClickListener bigPrevious = v -> { togglePlayPause(); };
    final View.OnClickListener bigNext = v -> { togglePlayPause(); };

    final View.OnClickListener startVideo = v -> {
        Toast.makeText(context, "start video", Toast.LENGTH_SHORT).show();
    };
    public static int getStateAsString(int newState) {
        switch (newState) {
            case BottomSheetBehavior.STATE_COLLAPSED:
                return 0;
            case BottomSheetBehavior.STATE_DRAGGING:
                return 1;
            case BottomSheetBehavior.STATE_EXPANDED:
                return 2;
            case BottomSheetBehavior.STATE_HIDDEN:
                return 3;
            case BottomSheetBehavior.STATE_SETTLING:
                return 4;
        }
        return 5;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
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
                return false; };

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
    private void callMusicDetail() {//connection is built
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

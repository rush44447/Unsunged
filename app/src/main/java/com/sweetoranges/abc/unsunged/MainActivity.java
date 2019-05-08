package com.sweetoranges.abc.unsunged;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
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
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public static ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    public MediaPlayer mediaPlayer = new MediaPlayer();

    private ImageView mPlayerControl,mPlayerControlBig;
    private TextView tv,totaltime,currenttime;
    private BottomNavigationView navigation;
    private AppCompatSeekBar Seek;
    Context context;
    public Handler myHandler = new Handler();
    RelativeLayout Controller;
    public View Hider;
    public double startTime = 0;
    public double finalTime = 0;
   // public static int oneTimeOnly = 0;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        LinearLayout bottomSheet= (LinearLayout) findViewById(R.id.bottom_sheet);
        RelativeLayout Controller=(RelativeLayout) findViewById(R.id.smallcontroller);
        ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmerAnimation();

        mPlayerControl = (ImageView) findViewById(R.id.player_control);
        mPlayerControlBig = (ImageView) findViewById(R.id.player_control_p);
        Seek=(AppCompatSeekBar)findViewById(R.id.seek);
        tv = (TextView) findViewById(R.id.nameOfSong);
        currenttime = (TextView) findViewById(R.id.currenttime);
        totaltime = (TextView) findViewById(R.id.totaltime);

        Hider=(View)findViewById(R.id.hiderView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        context = getApplicationContext();
        tv.setSelected(true);
        Seek.setClickable(true);
        loadFragment(new BingeFragment());
        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setPeekHeight(78);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
                    if(newState==BottomSheetBehavior.STATE_COLLAPSED){Hider.setAlpha(0);}
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                    case BottomSheetBehavior.STATE_EXPANDED:
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                    case BottomSheetBehavior.STATE_SETTLING: }
            }
            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Hider.setAlpha(slideOffset);
            }
        });

        findViewById(R.id.player_control).setOnClickListener(smallPlay);
        findViewById(R.id.previous).setOnClickListener(smallPrevious);
        findViewById(R.id.next).setOnClickListener(smallNext);
        findViewById(R.id.player_control_p).setOnClickListener(bigPlay);
        findViewById(R.id.previous_p).setOnClickListener(bigPrevious);
        findViewById(R.id.next_p).setOnClickListener(bigNext);
        findViewById(R.id.ToVideo).setOnClickListener(startVideo);

        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        Toast.makeText(context, String.valueOf((long)finalTime), Toast.LENGTH_SHORT).show();
        //mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(mp -> { });
        mediaPlayer.setOnCompletionListener(mp -> {});
         Seek.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime,100);
//        if (oneTimeOnly == 0) {
//            Seek.setMax((int) finalTime);
//            oneTimeOnly = 1; }
        callMusicDetail();
         totaltime.setText(String.format("%d : %d",
                 TimeUnit.MILLISECONDS.toMinutes((long) finalTime), TimeUnit.MILLISECONDS.toSeconds((long) finalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));
        currenttime.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

    }
    private void togglePlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mPlayerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            mPlayerControlBig.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mediaPlayer.start();
            mPlayerControl.setImageResource(R.drawable.ic_pause_black_24dp);
            mPlayerControlBig.setImageResource(R.drawable.ic_pause_black_24dp);

        }
    }

    final View.OnClickListener smallPlay = v -> { togglePlayPause(); };

    final View.OnClickListener smallPrevious = v -> {   int temp = (int)startTime;
        if((temp-5000)>0){
            startTime = startTime - 5000;
            mediaPlayer.seekTo((int) startTime);
        }else{mediaPlayer.reset(); }};

    final View.OnClickListener smallNext = v -> {int temp = (int)startTime;
        if((temp+5000)<=finalTime){
            startTime = startTime + 5000;
            mediaPlayer.seekTo((int) startTime);
        }else{ mediaPlayer.reset(); } };

    final View.OnClickListener bigPlay = v -> { togglePlayPause(); };

    final View.OnClickListener bigPrevious = v -> {   int temp = (int)startTime;
        if((temp-5000)>0){
            startTime = startTime - 5000;
            mediaPlayer.seekTo((int) startTime);
        }else{mediaPlayer.reset(); }};

    final View.OnClickListener bigNext = v -> {int temp = (int)startTime;
        if((temp+5000)<=finalTime){
            startTime = startTime + 5000;
            mediaPlayer.seekTo((int) startTime);
        }else{ mediaPlayer.reset(); } };

    final View.OnClickListener startVideo = v -> {
        Toast.makeText(context, "start video", Toast.LENGTH_SHORT).show();
    };

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
                return false;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private Runnable UpdateSongTime = new Runnable() {
        @SuppressLint("DefaultLocale")
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            currenttime.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
            Seek.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

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
    private void handleResponse(Response<StreamingRequest> response) {
        try { mediaPlayer.setDataSource(response.body().getMp3Url());//here mp3 file is loaded using retrieved url and fed into mMediaPlayer
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);}
    @Override protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}

package com.sweetoranges.abc.unsunged;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.sweetoranges.abc.unsunged.Activities.LoginActivity;
import com.sweetoranges.abc.unsunged.ActivityFragment.ActivityFragment;
import com.sweetoranges.abc.unsunged.ChallengeFragment.ChallengeFragment;
import com.sweetoranges.abc.unsunged.BingeFragment.BingeFragment;
import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInterface;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.MyProfileFragment.MyProfileFragment;
import com.sweetoranges.abc.unsunged.SearchFragment.SearchFragment;
import com.sweetoranges.abc.unsunged.utils.MyBounceInterpolator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import info.abdolahi.CircularMusicProgressBar;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public static ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    public MediaPlayer mediaPlayer = new MediaPlayer();
    private CoordinatorLayout coordinatorLayout;

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
    public String UserName;
    LinearLayout bottomSheet;
    CircularMusicProgressBar musciProgresss;
    TextView artist;
    private AppCompatImageButton likeButton;
    View viewx;
    List<String>songs=new ArrayList<>();
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String name = intent.getStringExtra("number");
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomSheet= (LinearLayout) findViewById(R.id.bottom_sheet);
        RelativeLayout Controller=(RelativeLayout) findViewById(R.id.smallcontroller);
        musciProgresss=(CircularMusicProgressBar)findViewById(R.id.album_art);
        viewx=(View)findViewById(R.id.Views);
            ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
            container.startShimmerAnimation();

         try{ ObjectAnimator colorFade = ObjectAnimator.ofObject(viewx, "backgroundColor", new ArgbEvaluator(), Color.argb(255,255,255,255), 0xff000000);
            colorFade.setDuration(3000);
            colorFade.start();
        }catch (Exception c){}
        mPlayerControl = (ImageView) findViewById(R.id.player_control);
        mPlayerControlBig = (ImageView) findViewById(R.id.player_control_p);
        Seek=(AppCompatSeekBar)findViewById(R.id.seek);
        tv = (TextView) findViewById(R.id.nameOfSong);
        currenttime = (TextView) findViewById(R.id.currenttime);
        totaltime = (TextView) findViewById(R.id.totaltime);
        Hider=(View)findViewById(R.id.hiderView);
        likeButton=(AppCompatImageButton)findViewById(R.id.likeButton);
        artist=(TextView)findViewById(R.id.artist);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        context = getApplicationContext();
        tv.setSelected(true);
        Seek.setClickable(true);
        loadFragment(new BingeFragment());
        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setPeekHeight(74);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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

//                    transitionBottomSheetBackgroundColor(slideOffset);
//                    animateBottomSheetArrows(slideOffset);


//                AdditiveAnimator.animate(view).setDuration(1000)
//                        .x(touch.getX())
//                        .y(touch.getY())
//                        .start();
//                TranslateAnimation anim = new TranslateAnimation(0, amountToMoveRight, 0, amountToMoveDown);
//                anim.setDuration(1000);
//
//                anim.setAnimationListener(new TranslateAnimation.AnimationListener() {
//
//                    @Override
//                    public void onAnimationStart(Animation animation) { }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) { }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation)
//                    {
//                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
//                        params.topMargin += amountToMoveDown;
//                        params.leftMargin += amountToMoveRight;
//                        view.setLayoutParams(params);
//                    }
//                });
            }
        });

        likeButton.setOnClickListener(v -> {
            bounceButton(v);
            likeButton.setBackgroundResource(R.drawable.heart);
        });

        setUpPlayerControl();
        checkAccount();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(mp -> { });
        mediaPlayer.setOnCompletionListener(mp -> {});
         Seek.setProgress((int) startTime);
        if(isNetworkAvailable()) callMusicDetail();
        else{
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    private void transitionBottomSheetBackgroundColor(float slideOffset) {
        int colorFrom = getResources().getColor(R.color.colorAccent);
        int colorTo = getResources().getColor(R.color.colorAc);
        bottomSheet.setBackgroundColor(interpolateColor(slideOffset,
                colorFrom, colorTo));
    }

    private void animateBottomSheetArrows(float slideOffset) {
//        mPlayerControl.setY(slideOffset);//setRotation(slideOffset * -180);
        artist.setText(String.valueOf(slideOffset));
        musciProgresss.setY(slideOffset*10);//setRotation(slideOffset * 180);
    }

    // Helper method to interpolate colors
    private int interpolateColor(float fraction, int startValue, int endValue) {
        int startA = (startValue >> 24) & 0xff;
        int startR = (startValue >> 16) & 0xff;
        int startG = (startValue >> 8) & 0xff;
        int startB = startValue & 0xff;
        int endA = (endValue >> 24) & 0xff;
        int endR = (endValue >> 16) & 0xff;
        int endG = (endValue >> 8) & 0xff;
        int endB = endValue & 0xff;
        return ((startA + (int) (fraction * (endA - startA))) << 24) |
                ((startR + (int) (fraction * (endR - startR))) << 16) |
                ((startG + (int) (fraction * (endG - startG))) << 8) |
                ((startB + (int) (fraction * (endB - startB))));
    }

    private void bounceButton(View view) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        likeButton.startAnimation(myAnim);
    }

    private void setUpPlayerControl() {
        findViewById(R.id.player_control).setOnClickListener(smallPlay);
        findViewById(R.id.previous).setOnClickListener(smallPrevious);
        findViewById(R.id.next).setOnClickListener(smallNext);
        findViewById(R.id.player_control_p).setOnClickListener(bigPlay);
        findViewById(R.id.previous_p).setOnClickListener(bigPrevious);
        findViewById(R.id.next_p).setOnClickListener(bigNext);
        findViewById(R.id.ToVideo).setOnClickListener(startVideo);
    }

    private void checkAccount() {
        try { SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
            if (!prefs.getBoolean("logininfo", false)) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }else{UserName=prefs.getString("username",null);}
        }catch (Exception E){ Toast.makeText(context, "Couldn't Login", Toast.LENGTH_SHORT).show();
        }
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
                    case R.id.activity:
                        loadFragment(new ActivityFragment());
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
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
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
    @SuppressLint("DefaultLocale")
    private void handleResponse(Response<StreamingRequest> response) {
        try { mediaPlayer.setDataSource(response.body().getMp3Url());//here mp3 file is loaded using retrieved url and fed into mMediaPlayer
            mediaPlayer.prepareAsync();
            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();
            totaltime.setText(String.format("%d : %d",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime), TimeUnit.MILLISECONDS.toSeconds((long) finalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));
            currenttime.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
            myHandler.postDelayed(UpdateSongTime,100);
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

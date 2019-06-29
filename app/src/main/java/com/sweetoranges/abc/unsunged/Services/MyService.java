package com.sweetoranges.abc.unsunged.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sweetoranges.abc.unsunged.R;


public class MyService extends Service implements MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer ;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.letitgo);
        mediaPlayer.setOnCompletionListener(this);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Toast.makeText(this, "Music Is Playing", Toast.LENGTH_SHORT).show();
        }else mediaPlayer.pause();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
    public void onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();
    }
}
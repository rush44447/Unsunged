package com.sweetoranges.abc.unsunged.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.sweetoranges.abc.unsunged.Classes.MediaPlayerHolder;

public class MusicService extends Service {
    private final IBinder mIBinder = new LocalBinder();

   // private MediaPlayerHolder mMediaPlayerHolder= new MediaPlayerHolder(this);

    private MusicNotificationManager mMusicNotificationManager;

    private boolean sRestoredFromPause = false;

    public final boolean isRestoredFromPause() {
        return sRestoredFromPause;
    }

    public void setRestoredFromPause(boolean restore) {
        sRestoredFromPause = restore;
    }

    public final MediaPlayerHolder getMediaPlayerHolder() {
        return new MediaPlayerHolder(this);
    }

    public MusicNotificationManager getMusicNotificationManager() {
        return mMusicNotificationManager;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        new MediaPlayerHolder(this).registerNotificationActionsReceiver(false);
        mMusicNotificationManager = null;
        new MediaPlayerHolder(this).release();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        MediaPlayerHolder mMediaPlayerHolder=new MediaPlayerHolder(this);
        if (mMediaPlayerHolder == null) {
            mMediaPlayerHolder = new MediaPlayerHolder(this);
            mMusicNotificationManager = new MusicNotificationManager(this);
            mMediaPlayerHolder.registerNotificationActionsReceiver(true);
        }
        return mIBinder;
    }

    public class LocalBinder extends Binder {
        public MusicService getInstance() {
            return MusicService.this;
        }
    }
}
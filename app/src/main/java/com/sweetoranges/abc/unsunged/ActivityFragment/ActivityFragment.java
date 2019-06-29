package com.sweetoranges.abc.unsunged.ActivityFragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.RecyclerAdapter;
import com.sweetoranges.abc.unsunged.Classes.SongProvider;
import com.sweetoranges.abc.unsunged.Model.Song;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Services.MusicNotificationManager;
import com.sweetoranges.abc.unsunged.Services.MusicService;
import com.sweetoranges.abc.unsunged.interfaces.PlaybackInfoListener;
import com.sweetoranges.abc.unsunged.interfaces.PlayerAdapter;
import com.sweetoranges.abc.unsunged.utils.EqualizerUtils;

import java.util.List;
import android.Manifest;
import android.os.Handler;
import java.util.ArrayList;


public class ActivityFragment extends Fragment implements View.OnClickListener, RecyclerAdapter.SongClicked{

  private RecyclerView recyclerView;
  private SeekBar seekBar;
  private ImageButton playPause, next, previous;
  private TextView songTitle;
  private MusicService mMusicService;
  private Boolean mIsBound;
  private PlayerAdapter mPlayerAdapter;
  private boolean mUserIsSeeking = false;
  private PlaybackListener mPlaybackListener;
  private List<Song> mSelectedArtistSongs;
  private MusicNotificationManager mMusicNotificationManager;
  private RecyclerAdapter recyclerAdapter;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_activity,container,false);

    doBindService();

    playPause = view.findViewById(R.id.buttonPlayPause);
    next = view.findViewById(R.id.buttonNext);
    previous = view.findViewById(R.id.buttonPrevious);
    seekBar = view.findViewById(R.id.seekBar);
    recyclerView = view.findViewById(R.id.recyclerView);
    songTitle = view.findViewById(R.id.songTitle);
    playPause.setOnClickListener(this);
    next.setOnClickListener(this);
    previous.setOnClickListener(this);
    recyclerAdapter = new RecyclerAdapter(this);
    recyclerView.setAdapter(recyclerAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setHasFixedSize(true);
    mSelectedArtistSongs = SongProvider.getAllDeviceSongs(getActivity());
    recyclerAdapter.addSongs((ArrayList) mSelectedArtistSongs);

    initializeSeekBar();

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  private final ServiceConnection mConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

      mMusicService = ((MusicService.LocalBinder) iBinder).getInstance();
      mPlayerAdapter = mMusicService.getMediaPlayerHolder();
      mMusicNotificationManager = mMusicService.getMusicNotificationManager();

      if (mPlaybackListener == null) {
        mPlaybackListener = new PlaybackListener();
        mPlayerAdapter.setPlaybackInfoListener(mPlaybackListener);
      }
      if (mPlayerAdapter != null && mPlayerAdapter.isPlaying()) {

        restorePlayerStatus();
      }
      checkReadStoragePermissions();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
      mMusicService = null;
    }
  };


  @Override
  public void onPause() {
    super.onPause();
    doUnbindService();
    if (mPlayerAdapter != null && mPlayerAdapter.isMediaPlayer()) {
      mPlayerAdapter.onPauseActivity();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    doBindService();
    if (mPlayerAdapter != null && mPlayerAdapter.isPlaying()) {

      restorePlayerStatus();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
      Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
      getActivity().finish();
    }
  }

  private void checkReadStoragePermissions() {
    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
    }
  }

  private void updatePlayingInfo(boolean restore, boolean startPlay) {

    if (startPlay) {
      mPlayerAdapter.getMediaPlayer().start();
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          mMusicService.startForeground(MusicNotificationManager.NOTIFICATION_ID,
                  mMusicNotificationManager.createNotification());
        }
      }, 250);
    }

    final Song selectedSong = mPlayerAdapter.getCurrentSong();

    songTitle.setText(selectedSong.title);
    final int duration = selectedSong.duration;
    seekBar.setMax(duration);

    if (restore) {
      seekBar.setProgress(mPlayerAdapter.getPlayerPosition());
      updatePlayingStatus();


      new Handler().postDelayed(() -> {
        //stop foreground if coming from pause state
        if (mMusicService.isRestoredFromPause()) {
          mMusicService.stopForeground(false);
          mMusicService.getMusicNotificationManager().getNotificationManager()
                  .notify(MusicNotificationManager.NOTIFICATION_ID,
                          mMusicService.getMusicNotificationManager().getNotificationBuilder().build());
          mMusicService.setRestoredFromPause(false);
        }
      }, 250);
    }
  }

  private void updatePlayingStatus() {
    final int drawable = mPlayerAdapter.getState() != PlaybackInfoListener.State.PAUSED ? R.drawable.ic_pause_black_24dp : R.drawable.ic_play_arrow_black_24dp;
    playPause.post(() -> playPause.setImageResource(drawable));
  }

  private void restorePlayerStatus() {
    seekBar.setEnabled(mPlayerAdapter.isMediaPlayer());

    //if we are playing and the activity was restarted
    //update the controls panel
    if (mPlayerAdapter != null && mPlayerAdapter.isMediaPlayer()) {

      mPlayerAdapter.onResumeActivity();
      updatePlayingInfo(true, false);
    }
  }

  private void doBindService() {
    getActivity().bindService(new Intent(getActivity(), MusicService.class), mConnection, Context.BIND_AUTO_CREATE);
    mIsBound = true;

    final Intent startNotStickyIntent = new Intent(getActivity(), MusicService.class);
    getActivity().startService(startNotStickyIntent);
  }

  private void doUnbindService() {
    if (mIsBound) {
      getActivity().unbindService(mConnection);
      mIsBound = false;
    }
  }


  public void onSongSelected(@NonNull final Song song, @NonNull final List<Song> songs) {
    if (!seekBar.isEnabled()) {
      seekBar.setEnabled(true);
    }
    try {
      mPlayerAdapter.setCurrentSong(song, songs);
      mPlayerAdapter.initMediaPlayer();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void skipPrev() {
 //   if (checkIsPlayer()) {
      mPlayerAdapter.instantReset();
   // }
  }

  public void resumeOrPause() {
   // if (checkIsPlayer()) {
      mPlayerAdapter.resumeOrPause();
  //  }
  }

  public void skipNext() {
    //if (checkIsPlayer())
      mPlayerAdapter.skip(true);
  }

  private boolean checkIsPlayer() {
    try {  boolean isPlayer = mPlayerAdapter.isMediaPlayer();


  if (!isPlayer) {
    EqualizerUtils.notifyNoSessionId(getActivity());
  }
      return isPlayer;

    }catch (Exception e){
  Toast.makeText(getActivity(), "erroe", Toast.LENGTH_SHORT).show();
}return false;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case (R.id.buttonPlayPause): {
        Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
        resumeOrPause();
        break;

      }
      case (R.id.buttonNext): {
        Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();

        skipNext();
        break;
      }
      case (R.id.buttonPrevious): {
        skipPrev();
        break;
      }
    }
  }


  private void initializeSeekBar() {
    seekBar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
              int userSelectedPosition = 0;

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {
                mUserIsSeeking = true;
              }

              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                  userSelectedPosition = progress;

                }

              }

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {

                if (mUserIsSeeking) {

                }
                mUserIsSeeking = false;
                mPlayerAdapter.seekTo(userSelectedPosition);
              }
            });
  }


  @Override
  public void onSongClicked(Song song) {
    Toast.makeText(getActivity(), song.title, Toast.LENGTH_SHORT).show();
    onSongSelected(song, mSelectedArtistSongs);
  }

  class PlaybackListener extends PlaybackInfoListener {

    @Override
    public void onPositionChanged(int position) {
      if (!mUserIsSeeking) {
        seekBar.setProgress(position);
      }
    }

    @Override
    public void onStateChanged(@State int state) {

      updatePlayingStatus();
      if (mPlayerAdapter.getState() != State.RESUMED && mPlayerAdapter.getState() != State.PAUSED) {
        updatePlayingInfo(false, true);
      }
    }

    @Override
    public void onPlaybackCompleted() {
      //After playback is complete
    }
  }
}

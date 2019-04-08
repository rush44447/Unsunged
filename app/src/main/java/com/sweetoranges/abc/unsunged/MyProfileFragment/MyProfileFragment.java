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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;
import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.Adapters.SCTrackAdapter;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;
import com.sweetoranges.abc.unsunged.Player.Config;
import com.sweetoranges.abc.unsunged.Player.Track;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Service.SCService;

import java.util.ArrayList;
import java.util.List;

public class MyProfileFragment extends Fragment {
    private ListView listView;
    private List<Track> mListItems;
    ImageView circularImageView;
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);
        circularImageView = (ImageView)view.findViewById(R.id.circleView);
        listView = (ListView)view.findViewById(R.id.track_list_view);
        circularImageView.setImageBitmap(ImageConverter.getRoundedCornerBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.imgview), 100));
        mSelectedTrackTitle = (TextView)view.findViewById(R.id.selected_track_title);
        mSelectedTrackImage = (ImageView)view.findViewById(R.id.selected_track_image);
        mPlayerControl = (ImageView)view.findViewById(R.id.player_control);

        mListItems = new ArrayList<Track>();
        listView.setAdapter(new SCTrackAdapter(getActivity(), mListItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = mListItems.get(position);
                mSelectedTrackTitle.setText(track.getTitle());
                Picasso.with(getActivity()).load(track.getArtworkURL()).into(mSelectedTrackImage); }});

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });

        Fabric.with(getActivity(), new Crashlytics());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.API_URL).addConverterFactory(GsonConverterFactory.create()).build();



        SCService scService = retrofit.create(SCService.class);
        scService.getRecentTracks("last_week").enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (response.isSuccessful()) {
                    List<Track> tracks = response.body();
                    showMessage(tracks.get(0).getTitle());
                } else { showMessage("Error code " + response.code()); }
            }

            @Override public void onFailure(Call<List<Track>> call, Throwable t) { showMessage("Network Error: " +  t.getMessage()); }});
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
    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}

package com.sweetoranges.abc.unsunged.SongChangeFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sweetoranges.abc.unsunged.Classes.PagifyApp;
import com.sweetoranges.abc.unsunged.R;

public class SongChangeFragment extends Fragment {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_song_change,container,false);
//        return rootView;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

    public static final String ARGS_POSITION = "position";
    private int mPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARGS_POSITION, 0);
        }
        return inflater.inflate(R.layout.fragment_song_change, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadAlbumArt(view);
    }

    private void loadAlbumArt(View view) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(getActivity()).load(PagifyApp.getAlbumDatabase().get(mPosition).getTrackNumber()).into(imageView);
    }
}

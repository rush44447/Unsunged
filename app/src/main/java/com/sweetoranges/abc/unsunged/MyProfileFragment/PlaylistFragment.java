package com.sweetoranges.abc.unsunged.MyProfileFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.R;

public class PlaylistFragment extends Fragment{
    RecyclerView playlistRv;
    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
View view =inflater.inflate(R.layout.fragment_playlist, container, false);
                playlistRv= (RecyclerView) view.findViewById(R.id.playlistRecycle);

                playlistRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                playlistRv.setAdapter(new PlaylistAdapter(getActivity()));
        return view;
    }

}
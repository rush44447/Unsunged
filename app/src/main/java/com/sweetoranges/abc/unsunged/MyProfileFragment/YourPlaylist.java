package com.sweetoranges.abc.unsunged.MyProfileFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;

public class YourPlaylist extends Fragment implements OnBackPressed{
    public static final String MOVIE = "movie";
    private static final String LOG_TAG = "DetailsActivity";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_your_playlist, container, false);
    }
    @Override public void onBackPressed(){ getActivity().getSupportFragmentManager().popBackStack(); }


}

package com.sweetoranges.abc.unsunged.ChallengeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.R;

import org.jetbrains.annotations.Nullable;

import androidx.fragment.app.Fragment;

public class ChallengeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_challenge, container, false);
        return view;
    }

}

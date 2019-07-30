package com.sweetoranges.abc.unsunged;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TrendingFragment extends Fragment {
    List<String>Names=new ArrayList<>();
    public static TrendingFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        TrendingFragment fragment = new TrendingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trending, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Names.add("Louis the child");
        Names.add("Scarss On 45");
        Names.add("Maroon 5");
        Names.add("Coldplay");
        Names.add("Selena Gomez");
        Toast.makeText(getActivity(), String.valueOf(getSomeIdentifier()), Toast.LENGTH_SHORT).show();
        ((TextView)view.findViewById(R.id.apexName)).setText(Names.get(getSomeIdentifier()));
    }

    public int getSomeIdentifier() {
        return getArguments().getInt("position");
    }
}
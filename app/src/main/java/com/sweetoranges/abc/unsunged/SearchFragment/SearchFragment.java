package com.sweetoranges.abc.unsunged.SearchFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.HorizontalScrollView;

import java.util.Objects;

public class SearchFragment extends Fragment implements OnBackPressed {
    private RecyclerView searchRecycler;
    HorizontalScrollView hsv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        searchRecycler.setLayoutManager(layoutManager);
        searchRecycler.setAdapter(new SearchAdapter(getActivity()));
        return view;
    }
    @Override public void onBackPressed(){ Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack(); }
}
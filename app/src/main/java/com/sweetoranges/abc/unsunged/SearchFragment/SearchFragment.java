package com.sweetoranges.abc.unsunged.SearchFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.crashlytics.android.Crashlytics;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.R;

import io.fabric.sdk.android.Fabric;

public class SearchFragment extends Fragment {
    RecyclerView searchRecycler;
    EditText searchTile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        searchTile=(EditText) view.findViewById(R.id.searchTile);
        Fabric.with(getActivity(), new Crashlytics());

        searchRecycler= (RecyclerView) view.findViewById(R.id.searchRecycler);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchRecycler.setAdapter(new SearchAdapter(getActivity()));
        return view;
    }
}

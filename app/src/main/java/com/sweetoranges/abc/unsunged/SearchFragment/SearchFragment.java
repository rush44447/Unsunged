package com.sweetoranges.abc.unsunged.SearchFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
    Toolbar mToolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
     //   setSupportActionBar(mToolbar);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
       // searchTile=(EditText) view.findViewById(R.id.searchTile);
        Fabric.with(getActivity(), new Crashlytics());
        searchRecycler= (RecyclerView) view.findViewById(R.id.searchRecycler);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        searchRecycler.setAdapter(new SearchAdapter(getActivity()));
//        mAdapter = new ArrayAdapter(MainActivity.this,
//                android.R.layout.simple_list_item_1,
//                getResources().getStringArray(R.array.months_array));
//        mListView.setAdapter(mAdapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mListView.setEmptyView(mEmptyView);
        return view;
    }
}

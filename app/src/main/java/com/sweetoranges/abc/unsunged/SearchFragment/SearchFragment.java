package com.sweetoranges.abc.unsunged.SearchFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.crashlytics.android.Crashlytics;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.JJSearchView;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.anim.controller.JJBarWithErrorIconController;

import io.fabric.sdk.android.Fabric;

public class SearchFragment extends Fragment {
    RecyclerView searchRecycler;
    EditText searchTile;
    Toolbar mToolbar;
    JJSearchView mJJSearchView;


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

        mJJSearchView = (JJSearchView) view.findViewById(R.id.jjsv);
        mJJSearchView.setController(new JJBarWithErrorIconController());

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


    public void start(View v) {
        mJJSearchView.startAnim();
    }

    public void reset(View v) {
        mJJSearchView.resetAnim();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action2:
                mJJSearchView.setController(new JJBarWithErrorIconController());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}

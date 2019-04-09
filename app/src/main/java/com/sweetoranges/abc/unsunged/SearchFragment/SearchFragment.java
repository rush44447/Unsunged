package com.sweetoranges.abc.unsunged.SearchFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
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
    JJSearchView mJJSearchView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        mJJSearchView = (JJSearchView) view.findViewById(R.id.jjsv);
        mJJSearchView.setController(new JJBarWithErrorIconController());
//        Fabric.with(getActivity(), new Crashlytics());
        mJJSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
            }
        });
        searchRecycler= (RecyclerView) view.findViewById(R.id.searchRecycler);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
       // searchRecycler.setAdapter(new SearchAdapter(getActivity()));
        return view;
    }
    public void start(View v) { mJJSearchView = (JJSearchView) v.findViewById(R.id.jjsv);
        mJJSearchView.startAnim();
    }

    public void reset(View v) { mJJSearchView = (JJSearchView) v.findViewById(R.id.jjsv);
        mJJSearchView.resetAnim();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
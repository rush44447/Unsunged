package com.sweetoranges.abc.unsunged.SearchFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.MusicTypeAdapter;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.PopupMenu;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;
import java.util.Objects;

public class SearchFragment extends Fragment implements OnBackPressed {
    private RecyclerView searchRecycler;
    private RecyclerView typeRecyc;
    private SearchBox search;
    AppCompatTextView Profile,Type,Language,Mood;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);
        search = (SearchBox) view.findViewById(R.id.searchbox);
        Profile = (AppCompatTextView) view.findViewById(R.id.name);
        Type = (AppCompatTextView) view.findViewById(R.id.type);
        Language = (AppCompatTextView) view.findViewById(R.id.language);
        Mood = (AppCompatTextView) view.findViewById(R.id.mood);
        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),"name"));

        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        searchRecycler.setAdapter(new SearchAdapter(getActivity()));

        search.enableVoiceRecognition(getActivity());
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_history_black_24dp));
            search.addSearchable(option);
        }
        search.setSearchListener(new SearchListener(){
            @Override public void onSearchOpened() {}
            @Override public void onSearchClosed() { }
            @Override public void onSearchTermChanged(String term) { }
            @Override public void onSearch(String searchTerm) {
                Toast.makeText(getActivity(), searchTerm +" Searched", Toast.LENGTH_LONG).show();
            }
            @Override public void onResultClick(SearchResult result) { }
            @Override public void onSearchCleared() { }
        });
        search.setOverflowMenu(R.menu.overflow_menu);
        search.setOverflowMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.test_menu_item:
                        Toast.makeText(getActivity(), "Clicked!", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),"name"));
            }});
        Type.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),"type"));
            }});
        Language.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),"lang"));
            }});
        Mood.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
               typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),"mood"));
            }});
        return view;
    }
    @Override public void onBackPressed(){ Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack(); }
}
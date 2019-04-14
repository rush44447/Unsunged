package com.sweetoranges.abc.unsunged.SearchFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import android.widget.PopupMenu;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.MenuListener;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;
import java.util.Objects;

public class SearchFragment extends Fragment implements OnBackPressed {
    private RecyclerView searchRecycler;
    Boolean isSearch;
    private SearchBox search;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        search = (SearchBox) view.findViewById(R.id.searchbox);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        searchRecycler.setLayoutManager(layoutManager);
        searchRecycler.setAdapter(new SearchAdapter(getActivity()));
        search.enableVoiceRecognition(getActivity());
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_history_black_24dp));
            search.addSearchable(option);
        }
        search.setMenuListener(new MenuListener(){

            @Override
            public void onMenuClick() {
                //Hamburger has been clicked
                Toast.makeText(getActivity(), "Menu click", Toast.LENGTH_LONG).show();
            }

        });
        search.setSearchListener(new SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged(String term) {
                //React to the search term changing
                //Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(getActivity(), searchTerm +" Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
            }

            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
            }

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
        return view;
    }
    @Override public void onBackPressed(){ Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack(); }
}
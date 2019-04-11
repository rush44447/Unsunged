package com.sweetoranges.abc.unsunged.SearchFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.iammert.library.ui.multisearchviewlib.MultiSearchView;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.R;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import android.widget.HorizontalScrollView;
public class SearchFragment extends Fragment {
    private RecyclerView searchRecycler;
    HorizontalScrollView hsv;
   // MultiSearchView multiSearchView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
       // multiSearchView=(MultiSearchView)view.findViewById(R.id.multiSearchView);

//   multiSearchView.setSearchViewListener(new MultiSearchView.MultiSearchViewListener(){
//    @Override
//    public void onTextChanged(int i, @NotNull CharSequence charSequence) {
//        Log.v("TEST", "changed: index: $index, query: $s");
//        Toast.makeText(getActivity(), "changed", Toast.LENGTH_SHORT).show();}
//
//    @Override
//    public void onSearchItemRemoved(int i) {
//        Log.v("TEST", "remove: index: $index");
//        Toast.makeText(getActivity(), "remove", Toast.LENGTH_SHORT).show();}
//
//    @Override
//    public void onSearchComplete(int i, @NotNull CharSequence charSequence) {
//        Log.v("TEST", "complete: index: $index, query: $s");
//        Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();}
//
//    @Override
//    public void onItemSelected(int i, @NotNull CharSequence charSequence) {
//        Log.v("TEST", "onItemSelected: index: $index, query: $s");
//        Toast.makeText(getActivity(), "onItemSelected", Toast.LENGTH_SHORT).show();}});


        searchRecycler.setAdapter(new SearchAdapter(getActivity()));
        return view;
    }
}
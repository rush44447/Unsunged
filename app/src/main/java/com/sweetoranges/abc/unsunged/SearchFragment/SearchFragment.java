package com.sweetoranges.abc.unsunged.SearchFragment;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Adapters.MusicTypeAdapter;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.R;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    AppCompatButton Name,Mood,Language,Type;
    public String[] name = new String[]{};
    public String[] lang = new String[]{"English", "Hindi", "Gujrati", "Rajasthani"};
    public String[] mood = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    public String[] type = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};

    public RecyclerView searchRecycler,search,typeRecyc;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
           Name=(AppCompatButton)view.findViewById(R.id.name);
           Type=(AppCompatButton)view.findViewById(R.id.type);
           Language=(AppCompatButton)view.findViewById(R.id.language);
           Mood=(AppCompatButton)view.findViewById(R.id.mood);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        //search=(RecyclerView)view.findViewById(R.id.search);
        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);

        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
//
       searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        searchRecycler.setAdapter(new SearchAdapter(getActivity(),name));
//
//        search.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));// sqlite here
//
        Name.setOnClickListener(v -> searchRecycler.setAdapter(new SearchAdapter(getActivity(),name)));
        Type.setOnClickListener(v -> searchRecycler.setAdapter(new SearchAdapter(getActivity(),type)));
        Language.setOnClickListener(v -> searchRecycler.setAdapter(new SearchAdapter(getActivity(),lang)));
        Mood.setOnClickListener(v -> searchRecycler.setAdapter(new SearchAdapter(getActivity(),mood)));

        return view;
    }
}

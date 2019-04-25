package com.sweetoranges.abc.unsunged.Search;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sweetoranges.abc.unsunged.Adapters.MusicTypeAdapter;
import com.sweetoranges.abc.unsunged.Adapters.NotesAdapter;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.R;

public class SearchProfileFragment extends Fragment {
    public RecyclerView searchRecycler,search,typeRecyc;
    public String name[] = new String[]{};
    public String lang[] = new String[]{"English","Hindi","Gujrati","Rajasthani"};
    public String mood[] = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    public String type[] = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};

    public static final int EXTRA_REVEAL_CENTER_PADDING = 40;
    public SearchProfileFragment() {
    // Required empty public constructor
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View view=inflater.inflate(R.layout.fragment_search_profile, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        search=(RecyclerView)view.findViewById(R.id.search);
        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);

        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));

        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        searchRecycler.setAdapter(new SearchAdapter(getActivity()));

        search.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
       // search.setAdapter(new NotesAdapter(getActivity(), shops));

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//            }});
        return view;
    }
}

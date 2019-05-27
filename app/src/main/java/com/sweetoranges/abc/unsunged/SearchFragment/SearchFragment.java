package com.sweetoranges.abc.unsunged.SearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Adapters.HistoryAdapter;
import com.sweetoranges.abc.unsunged.Adapters.MusicTypeAdapter;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.R;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SearchFragment extends Fragment {
    AppCompatButton Name,Mood,Language,Type;
    public String[] name = new String[]{};
    public String[] lang = new String[]{"English", "Hindi", "Gujrati", "Rajasthani"};
    public String[] mood = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    public String[] type = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};
    public String[] history = new String[]{"Arijit","Shreya","Vishal","Neha"};

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
        search=(RecyclerView)view.findViewById(R.id.searchHistory);
        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);

        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));

       searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        searchRecycler.setAdapter(new SearchAdapter(getActivity(),name));
        search.setOnClickListener(v -> {
            search.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
            search.setAdapter(new HistoryAdapter(getActivity(),history));

        });



        Name.setOnClickListener(v -> {searchRecycler.setAdapter(new SearchAdapter(getActivity(),name));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));});
        Type.setOnClickListener(v -> {
            searchRecycler.setAdapter(new SearchAdapter(getActivity(),type));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),type)); });
        Language.setOnClickListener(v -> {
            searchRecycler.setAdapter(new SearchAdapter(getActivity(),lang));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),lang)); });
        Mood.setOnClickListener(v -> {
            searchRecycler.setAdapter(new SearchAdapter(getActivity(),mood));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),mood)); });

        return view;
    }
}

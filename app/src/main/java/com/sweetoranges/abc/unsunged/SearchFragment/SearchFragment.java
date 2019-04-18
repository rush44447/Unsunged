package com.sweetoranges.abc.unsunged.SearchFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.sweetoranges.abc.unsunged.Adapters.MusicTypeAdapter;
import com.sweetoranges.abc.unsunged.Adapters.NotesAdapter;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Sqlite.DatabaseHelper;
import com.sweetoranges.abc.unsunged.Sqlite.Note;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment  {
    private RecyclerView searchRecycler,search,typeRecyc;
    private AppCompatTextView Profile,Type,Language,Mood;
    EditText edtsearch;
    private NotesAdapter mAdapter;
    private List<Note> notesList = new ArrayList<>();
    private DatabaseHelper db = new DatabaseHelper(getActivity());

    private String name[] = new String[]{};
    private String lang[] = new String[]{"English","Hindi","Gujrati","Rajasthani"};
    private String mood[] = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    private String type[] = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        search=(RecyclerView)view.findViewById(R.id.search);
        edtsearch=(EditText)view.findViewById(R.id.editsearch);
        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);
        Profile = (AppCompatTextView) view.findViewById(R.id.name);
        Type = (AppCompatTextView) view.findViewById(R.id.type);
        Language = (AppCompatTextView) view.findViewById(R.id.language);
        Mood = (AppCompatTextView) view.findViewById(R.id.mood);
        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        searchRecycler.setAdapter(new SearchAdapter(getActivity()));


        Profile.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
            }});
        Type.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),type));
            }});
        Language.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),lang));
            }});
        Mood.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
               typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),mood));
            }});
        edtsearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                notesList.addAll(db.getAllNotes());

                searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
                searchRecycler.setItemAnimator(new DefaultItemAnimator());
                searchRecycler.setAdapter(mAdapter);

                //  searchRecycler.setAdapter(new SearchAdapter(getActivity()));
            }});
        edtsearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    createNote(edtsearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
        db = new DatabaseHelper(getActivity());
        return view;
    }

    private void createNote(String note) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertNote(note);

        // get the newly inserted note from db
        Note n = db.getNote(id);

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

        }
    }
}
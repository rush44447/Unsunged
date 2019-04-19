package com.sweetoranges.abc.unsunged.SearchFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class SearchFragment extends Fragment  {
    public RecyclerView searchRecycler,search,typeRecyc;
    public AppCompatTextView Profile,Type,Language,Mood;
    EditText edtsearch;
    Button btn;
    public List<Note> notesList = new ArrayList<>();
    public DatabaseHelper db = new DatabaseHelper(getActivity());

    public String name[] = new String[]{};
    public String lang[] = new String[]{"English","Hindi","Gujrati","Rajasthani"};
    public String mood[] = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    public String type[] = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        search=(RecyclerView)view.findViewById(R.id.search);
        btn=(Button)view.findViewById(R.id.btn);
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
//        try{notesList.addAll(db.getAllNotes());    maybe issue is ghere in addall
//             Toast.makeText(getActivity(), notesList.get(0).getNote(), Toast.LENGTH_SHORT).show();
//
//        }catch (Exception e){
//            Toast.makeText(getActivity(), "hrere", Toast.LENGTH_SHORT).show();
//        }
                search.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
                //  search.setAdapter(new NotesAdapter(getActivity(),notesList));
            }});
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                createNote(edtsearch.getText().toString());

            }});
//        edtsearch.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // If the event is a key-down event on the "enter" button
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    createNote(edtsearch.getText().toString());
//                    return true;
//                }
//                return false;
//            }
//        });
        return view;
    }

    private void createNote(String note) {
            long id = db.insertNote(note);
        Note n = db.getNote(id);
        if (n != null) {
            notesList.add(0, n);
          //  mAdapter.notifyDataSetChanged();
        }
    }
}
// try {
//
//        }catch(Exception e){
//            Toast.makeText(getActivity(), "err", Toast.LENGTH_SHORT).show();
//        }
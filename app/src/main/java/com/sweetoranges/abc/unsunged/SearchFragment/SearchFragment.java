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
import com.sweetoranges.abc.unsunged.R;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    AppCompatButton Name,Mood,Language,Type;
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


        return view;
    }
}

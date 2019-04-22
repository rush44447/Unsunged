package com.sweetoranges.abc.unsunged.SearchFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.ferfalk.simplesearchview.utils.DimensUtils;
import com.google.android.material.tabs.TabLayout;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Sqlite.DatabaseHelper;
import com.sweetoranges.abc.unsunged.Sqlite.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SearchFragment extends Fragment  {
//    public RecyclerView searchRecycler,search,typeRecyc;
//    public AppCompatTextView Profile,Type,Language,Mood;
//    Button btn;
private DatabaseHelper db = new DatabaseHelper(getActivity());

    private List<Note> notesList = new ArrayList<>();
    public String name[] = new String[]{};
    public String lang[] = new String[]{"English","Hindi","Gujrati","Rajasthani"};
    public String mood[] = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    public String type[] = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};

    public static final int EXTRA_REVEAL_CENTER_PADDING = 40;
    private SimpleSearchView searchView;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
Context context =getActivity();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        searchView = view.findViewById(R.id.searchView);
        tabLayout = view.findViewById(R.id.tabLayout);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        setHasOptionsMenu(true);
//        notesList.addAll(db.getAllNotes());
//        db.insertNote("heyhere");
//        Toast.makeText(context, notesList.get(0).getNote(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, db.getNote(0).getNote(), Toast.LENGTH_SHORT).show();


//        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
//        search=(RecyclerView)view.findViewById(R.id.search);
//        btn=(Button)view.findViewById(R.id.btn);
//        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);
//        Profile = (AppCompatTextView) view.findViewById(R.id.name);
//        Type = (AppCompatTextView) view.findViewById(R.id.type);
//        Language = (AppCompatTextView) view.findViewById(R.id.language);
//        Mood = (AppCompatTextView) view.findViewById(R.id.mood);
//        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
//        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
//        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
//        searchRecycler.setAdapter(new SearchAdapter(getActivity()));
//        search.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
//        search.setAdapter(new NotesAdapter(getActivity(), shops));
//
//        Profile.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
//            }});
//        Type.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),type));
//            }});
//        Language.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),lang));
//            }});
//        Mood.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//               typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),mood));
//            }});
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//            }});

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.main_menu, menu);
    super.onCreateOptionsMenu(menu,inflater);
    setupSearchView(menu);
}
    private void setupSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        searchView.setTabLayout(tabLayout);

        Point revealCenter = searchView.getRevealAnimationCenter();
        revealCenter.x = revealCenter.x - DimensUtils.convertDpToPx(EXTRA_REVEAL_CENTER_PADDING, getActivity());   //  imppp[ppppppppppppp
    }

//    @Override
//    public void onBackPressed() {
//        if (searchView.onBackPressed()) {
//            return;
//        }
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
            // Empty
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText("hey there");
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
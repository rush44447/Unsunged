package com.sweetoranges.abc.unsunged.SearchFragment;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.Classes.ApiCaller;
import com.sweetoranges.abc.unsunged.Classes.ApiInteract;
import com.sweetoranges.abc.unsunged.Model.Quick;
import com.sweetoranges.abc.unsunged.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


    public class SearchFragment extends Fragment{
    //AppCompatButton Name,Mood,Language,Type;
    public String[] lang = new String[]{"English", "Hindi", "Gujrati", "Rajasthani"};
    public String[] mood = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    public String[] type = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};
    public String[] history = new String[]{"Arijit","Shreya","Vishal","Neha"};
    private static final String URL = "https://api.androidhive.info/json/contacts.json";
    Context context=getActivity();
    private SearchView searchView;
    private SearchAdapter mAdapter;
    private TabLayout   tabLayout;
    private SearchView.OnQueryTextListener queryTextListener;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {       // new HistoryAdapter(getActivity(),history)
        View view=inflater.inflate(R.layout.fragment_search, container, false);
//         Name=(AppCompatButton)view.findViewById(R.id.name);
//         Type=(AppCompatButton)view.findViewById(R.id.type);
//         Language=(AppCompatButton)view.findViewById(R.id.language);
//         Mood=(AppCompatButton)view.findViewById(R.id.mood);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Category"));
        replaceFragment(new ProfileFragment());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new ProfileFragment());
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new CategoryFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
//        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);
//        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
//        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        //typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));

//        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),history));
//        searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,name));

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Unsunged");
        whiteNotificationBar(view);


//        Name.setOnClickListener(v -> {searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,name));
////            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
//        });
//        Type.setOnClickListener(v -> {
//            searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,type));
////            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),type));
//        });
//        Language.setOnClickListener(v -> {
//            searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,lang));
////            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),lang));
//        });
//        Mood.setOnClickListener(v -> {
//            searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,mood));
////            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),mood));
//        });

        return view;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
       // searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

      //  searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // filter recycler view when query submitted
//                mAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                // filter recycler view when text is changed
//                mAdapter.getFilter().filter(query);
//                return false;
//            }
//        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getActivity().getWindow().setStatusBarColor(Color.WHITE);
        }
    }

}

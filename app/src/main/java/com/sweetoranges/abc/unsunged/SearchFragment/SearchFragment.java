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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sweetoranges.abc.unsunged.Adapters.HistoryAdapter;
import com.sweetoranges.abc.unsunged.Adapters.MusicTypeAdapter;
import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.Classes.ApiCaller;
import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInteract;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.Model.Quick;
import com.sweetoranges.abc.unsunged.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import info.abdolahi.CircularMusicProgressBar;
import retrofit2.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class SearchFragment extends Fragment implements SearchAdapter.ContactsAdapterListener{
    AppCompatButton Name,Mood,Language,Type;
    public String[] name = new String[]{};
    public String[] lang = new String[]{"English", "Hindi", "Gujrati", "Rajasthani"};
    public String[] mood = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    public String[] type = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};
    public String[] history = new String[]{"Arijit","Shreya","Vishal","Neha"};
    private static final String URL = "https://api.androidhive.info/json/contacts.json";
    private List<Quick> contactList = new ArrayList<>();
    Context context=getActivity();
    private SearchView searchView;
    public RecyclerView searchRecycler,typeRecyc;
    private SearchAdapter mAdapter;
    private SearchView.OnQueryTextListener queryTextListener;
    ProgressBar progress;
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
         Name=(AppCompatButton)view.findViewById(R.id.name);
         Type=(AppCompatButton)view.findViewById(R.id.type);
         Language=(AppCompatButton)view.findViewById(R.id.language);
         Mood=(AppCompatButton)view.findViewById(R.id.mood);
        searchRecycler=(RecyclerView)view.findViewById(R.id.searchRecycler);
        progress=(ProgressBar)view.findViewById(R.id.progress);
        typeRecyc=(RecyclerView)view.findViewById(R.id.typeRecyc);
        typeRecyc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        //typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
        fetchContacts();

        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),history));
        searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,name));

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Unsunged");
        whiteNotificationBar(view);
        contactList = new ArrayList<>();


        Name.setOnClickListener(v -> {searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,name));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));});
        Type.setOnClickListener(v -> {
            searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,type));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),type)); });
        Language.setOnClickListener(v -> {
            searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,lang));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),lang)); });
        Mood.setOnClickListener(v -> {
            searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,mood));
            typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),mood)); });

        return view;
    }

    private void fetchContacts() {
        try {progress.setVisibility(View.VISIBLE);
             ApiInteract apiService = ApiCaller.getClient().create(ApiInteract.class);

            Call<List<Quick>> call = apiService.getStreaming();
            call.enqueue(new retrofit2.Callback<List<Quick>>() {
                @Override
                public void onResponse(Call<List<Quick>> call, retrofit2.Response<List<Quick>> response) {
//                    contactList.add(response.body());
                    List<Quick> cont=response.body();
                    Toast.makeText(getActivity(), cont.get(0).getName(), Toast.LENGTH_SHORT).show();
                    searchRecycler.setAdapter(new SearchAdapter(getActivity(), cont, SearchFragment.this::onContactSelected,name));
                   // recyclerView.setAdapter(new StoryAdapter(getActivity(),follow));
                }
                @Override
                public void onFailure(Call<List<Quick>> call, Throwable t) {
                    Toast.makeText(getActivity(), "failed to ", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(),"err", Toast.LENGTH_SHORT).show();
        }
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        // close search view on back button pressed
//        if (!searchView.isIconified()) {
//            searchView.setIconified(true);
//            return;
//        }
//        super.onBackPressed();
//    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getActivity().getWindow().setStatusBarColor(Color.WHITE);
        }
    }
    @Override
    public void onContactSelected(Quick contact) {
        Toast.makeText(getActivity(), "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show();
    }

}

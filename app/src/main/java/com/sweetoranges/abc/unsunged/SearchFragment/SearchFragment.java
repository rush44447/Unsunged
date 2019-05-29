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
import com.sweetoranges.abc.unsunged.Model.Quick;
import com.sweetoranges.abc.unsunged.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

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

    private SearchView searchView;
    public RecyclerView searchRecycler,search,typeRecyc;
    private SearchAdapter mAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        search.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        searchRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        typeRecyc.setAdapter(new MusicTypeAdapter(getActivity(),name));
        fetchContacts();
        searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this,name));

        search.setOnClickListener(v -> {
            search.setAdapter(new HistoryAdapter(getActivity(),history));
        });
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Unsunged");
        whiteNotificationBar(search);
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
        try {
             ApiInteract apiService = ApiCaller.getClient().create(ApiInteract.class);

            Call<Quick> call = apiService.getStreaming();
            call.enqueue(new retrofit2.Callback<Quick>() {
                @Override
                public void onResponse(Call<Quick> call, retrofit2.Response<Quick> response) {
                    contactList.add(response.body());
                    Toast.makeText(getActivity(), contactList.get(0).getName(), Toast.LENGTH_SHORT).show();

                   // recyclerView.setAdapter(new StoryAdapter(getActivity(),follow));
                }
                @Override
                public void onFailure(Call<Quick> call, Throwable t) {
                    Toast.makeText(getActivity(), "failed to connect", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(),"err", Toast.LENGTH_SHORT).show();
        }
//        JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if (response == null) {
//                            Toast.makeText(getActivity(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
//                            return;
//                        }
//
//                        List<Quick> items = new Gson().fromJson(response.toString(), new TypeToken<List<Quick>>() {
//                        }.getType());
//
//                        // adding contacts to contacts list
//                        contactList.clear();
//                        contactList.addAll(items);
//
//                        // refreshing recycler view
//                        mAdapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // error in getting json
//                Log.e(TAG, "Error: " + error.getMessage());
//            }
//        });
//
//        MyApplication.getInstance().addToRequestQueue(request);
    }
//
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
//
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                mAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                mAdapter.getFilter().filter(query);
//                return false;
//            }
//        });
//     //   return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
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

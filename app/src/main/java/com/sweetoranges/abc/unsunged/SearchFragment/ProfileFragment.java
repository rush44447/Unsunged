package com.sweetoranges.abc.unsunged.SearchFragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.SearchAdapter;
import com.sweetoranges.abc.unsunged.Classes.ApiCaller;
import com.sweetoranges.abc.unsunged.Classes.ApiInteract;
import com.sweetoranges.abc.unsunged.Model.Quick;
import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ProfileFragment extends Fragment implements SearchAdapter.ContactsAdapterListener{
  public RecyclerView searchRecycler;
  private List<Quick> contactList = new ArrayList<>();
  ProgressBar progress;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
    searchRecycler=(RecyclerView)rootView.findViewById(R.id.searchRecycler);
    progress=(ProgressBar)rootView.findViewById(R.id.progress);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
    layoutManager.setReverseLayout(true);
    searchRecycler.setLayoutManager(layoutManager);
    fetchSearch();
    searchRecycler.setAdapter(new SearchAdapter(getActivity(), contactList, this));

    return rootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  private void fetchSearch() {
    try {progress.setVisibility(View.VISIBLE);
      ApiInteract apiService = ApiCaller.getClient().create(ApiInteract.class);

      Call<List<Quick>> call = apiService.getStreaming();
      call.enqueue(new retrofit2.Callback<List<Quick>>() {
        @Override
        public void onResponse(Call<List<Quick>> call, retrofit2.Response<List<Quick>> response) {
//                    contactList.add(response.body());
          List<Quick> cont=response.body();
          searchRecycler.setAdapter(new SearchAdapter(getActivity(), cont, ProfileFragment.this::onContactSelected));
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
  @Override public void onContactSelected(Quick contact) {
    Toast.makeText(getActivity(), "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show();
  }

}

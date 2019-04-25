package com.sweetoranges.abc.unsunged.BingeFragment;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInterface;
import com.sweetoranges.abc.unsunged.Classes.Client;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.Model.Story;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Services.Service;
import com.sweetoranges.abc.unsunged.Story.StoryAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BingeFragment extends Fragment  {
    String userID;
    private RecyclerView recyclerView;
    private List<Story> followings = new ArrayList<>();
    private List<StreamingRequest> follow = new ArrayList<>();
    ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_binge, container, false);
//        try{ ObjectAnimator colorFade = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), Color.argb(255,255,255,255), 0xff000000);
//            colorFade.setDuration(7000);
//            colorFade.start();
//        }catch (Exception c){}

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Fetching Stories...");
        pd.setCancelable(false);
        pd.show();

     //   followings=getFollowingsId();
        recyclerView = view.findViewById(R.id.story);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadJSON();

         //   recyclerView.setAdapter(new StoryAdapter(getActivity(),followings));

        return view;
    }
//
//    private List<Story> getFollowingsId() {
//        // hit api with userID and get idcodes of followings
//        return null;
//    }
    private void loadJSON() {
        //Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            ApiClient Client = new ApiClient();
            //Service request = com.sweetoranges.abc.unsunged.Classes.Client.retrofit.create(Service.class);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);//connection is built
    Call<StreamingRequest> call = apiService.getStreaming("idshnmkl");//this is added to baseurl and data is  retrieved

           // Call<List<Story>> call = request.getStories();
            call.enqueue(new retrofit2.Callback<StreamingRequest>() {
                @Override
                public void onResponse(Call<StreamingRequest> call, Response<StreamingRequest> response) {
                    follow.add(response.body());
                    Toast.makeText(getActivity(), follow.get(0).getTitle(), Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new StoryAdapter(getActivity(),follow));
                    pd.hide();
                }
                @Override
                public void onFailure(Call<StreamingRequest> call, Throwable t) {
                    Toast.makeText(getActivity(), "failed to connect", Toast.LENGTH_SHORT).show();
                }
            });


//            call.enqueue(new Callback<List<Story>>() {
//                @Override public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
//                    followings = response.body();
//                    recyclerView.setAdapter(new StoryAdapter(getActivity(),followings));
//                    pd.hide(); }
//                    @Override public void onFailure(Call<List<Story>> call, Throwable t) {
//                    Log.d("Error", t.getMessage());
//                    Toast.makeText(getActivity(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();pd.hide();
//                }});
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(),"err", Toast.LENGTH_SHORT).show();
        }
    }
}
//
/// ApiClient Client = new ApiClient();

//    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);//connection is built
//
//    Call<StreamingRequest> call = apiService.getStreaming("idshnmkl");//this is added to baseurl and data is  retrieved
//            call.enqueue(new retrofit2.Callback<StreamingRequest>() {
//@Override
//public void onResponse(Call<StreamingRequest> call, Response<StreamingRequest> response) {
//        handleResponse(response);
//        }
//
//@Override
//public void onFailure(Call<StreamingRequest> call, Throwable t) {
//        System.out.println("FAILED " + t.toString());
//        }
//        });

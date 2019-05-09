package com.sweetoranges.abc.unsunged.BingeFragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInterface;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.Model.Story;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Story.StoryAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Response;

public class BingeFragment extends Fragment  {
    private RecyclerView recyclerView;
    private List<StreamingRequest> follow = new ArrayList<>();
    ProgressDialog pd;
    private List<String> songList=new ArrayList<>();
    AppCompatImageView firstImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_binge, container, false);
//        try{ ObjectAnimator colorFade = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), Color.argb(255,255,255,255), 0xff000000);
//            colorFade.setDuration(7000);
//            colorFade.start();
//        }catch (Exception c){}
        firstImage=(AppCompatImageView)view.findViewById(R.id.firsthero) ;
        AppCompatTextView first = view.findViewById(R.id.firsttext);
        AppCompatTextView second = view.findViewById(R.id.secondtext);
        AppCompatTextView third = view.findViewById(R.id.thirdtext);
        AppCompatTextView forth = view.findViewById(R.id.forthtext);
        AppCompatTextView fifth = view.findViewById(R.id.fifthtext);
        first.setSelected(true);
        second.setSelected(true);
        third.setSelected(true);
        forth.setSelected(true);
        fifth.setSelected(true);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Fetching Stories...");
        pd.setCancelable(false);
        pd.show();

     //   followings=getFollowingsId();
        recyclerView = view.findViewById(R.id.story);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        songList=getDataForStory();
        firstImage.setOnClickListener(v -> recyclerView.smoothScrollBy(500, 0));
        loadJSON();
        return view;
    }

    private List<String> getDataForStory() {
        List<String> getStory=new ArrayList<>();

    return getStory;}


    private void loadJSON() {
        try { Call<StreamingRequest> call = MainActivity.apiService.getStreaming("idshnmkl");
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

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(),"err", Toast.LENGTH_SHORT).show();
        }
    }


}
package com.sweetoranges.abc.unsunged.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sweetoranges.abc.unsunged.Classes.ArcTranslateAnimation;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.Model.Quick;
import com.sweetoranges.abc.unsunged.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Response;

import static com.sweetoranges.abc.unsunged.MainActivity.apiService;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable{
    private  Context context;
   // private ViewHolder viewHolder;
    //    private String[] name = new String[]{"Rushabh","Parth","Shailesh","Sangita"};
    private List<Quick> contactList;
    private List<Quick> contactListFiltered;
    private ContactsAdapterListener listener;
    public int[] id=new int[]{1,2,3,4};
    List<String>songs=new ArrayList<>();
    public SearchAdapter(FragmentActivity activity, List<Quick> contactList,  ContactsAdapterListener listener) {
        this.context = activity;
        this.contactList=contactList;
        this.listener = listener;
        this.contactListFiltered = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Username,Userrank;
        private ImageView ProfilePic;
        private CardView card;
        private RecyclerView mRecyclerView;

        public MyViewHolder(View view) {
            super(view);
            Username=(TextView)view.findViewById(R.id.username);
            Userrank=(TextView)view.findViewById(R.id.userrank);
            card=(CardView)view.findViewById(R.id.card_griditem);
            mRecyclerView=(RecyclerView)view.findViewById(R.id.quickrecycler);
            ProfilePic=(ImageView)view.findViewById(R.id.circleView);
            view.setOnClickListener(view1 -> {
                listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
            });


        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tiles, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Quick contact = contactListFiltered.get(position);
        holder.Userrank.setText(contact.getName());
        holder.Username.setText(contact.getPhone());
        Glide.with(context)
                .load(contact.getImage())
               // .apply(RequestOptions.circleCropTransform())
                .into(holder.ProfilePic);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        if(isNetworkAvailable())callMusicDetail(holder.mRecyclerView);

        onHitMeToShowBezier(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        if(contactListFiltered.size()<7)return contactListFiltered.size();
        else return 7;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<Quick> filteredList = new ArrayList<>();
                    for (Quick row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Quick>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public void onHitMeToShowBezier(final View v, int position) {
        ArcTranslateAnimation animation = new ArcTranslateAnimation(1000, 0, 800, 0);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(500);
        v.startAnimation(animation);
    }
    public interface ContactsAdapterListener {
        void onContactSelected(Quick contact);
    }

    private void callMusicDetail(RecyclerView mRecyclerView) {
        Call<StreamingRequest> call = apiService.getStreaming("idshnmkl");
        call.enqueue(new retrofit2.Callback<StreamingRequest>() {
            @Override
            public void onResponse(Call<StreamingRequest> call, Response<StreamingRequest> response) {
                songs.clear();
                for(int i=0;i<4;i++){
                    songs.add(response.body().getMp3Url());
                }
                mRecyclerView.setAdapter(new QuickAdapter(context,id,songs));

            }
            @Override
            public void onFailure(Call<StreamingRequest> call, Throwable t) {
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

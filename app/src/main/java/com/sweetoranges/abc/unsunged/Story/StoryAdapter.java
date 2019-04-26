package com.sweetoranges.abc.unsunged.Story;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.Model.Story;
import com.sweetoranges.abc.unsunged.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private  Context context;
    private ViewHolder viewHolder;

    private List<StreamingRequest> followings;
    public StoryAdapter(FragmentActivity activity, List<StreamingRequest> followings) {
        this.followings=followings;
        this.context=activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView storyImage;
        private TextView Username;

        private ViewHolder(final View v){
            super(v);
            storyImage=(ImageView)v.findViewById(R.id.storyimage);
            Username=(TextView) v.findViewById(R.id.storyname);

        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.storyImage.setImageResource(R.drawable.imgview);
        holder.Username.setText(followings.get(0).getStatus());
       // holder.categorytext.setText(followings.get(0).getTitle());
//        holder.Titletext.setText(followings.get(position).getTitle());
//        holder.dateCreated.setText(followings.get(position).getDateCreated());
//        Picasso.with(context)
//                .load(followings.get(position).getCover())
//                .placeholder(R.drawable.imgview)
//                .into(viewHolder.storyImage);
    }
    @Override public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.freshstory, parent, false);

        return new ViewHolder(v); }
    @Override public int getItemCount(){ return 6;  }

}
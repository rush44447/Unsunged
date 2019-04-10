package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    Context context;
    private ViewHolder viewHolder;
    List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    private String types;
    public PlaylistAdapter(FragmentActivity activity,String Types) { context = activity;this.types=Types; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView listType;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.listType);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.listType.setText(name[position]);
    }


    @Override public PlaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.playlist, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }
}

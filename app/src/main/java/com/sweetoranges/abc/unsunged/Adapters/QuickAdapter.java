package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

public class QuickAdapter extends RecyclerView.Adapter<QuickAdapter.ViewHolder> {
    public Context context;
    private ViewHolder viewHolder;
    private int[] ids ;

    public QuickAdapter(Context context, int[] id) {this.context = context;this.ids=id; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView listType;
//        private View Quickback;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.quickname);
//            Quickback=(View)v.findViewById(R.id.quickback);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.listType.setText("here");
//        if(position==0) holder.Quickback.setBackgroundResource(R.drawable.lady);
//        if(position==1) holder.Quickback.setBackgroundResource(R.drawable.ladya);
//        if(position==2) holder.Quickback.setBackgroundResource(R.drawable.ladyb);
//        if(position==3) holder.Quickback.setBackgroundResource(R.drawable.ladyc);

    }


    @Override public QuickAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.quicksearch, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }



    @Override public int getItemCount(){ return 3;  }
}

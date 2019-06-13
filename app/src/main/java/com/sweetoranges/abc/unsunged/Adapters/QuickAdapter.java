package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.utils.MyBounceInterpolator;

import java.util.ArrayList;
import java.util.List;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;
public class QuickAdapter extends RecyclerView.Adapter<QuickAdapter.ViewHolder> {
    public Context context;
    private ViewHolder viewHolder;
    private int[] ids ;

    public QuickAdapter(Context context, int[] id) {this.context = context;this.ids=id; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView listType;
        FloatingActionButton playsearch;
        private ImageView likesearch;
//        private View Quickback;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.quickname);
            playsearch=(FloatingActionButton)v.findViewById(R.id.playsearch);
            likesearch=(ImageView)v.findViewById(R.id.likesearch);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.listType.setText("here");
        holder.likesearch.setOnClickListener(v -> {
            bounceButton(v);
            holder.likesearch.setBackgroundResource(R.drawable.heart);
        });

//        if(position==0) holder.Quickback.setBackgroundResource(R.drawable.lady);
//        if(position==1) holder.Quickback.setBackgroundResource(R.drawable.ladya);
//        if(position==2) holder.Quickback.setBackgroundResource(R.drawable.ladyb);
//        if(position==3) holder.Quickback.setBackgroundResource(R.drawable.ladyc);

    }

    private void bounceButton(View view) {
        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        view.startAnimation(myAnim);
    }


    @Override public QuickAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.quicksearch, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }

    @Override public int getItemCount(){ return 3;  }

}

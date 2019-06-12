package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sweetoranges.abc.unsunged.R;

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
        VideoView videoView;
//        private View Quickback;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.quickname);
            videoView =(VideoView)v.findViewById(R.id.videoView);

            //Creating MediaController

//            Quickback=(View)v.findViewById(R.id.quickback);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.listType.setText("here");
        MediaController mediaController= new MediaController(context);
        mediaController.setAnchorView(holder.videoView);
        String path = "android.resource://" + context.getPackageName() + "/" + R.raw.marvel;
        holder.videoView.setVideoURI(Uri.parse(path));
        holder.videoView.requestFocus();
        holder.videoView.start();
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

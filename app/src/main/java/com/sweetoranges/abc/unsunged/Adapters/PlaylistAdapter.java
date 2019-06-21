package com.sweetoranges.abc.unsunged.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.Activities.YourPlaylist;
import com.sweetoranges.abc.unsunged.Model.PlaylistType;
import com.sweetoranges.abc.unsunged.R;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>  {
    public Context context;
    private ViewHolder viewHolder;
    List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    List<PlaylistType> play=new ArrayList<PlaylistType>() ;

    public PlaylistAdapter(FragmentActivity activity) { this.context = activity; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView animage;
        private TextView listType;
        private MediaPlayer mMediaPlayers;
        int x=-1;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.listType);
            animage=(ImageView)v.findViewById(R.id.animage);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        final PlaylistType play=new PlaylistType();
        holder.listType.setText(name[position]);
        holder.listType.setOnClickListener(view -> {
            Intent i =new Intent(context, YourPlaylist.class);
            i.putExtra("name",name[0]);
            Pair[] pairs=new Pair[2];
            pairs[0]=new Pair<View,String>(holder.listType,"txt_transition");
            pairs[1]=new Pair<View,String>(holder.animage,"img_transition");
          //  ActivityOptions option=new  ActivityOptions.makeSceneTransitionAnimation((Activity)context,pairs);
            //context.startActivity(i,option.toBundle());
        });
    }

    @Override public PlaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.playlist, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }



    @Override public int getItemCount(){ return name.length;  }
}
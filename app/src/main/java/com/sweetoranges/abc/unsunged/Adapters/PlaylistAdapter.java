package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        private TextView listType;
        private MediaPlayer mMediaPlayers;
        int x=-1;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.listType);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        final PlaylistType play=new PlaylistType();
        holder.listType.setText(name[position]);
        holder.listType.setOnClickListener(view -> {
//            Intent playlist =new Intent(context, YourPlaylist.class);
        });
    }


    @Override public PlaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.playlist, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }



    @Override public int getItemCount(){ return name.length;  }
}
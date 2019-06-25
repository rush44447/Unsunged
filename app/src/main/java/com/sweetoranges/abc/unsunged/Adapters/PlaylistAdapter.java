package com.sweetoranges.abc.unsunged.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Activities.YourPlaylist;
import com.sweetoranges.abc.unsunged.Model.PlaylistType;
import com.sweetoranges.abc.unsunged.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>  {
    public Context context;
    private ViewHolder viewHolder;
    List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    private int shareID[] = new int[]{0,1,2,3,4,6};
    private String shareSong[] = new String[]{"Believer","here with me","Better Not"};
    private String shuffleSong[] = new String[]{"Believer","here with me","Better Not"};
    private List<String> shuffleID = new ArrayList<>();

    List<PlaylistType> play=new ArrayList<PlaylistType>() ;

    public PlaylistAdapter(FragmentActivity activity) { this.context = activity; }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView Share,Shuffle;
        private TextView listType;
        private MediaPlayer mMediaPlayers;
        private View list;
        int x=-1;
        private ViewHolder(final View v){
            super(v);
            listType=(TextView)v.findViewById(R.id.listType);
            Share=(ImageView)v.findViewById(R.id.shareLiked);
            Shuffle=(ImageView)v.findViewById(R.id.shuffleliked);
            list=(View)v.findViewById(R.id.list);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        final PlaylistType play=new PlaylistType();
        holder.listType.setText(name[position]);
        holder.list.setOnClickListener(view -> {
            Intent i =new Intent(context, YourPlaylist.class);
            i.putExtra("name",name[0]);
            context.startActivity(i);
        });
        holder.Shuffle.setOnClickListener(v -> {
            shuffleID.add("Believer");
            shuffleID.add("here with me");
            shuffleID.add("Better Not");
            Collections.shuffle(shuffleID);
            Toast.makeText(context, shuffleID.get(0)+shuffleID.get(1)+shuffleID.get(2), Toast.LENGTH_SHORT).show();
        });
        holder.Share.setOnClickListener(v -> {
            String Message="";
            for(int i=0;i<shareSong.length;i++){
                Message=Message+(shareSong[i])+", ";// get names and append that here
            }
            SharedPreferences prefs = context.getSharedPreferences("login", MODE_PRIVATE);
            String name=prefs.getString("username", "");
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,name+" has shared you a playlist");
            i.putExtra(android.content.Intent.EXTRA_TEXT, Message);
            context.startActivity(Intent.createChooser(i,"Share via"));
        });
    }

    @Override public PlaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.playlist, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }



    @Override public int getItemCount(){ return name.length;  }
}
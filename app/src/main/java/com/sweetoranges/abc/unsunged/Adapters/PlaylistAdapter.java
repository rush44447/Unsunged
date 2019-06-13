package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Classes.ApiClient;
import com.sweetoranges.abc.unsunged.Classes.ApiInterface;
import com.sweetoranges.abc.unsunged.Classes.DetailsTransition;
import com.sweetoranges.abc.unsunged.Classes.PlayListViewHolder;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.DetailsFragment.DetailsFragment;
import com.sweetoranges.abc.unsunged.MyProfileFragment.YourPlaylist;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.interfaces.PlayListClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Response;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>  {
    public Context context;
    private ViewHolder viewHolder;
    List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
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
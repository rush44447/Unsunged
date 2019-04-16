package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.ViewHolder> {
    public Context context;
    private ViewHolder viewHolder;
    private String searchby;
    List<String[]> dataList = new ArrayList<String[]>();
    private String lang[] = new String[]{"English","Hindi","Gujrati","Rajasthani"};
    private String mood[] = new String[]{"Soothing","Travelling","Happy","Nostalgia","Inspirational","Slow"};
    private String type[] = new String[]{"Jazz","Rock","Indian Classical Music","Popular Music", "Folk Music","Rap","Country Music","Indie Rock","Pop Music","Techno","Rhythm and Blues","Instrumental","Electronic Dance Music"};
    private String length[]=new String[]{};
    public MusicTypeAdapter(FragmentActivity activity,String searchby) {this.context = activity;this.searchby=searchby;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView listText;
        private MediaPlayer mMediaPlayers;
        int x=-1;
        private ViewHolder(final View v){
            super(v);
            listText=(TextView)v.findViewById(R.id.listText);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        if(searchby.equals("lang")){
            length=lang;
            holder.listText.setText(lang[position]);}
        if(searchby.equals("mood")){
            length=mood;
        holder.listText.setText(mood[position]);}
        if(searchby.equals("type")){
            length=type;
            holder.listText.setText(type[position]);}
        if(searchby.equals("name")){ holder.listText.setText("");}
        holder.listText.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
            }});
    }


    @Override public MusicTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.play, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return length.length;}
}
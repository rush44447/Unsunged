package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private ViewHolder viewHolder;
  //  List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    public SearchAdapter(FragmentActivity activity) {
        context = activity;
       // for (int i = 0; i < name.length; i++) { dataList.add(new String[]{name[i]}); }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text11;
        private CardView card;
        private GridView gridView;
        private ViewHolder(final View v){
            super(v);
            text11=(TextView)v.findViewById(R.id.text11);
            card=(CardView)v.findViewById(R.id.card_griditem);
            gridView=(GridView)v.findViewById(R.id.gridview);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(" hey"+" ");//name[position]

//        Animation slide_up = AnimationUtils.loadAnimation(context, R.anim.slide_up);
//        holder.text11.startAnimation(slide_up);
    }
    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);
         viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }
}

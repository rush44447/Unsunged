package com.sweetoranges.abc.unsunged.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


//     ADD ANIMATION CARD RISING FROM BOTTOM UP FROM RIGHT
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ViewHolder viewHolder;
    CardView crd;
    List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    public SearchAdapter(FragmentActivity activity) {
        this.context = activity;
       // for (int i = 0; i < name.length; i++) { dataList.add(new String[]{name[i]}); }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView text11;
        private ViewHolder(final View v){
            super(v);
            text11=(TextView)v.findViewById(R.id.text11);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(" "+name[position]+" ");//name[position]



//        Animation slide_up = AnimationUtils.loadAnimation(context, R.anim.slide_up);
//        holder.text11.startAnimation(slide_up);
    }
    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }
}

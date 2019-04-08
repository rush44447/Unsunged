package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;
//     ADD ANIMATION CARD RISING FROM BOTTOM UP FROM RIGHT
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ViewHolder viewHolder;
    CardView  crd;
    List<String[]> dataList = new ArrayList<String[]>();
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    public SearchAdapter(FragmentActivity activity) {
        context = activity;
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
    }
    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);
        viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }
}

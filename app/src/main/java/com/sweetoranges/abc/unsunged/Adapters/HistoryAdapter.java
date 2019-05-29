package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sweetoranges.abc.unsunged.Classes.ArcTranslateAnimation;
import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private String[] searchby;
    private Context context;
    private ViewHolder viewHolder;
    public HistoryAdapter(FragmentActivity activity,String[] searchby) { context = activity;this.searchby=searchby; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView historyText;
        private ImageButton Cross;
        private ViewHolder(final View v){
            super(v);
            historyText=(TextView)v.findViewById(R.id.row_item);
            Cross=(ImageButton)v.findViewById(R.id.cross);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
            holder.historyText.setText(searchby[position]);
            holder.Cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> numlist = new ArrayList<String>();
                    for(int i= 0;i<searchby.length;i++)
                    {
                        if(position == Integer.parseInt(searchby[i]))
                        {
                            // No operation here
                        }
                        else
                        {
                            numlist.add(searchby[i]);
                        }
                    }
                    searchby = numlist .toArray(new String[numlist .size()]);
                }
            });
    }

    @Override public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.searched, parent, false);

        return new ViewHolder(v); }
    @Override public int getItemCount(){ return searchby.length;  }


}

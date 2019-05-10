package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager;
import com.sweetoranges.abc.unsunged.Classes.ArcTranslateAnimation;
import com.sweetoranges.abc.unsunged.R;
import java.util.Arrays;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private  Context context;
    private ViewHolder viewHolder;
    private String[] name = new String[]{"Rushabh","Parth","Shailesh","Sangita"};
    private String[] searchby;

    public SearchAdapter(FragmentActivity activity,String[] searchby) { context = activity;this.searchby=searchby; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text11;
        private CardView card;
        private RecyclerView mRecyclerView;
        private EchelonLayoutManager mLayoutManager;
        private ViewHolder(final View v){
            super(v);
            text11=(TextView)v.findViewById(R.id.username);
            card=(CardView)v.findViewById(R.id.card_griditem);
            mRecyclerView = v.findViewById(R.id.recycler_view);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(name[position]);
        holder.mLayoutManager = new EchelonLayoutManager(context);
        holder.mRecyclerView.setLayoutManager(holder.mLayoutManager);
        holder.mRecyclerView.setAdapter(new MyAdapter());

        onHitMeToShowBezier(holder.itemView, position);
    }

    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);

        return new ViewHolder(v); }
    @Override public int getItemCount(){ return name.length;  }

    public void onHitMeToShowBezier(final View v, int position) {
        ArcTranslateAnimation animation = new ArcTranslateAnimation(1000, 0, 800, 0);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(500);
        v.startAnimation(animation);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_echelon,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.backlayout.setBackgroundResource(R.drawable.imgview);
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout backlayout;
            public ViewHolder(View itemView) {
                super(itemView);
                backlayout = itemView.findViewById(R.id.layout);

            }
        }
    }
}

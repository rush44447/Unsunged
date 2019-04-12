package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.GridView;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.Classes.ArcTranslateAnimation;
import com.sweetoranges.abc.unsunged.R;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import io.codetail.animation.arcanimator.Side;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private ViewHolder viewHolder;
    ViewGroup mParent;
    Side mSide;
    float startX;
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    public SearchAdapter(FragmentActivity activity) {
        context = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text11;
        private CardView card;
        private GridView gridView;
        private ViewHolder(final View v){
            super(v);
            text11=(TextView)v.findViewById(R.id.text11);
            card=(CardView)v.findViewById(R.id.card_griditem);
           // gridView=(GridView)v.findViewById(R.id.gridview);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(name[position]);//name[position]
        startX = holder.itemView.getTranslationX();
        startX = holder.itemView.getTranslationY();
        onHitMeToShowBezier(holder.itemView, position);
    }
    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);
         viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }
//    private void setAnimation(final View viewToAnimate, int position)
//    {
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
//        animation.setDuration(500);
//        animation.setStartOffset(position*100);
//        viewToAnimate.startAnimation(animation);
  //  }
    public void onHitMeToShowBezier(final View v, int position) {
        ArcTranslateAnimation animation = new ArcTranslateAnimation(-400, 0, -600, 0);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(500);
        animation.setFillAfter(true);
        v.startAnimation(animation);
    }
}

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.codetail.animation.arcanimator.ArcAnimator;
import io.codetail.animation.arcanimator.ArcDebugView;
import io.codetail.animation.arcanimator.Side;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private ViewHolder viewHolder;
    ViewGroup mParent;
    Side mSide;
    float startX;
    float startY;
    ArcDebugView mArcDebugView;
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
           // gridView=(GridView)v.findViewById(R.id.gridview);
        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(name[position]);//name[position]
        startX = holder.itemView.getTranslationX();
        startX = holder.itemView.getTranslationY();
        setAnimation(holder.itemView, position);
    }
    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);
         viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }
    private void setAnimation(View viewToAnimate, int position)
    {  //mParent = (ViewGroup) viewToAnimate;
        float startBlueX;
        float startBlueY;
        int endBlueX;
        int endBlueY;
        float startRedX;
        float startRedY;
//        viewToAnimate.setTranslationX(startX);
//        viewToAnimate.setTranslationY(startY);
//        endBlueX = viewToAnimate.getRight() / 2;
//        endBlueY = (int) (viewToAnimate.getBottom() * 0.8f);
//        ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(viewToAnimate, endBlueX,
//                endBlueY, 30, Side.LEFT)
//                .setDuration(500);
//        arcAnimator.start();
//        ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(viewToAnimate, mParent, 30, Side.RIGHT)
//                .setDuration(400);
//        arcAnimator.start();
//        mArcDebugView.drawArcAnimator(arcAnimator);


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        animation.setDuration(500);
        animation.setStartOffset(position*100);
        viewToAnimate.startAnimation(animation);
    }
}

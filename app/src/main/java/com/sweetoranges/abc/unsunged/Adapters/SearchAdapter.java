package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;
import com.sweetoranges.abc.unsunged.Classes.ArcTranslateAnimation;
import com.sweetoranges.abc.unsunged.R;
import java.util.Arrays;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private  Context context;
    private ViewHolder viewHolder;
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};

    public SearchAdapter(FragmentActivity activity) { context = activity; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text11;
        private CardView card;
        private ViewHolder(final View v){
            super(v);
            text11=(TextView)v.findViewById(R.id.text11);
            card=(CardView)v.findViewById(R.id.card_griditem);

        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(name[position]);

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
}
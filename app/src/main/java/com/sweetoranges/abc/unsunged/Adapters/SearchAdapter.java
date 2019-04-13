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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements CardStackView.ItemExpendListener{
    private Context context;
    private ViewHolder viewHolder;

    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };

    public SearchAdapter(FragmentActivity activity) {
        context = activity;
    }

    @Override
    public void onItemExpend(boolean expend) {
      // viewHolder.mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text11;
        private CardView card;
        private CardStackView mStackView;
        private TestStackAdapter mTestStackAdapter;
        private ViewHolder(final View v){
            super(v);
            text11=(TextView)v.findViewById(R.id.text11);
            card=(CardView)v.findViewById(R.id.card_griditem);
            mStackView = (CardStackView) v.findViewById(R.id.stackview_main);

        }
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(name[position]);//name[position]
        holder.mStackView.setItemExpendListener(this);
        holder.mStackView.setAdapter(new TestStackAdapter(context));
      //  holder.mStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(holder.mStackView));
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        new TestStackAdapter(context).updateData(Arrays.asList(TEST_DATAS));
                    }
                }
                , 100
        );
        onHitMeToShowBezier(holder.itemView, position);
    }
    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);
         viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }

    public void onHitMeToShowBezier(final View v, int position) {
        ArcTranslateAnimation animation = new ArcTranslateAnimation(600, 0, 800, 0);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1000);

        v.startAnimation(animation);
    }
}

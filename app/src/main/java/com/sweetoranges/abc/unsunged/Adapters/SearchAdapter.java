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
    private static Context context;
    private ViewHolder viewHolder;
    private String name[] = new String[]{"Favourites","Liked","Current","Playlist1"};

    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
    };

    public SearchAdapter(FragmentActivity activity) { context = activity; }

    public static class ViewHolder extends RecyclerView.ViewHolder implements CardStackView.ItemExpendListener {
        private TextView text11;
        private CardView card;
        private CardStackView mStackView;
        private LinearLayout mActionButtonContainer;
        private TestStackAdapter mTestStackAdapter;
        private ViewHolder(final View v){
            super(v);
            text11=(TextView)v.findViewById(R.id.text11);
            card=(CardView)v.findViewById(R.id.card_griditem);
            mStackView = (CardStackView) v.findViewById(R.id.stackview_main2);
            mActionButtonContainer = (LinearLayout)v.findViewById(R.id.button_container);
            mStackView.setItemExpendListener(this);
            mStackView.setAdapter(new TestStackAdapter(context));
            //  holder.mStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(holder.mStackView));
            new Handler().postDelayed(new Runnable() {@Override public void run() { new TestStackAdapter(context).updateData(Arrays.asList(TEST_DATAS)); }}, 500);
        }

        @Override public void onItemExpend(boolean expend) {
            mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);}
    }
    @Override public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.text11.setText(name[position]);//name[position]

        onHitMeToShowBezier(holder.itemView, position);
        //mStackView = (CardStackView) view.findViewById(R.id.stackview_main);
        //        mActionButtonContainer = (LinearLayout)     view.findViewById(R.id.button_container);
        //        int img1=getActivity().getResources().getIdentifier("download", "drawable", getActivity().getPackageName());
        //        mStackView.setItemExpendListener(this);
        //        mTestStackAdapter = new TestStackAdapter(getActivity());
        //        mStackView.setAdapter(mTestStackAdapter);
        //        new Handler().postDelayed(
        //                new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
        //                    }
        //                }
        //                , 200
        //        );
    }
    @Override public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.tiles, parent, false);
         viewHolder = new ViewHolder(v);
        return viewHolder; }
    @Override public int getItemCount(){ return name.length;  }

    public void onHitMeToShowBezier(final View v, int position) {
        ArcTranslateAnimation animation = new ArcTranslateAnimation(1000, 0, 800, 0);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(500);

        v.startAnimation(animation);
    }
}

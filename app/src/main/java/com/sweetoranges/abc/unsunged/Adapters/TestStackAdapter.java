package com.sweetoranges.abc.unsunged.Adapters;
import android.content.Context;
import android.graphics.PorterDuff;
//import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;
import com.sweetoranges.abc.unsunged.R;

import androidx.core.content.ContextCompat;

public class TestStackAdapter extends StackAdapter<Integer> {

    public TestStackAdapter(Context context) {
        super(context);
    }

    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
        ColorItemWithNoHeaderViewHolder h = (ColorItemWithNoHeaderViewHolder) holder;
        h.onBind(data, position);
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {//            mLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.download));
        return new ColorItemWithNoHeaderViewHolder(getLayoutInflater().inflate(R.layout.list_card_item_with_no_header, parent, false));

    }

    @Override
    public int getItemViewType(int position) { return R.layout.list_card_item_with_no_header; }


    static class ColorItemWithNoHeaderViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        TextView mTextTitle;
        RelativeLayout cover;
        public ColorItemWithNoHeaderViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
            cover = (RelativeLayout) view.findViewById(R.id.coverimage);
        }

        @Override
        public void onItemExpand(boolean b) {
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));
            cover.setBackgroundResource(R.drawable.sample);

        }

    }


}

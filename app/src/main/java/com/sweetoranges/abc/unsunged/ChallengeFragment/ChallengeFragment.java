package com.sweetoranges.abc.unsunged.ChallengeFragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;
import com.sweetoranges.abc.unsunged.Adapters.TestStackAdapter;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;

import java.util.Arrays;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChallengeFragment extends Fragment implements OnBackPressed, CardStackView.ItemExpendListener {
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
    private CardStackView mStackView;
    private LinearLayout mActionButtonContainer;
    private TestStackAdapter mTestStackAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_challenge, container, false);
        mStackView = (CardStackView) view.findViewById(R.id.stackview_main);
        mStackView.setItemExpendListener(this);
        mTestStackAdapter = new TestStackAdapter(getActivity());
        mStackView.setAdapter(mTestStackAdapter);


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
                    }
                }
                , 200
        );
        return view;
    }
    @Override public void onBackPressed(){ Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack(); }

    @Override
    public void onItemExpend(boolean expend) {
        //mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }

}

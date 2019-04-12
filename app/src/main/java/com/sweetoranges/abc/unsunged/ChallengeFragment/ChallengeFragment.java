package com.sweetoranges.abc.unsunged.ChallengeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mutualmobile.cardstack.CardStackLayout;
import com.mutualmobile.cardstack.utils.Units;
import com.sweetoranges.abc.unsunged.Adapters.MyCardStackAdapter;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.Classes.OnRestartRequest;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Utils.Prefs;

import java.util.Objects;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChallengeFragment extends Fragment implements OnBackPressed, OnRestartRequest {
    private CardStackLayout mCardStackLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_challenge, container, false);
        mCardStackLayout = (CardStackLayout) view.findViewById(R.id.cardStack);
        setupAdapter();
        return view;
    }
    @Override public void onBackPressed(){ Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack(); }
    @Override
    public void requestRestart() {
        mCardStackLayout.removeAdapter();
        mCardStackLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                setupAdapter();
            }
        }, 200);
    }

    private void setupAdapter() {

        mCardStackLayout.setShowInitAnimation(Prefs.isShowInitAnimationEnabled());

        mCardStackLayout.setParallaxEnabled(Prefs.isParallaxEnabled());
        mCardStackLayout.setParallaxScale(Prefs.getParallaxScale(getActivity()));

        mCardStackLayout.setCardGap(Units.dpToPx(getActivity(), Prefs.getCardGap(getActivity())));
        mCardStackLayout.setCardGapBottom(Units.dpToPx(getActivity(), Prefs.getCardGapBottom(getActivity())));

        //mCardStackLayout.setAdapter(new MyCardStackAdapter(getActivity()));
    }

}

package com.sweetoranges.abc.unsunged.BingeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;
import org.jetbrains.annotations.Nullable;
import androidx.fragment.app.Fragment;

public class BingeFragment extends Fragment implements OnBackPressed {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_binge, container, false);
    }
    @Override public void onBackPressed(){ getActivity().getSupportFragmentManager().popBackStack(); }
}

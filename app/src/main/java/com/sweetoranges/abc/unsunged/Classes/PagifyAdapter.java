package com.sweetoranges.abc.unsunged.Classes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.escodro.viewpagify.ViewPagify;
import com.sweetoranges.abc.unsunged.SongChangeFragment.SongChangeFragment;

public class PagifyAdapter extends FragmentStatePagerAdapter {

    public PagifyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt(SongChangeFragment.ARGS_POSITION, position);
        final SongChangeFragment fragment = new SongChangeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return PagifyApp.getAlbumDatabase().size();
    }
}

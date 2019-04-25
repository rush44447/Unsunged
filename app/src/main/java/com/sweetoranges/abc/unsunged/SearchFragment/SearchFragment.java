package com.sweetoranges.abc.unsunged.SearchFragment;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.ferfalk.simplesearchview.utils.DimensUtils;
import com.google.android.material.tabs.TabLayout;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Search.SearchLanguageFragment;
import com.sweetoranges.abc.unsunged.Search.SearchProfileFragment;
import com.sweetoranges.abc.unsunged.Search.SearchTypeFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment  {
    public static final int EXTRA_REVEAL_CENTER_PADDING = 40;
    private SimpleSearchView searchView;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search,container,false);
//        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        // setSupportActionBar(toolbar);
//
//        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//
//        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
//        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
        setupSearchView(menu);
    }
    private void setupSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        searchView.setTabLayout(tabLayout);

        Point revealCenter = searchView.getRevealAnimationCenter();
        revealCenter.x = revealCenter.x - DimensUtils.convertDpToPx(EXTRA_REVEAL_CENTER_PADDING, getActivity());   //  imppp[ppppppppppppp
    }

//    @Override
//    public void onBackPressed() {
//        if (searchView.onBackPressed()) {
//            return;
//        }
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager());
        adapter.addFragment(new SearchProfileFragment(), "Search By Profile");
        adapter.addFragment(new SearchTypeFragment(), "Search By Type");
        adapter.addFragment(new SearchLanguageFragment(), "Search By Language");
        //search by mood
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
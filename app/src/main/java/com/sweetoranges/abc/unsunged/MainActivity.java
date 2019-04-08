package com.sweetoranges.abc.unsunged;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sweetoranges.abc.unsunged.ChallengeFragment.ChallengeFragment;
import com.sweetoranges.abc.unsunged.BingeFragment.BingeFragment;
import com.sweetoranges.abc.unsunged.MyProfileFragment.MyProfileFragment;
import com.sweetoranges.abc.unsunged.SearchFragment.SearchFragment;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomnav;
    private int mSelectedItem;
    private static final String SELECTED_ITEM = "arg_selected_item";
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.binge:
                    loadFragment(new BingeFragment());
                    return true;
                case R.id.search:
                    loadFragment(new SearchFragment());
                    return true;
                case R.id.type:
                    loadFragment(new ChallengeFragment());
                    return true;
                case R.id.profile:
                    loadFragment(new MyProfileFragment());
                    return true;
            }
            return false; }};

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}



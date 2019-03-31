package com.sweetoranges.abc.unsunged;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sweetoranges.abc.unsunged.AudioTypeFragment.AudioTypeFragment;
import com.sweetoranges.abc.unsunged.BingeFragment.BingeFragment;
import com.sweetoranges.abc.unsunged.MyProfileFragment.MyProfileFragment;
import com.sweetoranges.abc.unsunged.SearchFragment.SearchFragment;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomnav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomnav =(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.binge:
                                selectedFragment = BingeFragment.newInstance();
                                break;
                            case R.id.search:
                                selectedFragment = SearchFragment.newInstance();
                                break;
                            case R.id.type:
                                selectedFragment = AudioTypeFragment.newInstance();
                                break;
                            case R.id.profile:
                                selectedFragment = MyProfileFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, BingeFragment.newInstance());
        transaction.commit();
    }
}

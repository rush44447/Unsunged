package com.sweetoranges.abc.unsunged;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    float x1,y1;
    float x2,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dashboard);
        Intent i = new Intent(Dashboard.this,MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_down, R.anim.slide_upwards);
    }

}

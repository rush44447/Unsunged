package com.sweetoranges.abc.unsunged.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.R;

public class LoginActivity extends AppCompatActivity {
AppCompatImageView BackScreen;
Button Loginpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BackScreen=(AppCompatImageView)findViewById(R.id.loginScreen);
        Loginpage=(Button)findViewById(R.id.loginButton);
        Glide.with(getApplicationContext()).load(R.drawable.music).into(BackScreen);
        Loginpage.setOnClickListener(v -> { startActivity(new Intent(LoginActivity.this, MainActivity.class));});
    }
}

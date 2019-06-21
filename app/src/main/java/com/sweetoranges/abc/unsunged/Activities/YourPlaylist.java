
package com.sweetoranges.abc.unsunged.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.sweetoranges.abc.unsunged.R;

public class YourPlaylist extends AppCompatActivity {
    TextView PlaylistType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_playlist);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        PlaylistType=(TextView)findViewById(R.id.atext);

    }
}

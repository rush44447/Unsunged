
package com.sweetoranges.abc.unsunged.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
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
      //  PlaylistType=(TextView)findViewById(R.id.atext);
        final TextView animateTextView = (TextView)findViewById(R.id.atext);
        animateTextView.setText(name);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(400f, 0f);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(animation -> {
            float progress = (float) animation.getAnimatedValue();
            animateTextView.setTranslationY(progress);
            // no need to use invalidate() as it is already present in             //the text view.
        });
        valueAnimator.start();

    }
}

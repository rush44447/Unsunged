package com.sweetoranges.abc.unsunged.MyProfileFragment;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;

public class MyProfileFragment extends Fragment {
    private RecyclerView playlistRv;
    private ImageView backImage;
    private ImageView circularImageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);

        playlistRv= (RecyclerView) view.findViewById(R.id.playlistRecycle);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.imgview), 100);
        Bitmap back = ImageConverter.getRoundedCornerBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.music), 100);
        circularImageView = (ImageView)view.findViewById(R.id.circleView);
        backImage = (ImageView)view.findViewById(R.id.backimage);
        circularImageView.setImageBitmap(circularBitmap);
        backImage.setImageBitmap(back);
        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiag();
            }

            private void showDiag() {
                final View dialogView = View.inflate(getContext(),R.layout.profile,null);

                final Dialog dialog = new Dialog(getContext(),R.style.MyAlertDialogStyle);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(dialogView);

                ImageView imageView = (ImageView)dialog.findViewById(R.id.closeDialogImg);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        revealShow(dialogView, false, dialog);
                    }});
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override public void onShow(DialogInterface dialogInterface) {
                        revealShow(dialogView, true, null);
                    }});
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (i == KeyEvent.KEYCODE_BACK){
                            revealShow(dialogView, false, dialog);return true; }
                        return false; }});
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
            }

            private void revealShow(View dialogView, boolean b, final Dialog dialog) {

                final View view = dialogView.findViewById(R.id.dialog);

                int w = view.getWidth();
                int h = view.getHeight();

                int endRadius = (int) Math.hypot(w, h);

                int cx = (int) (circularImageView.getX() +334);
                int cy = (int) (circularImageView.getY())+ circularImageView.getHeight() + 48;


                if(b){
                    Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);
                    view.setVisibility(View.VISIBLE);
                    revealAnimator.setDuration(300);
                    revealAnimator.start();

                } else {

                    Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override public void onAnimationEnd(Animator animation) { super.onAnimationEnd(animation);
                            dialog.dismiss();
                            view.setVisibility(View.INVISIBLE); }});
                    anim.setDuration(300);
                    anim.start(); } }});
        playlistRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity()));
        return view;
    }

}

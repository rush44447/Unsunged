package com.sweetoranges.abc.unsunged.MyProfileFragment;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.transition.Fade;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Classes.DetailsTransition;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.Classes.PlayListViewHolder;
import com.sweetoranges.abc.unsunged.DetailsFragment.DetailsFragment;
import com.sweetoranges.abc.unsunged.R;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;
import com.sweetoranges.abc.unsunged.interfaces.PlayListClickListener;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static android.app.Activity.RESULT_OK;

public class MyProfileFragment extends Fragment implements PlayListClickListener {
    private RecyclerView playlistRv;
    private ImageView backImage;
    private ImageView circularImageView;
    private String imageName;
    private static int RESULT_LOAD_IMAGE = 1;
    String picturePath="";
    private Handler myHandler = new Handler();
    Bitmap myBitmap;
    Boolean Flag=false;
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
                ImageView circularImageDialog = (ImageView) dialog.findViewById(R.id.circleViewX);
                ImageView imageView = (ImageView)dialog.findViewById(R.id.closeDialogImg);
                imageView.setOnClickListener(v -> revealShow(dialogView, false, dialog));

                dialog.setOnShowListener(dialogInterface -> revealShow(dialogView, true, null));

                dialog.setOnKeyListener((dialogInterface, i, keyEvent) -> {
                    if (i == KeyEvent.KEYCODE_BACK){
                        revealShow(dialogView, false, dialog);return true; }
                    return false; });
                circularImageDialog.setOnClickListener(v -> {
                    browsePhoto("Unsunged");
                    myHandler.postDelayed(new Runnable() {
                        @SuppressLint("DefaultLocale")
                        public void run() {
                            if(Flag){
                                //circularImageDialog.setImageBitmap(myBitmap);
                                circularImageDialog.setImageResource(R.drawable.model);//setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                Toast.makeText(getActivity(), "here"+picturePath, Toast.LENGTH_SHORT).show();
                                Flag=false;
                            }
                            myHandler.postDelayed(this, 100);
                        }
                    },100);
                    // circularImageDialog.setImageBitmap(myBitmap);

                });

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
    @Override
    public void onPlayListClicked(PlayListViewHolder holder, int position) {
        int kittenNumber = (position % 6) + 1;

        DetailsFragment kittenDetails = DetailsFragment.newInstance(kittenNumber);
        kittenDetails.setSharedElementEnterTransition(new DetailsTransition());
        kittenDetails.setEnterTransition(new Fade());
        kittenDetails.setExitTransition(new Fade());
        kittenDetails.setSharedElementReturnTransition(new DetailsTransition());

        getActivity().getSupportFragmentManager()
                .beginTransaction()
             //   .addSharedElement(, "playImage")
                .replace(R.id.container, kittenDetails)
                .addToBackStack(null)
                .commit();
    }
    private void browsePhoto(String imageName) {
        if (this.imageName != null && !this.imageName.equals(""))
            this.imageName = imageName;
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            File imgFile = new File(picturePath);
            if(imgFile.exists()) { myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
               Flag=true; }
        }
    }


}

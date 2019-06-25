package com.sweetoranges.abc.unsunged.MyProfileFragment;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.transition.Fade;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.sweetoranges.abc.unsunged.Classes.DetailsTransition;
import com.sweetoranges.abc.unsunged.Classes.PlayListViewHolder;
import com.sweetoranges.abc.unsunged.DetailsFragment.DetailsFragment;
import com.sweetoranges.abc.unsunged.R;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Classes.ImageConverter;
import com.sweetoranges.abc.unsunged.interfaces.PlayListClickListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MyProfileFragment extends Fragment implements PlayListClickListener {
   // private RecyclerView playlistRv;
    private ImageView backImage;
    private ImageView circularImageView;
    private String imageName;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int CAPTURE_IMAGE = 0;
    private static final int SELECT_PICTURE = 1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int PICK_IMAGE_REQUEST = 0;
    private Uri mImageUri;
    Boolean Flag=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);

    //        playlistRv= (RecyclerView) view.findViewById(R.id.playlistRecycle);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.imgview), 100);
        Bitmap back = ImageConverter.getRoundedCornerBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.music), 100);
        circularImageView = (ImageView)view.findViewById(R.id.circleView);
        backImage = (ImageView)view.findViewById(R.id.backimage);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);
        tabLayout.setupWithViewPager(viewPager);

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
                    imageSelect();
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String mImageUri = preferences.getString("image", null);
try {
    if (mImageUri != null) {
        circularImageView.setImageURI(Uri.parse(mImageUri));
    } else {
        circularImageView.setImageResource(R.drawable.ladyc);
    }
}catch (Exception e)
{circularImageView.setImageResource(R.drawable.ladyc); }

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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new UploadedFragment(), "Uploaded");
        adapter.addFragment(new PlaylistFragment(), "PlayList");
        viewPager.setAdapter(adapter);
    }
public void imageSelect() {
    permissionsCheck();
    Intent intent;
    intent = new Intent(Intent.ACTION_GET_CONTENT);
    intent.setType("image/*");
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//    startActivityForResult(i, RESULT_LOAD_IMAGE);
}

    public void permissionsCheck() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_IMAGE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a image.
                // The Intent's data Uri identifies which item was selected.
                if (data != null) {

                    // This is the key line item, URI specifies the name of the data
                    mImageUri = data.getData();

                    // Saves image URI as string to Default Shared Preferences
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("image", String.valueOf(mImageUri));
                    editor.commit();

                    // Sets the ImageView with the Image URI
                    circularImageView.setImageURI(mImageUri);
                    circularImageView.invalidate();
                }
            }
        }
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

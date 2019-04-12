package com.sweetoranges.abc.unsunged.MyProfileFragment;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;
import androidx.recyclerview.widget.RecyclerView;
import io.fabric.sdk.android.Fabric;

import android.widget.ImageView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;

public class MyProfileFragment extends Fragment implements OnBackPressed {
    RecyclerView playlistRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);

        playlistRv= (RecyclerView) view.findViewById(R.id.playlistRecycle);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.imgview);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
        ImageView circularImageView = (ImageView)view.findViewById(R.id.circleView);
        circularImageView.setImageBitmap(circularBitmap);
        playlistRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity()));
        return view;
    }

    @Override public void onBackPressed(){ getActivity().getSupportFragmentManager().popBackStack(); }

}

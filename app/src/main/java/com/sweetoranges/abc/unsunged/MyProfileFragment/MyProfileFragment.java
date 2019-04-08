package com.sweetoranges.abc.unsunged.MyProfileFragment;
import io.fabric.sdk.android.Fabric;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.crashlytics.android.Crashlytics;
import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;
import com.sweetoranges.abc.unsunged.R;

public class MyProfileFragment extends Fragment {
    RecyclerView playlistRv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.imgview);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
        ImageView circularImageView = (ImageView)view.findViewById(R.id.circleView);
        circularImageView.setImageBitmap(circularBitmap);

        Fabric.with(getActivity(), new Crashlytics());

        playlistRv= (RecyclerView) view.findViewById(R.id.playlistRecycle);
        playlistRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity()));

        return view;
    }

}

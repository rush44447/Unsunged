package com.sweetoranges.abc.unsunged.MyProfileFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sweetoranges.abc.unsunged.R;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;
import org.jetbrains.annotations.Nullable;
public class MyProfileFragment extends Fragment {
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

       // Fabric.with(getActivity(), new Crashlytics());


        playlistRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Favourites"));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Liked"));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Current"));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Playlist1"));
        return view;
    }


}

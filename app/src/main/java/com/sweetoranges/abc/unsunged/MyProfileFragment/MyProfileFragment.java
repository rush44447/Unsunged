package com.sweetoranges.abc.unsunged.MyProfileFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sweetoranges.abc.unsunged.Classes.OnBackPressed;
import com.sweetoranges.abc.unsunged.R;
import com.iammert.library.ui.multisearchviewlib.MultiSearchView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.PlaylistAdapter;
import com.sweetoranges.abc.unsunged.Classes.ImageConverter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
//        mv=(MultiSearchView)view.findViewById(R.id.multiSearchView);
//
//        mv.setSearchViewListener(new MultiSearchView.MultiSearchViewListener(){
//    @Override
//    public void onTextChanged(int i, @NotNull CharSequence charSequence) {
//        Log.v("TEST", "changed: index: $index, query: $s");
//        Toast.makeText(getActivity(), "changed", Toast.LENGTH_SHORT).show();}
//
//    @Override
//    public void onSearchItemRemoved(int i) {
//        Log.v("TEST", "remove: index: $index");
//        Toast.makeText(getActivity(), "remove", Toast.LENGTH_SHORT).show();}
//
//    @Override
//    public void onSearchComplete(int i, @NotNull CharSequence charSequence) {
//        Log.v("TEST", "complete: index: $index, query: $s");
//        Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();}
//
//    @Override
//    public void onItemSelected(int i, @NotNull CharSequence charSequence) {
//        Log.v("TEST", "onItemSelected: index: $index, query: $s");
//        Toast.makeText(getActivity(), "onItemSelected", Toast.LENGTH_SHORT).show();}});
     //   Fabric.with(getActivity(), new Crashlytics());


        playlistRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Favourites"));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Liked"));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Current"));
        playlistRv.setAdapter(new PlaylistAdapter(getActivity(),"Playlist1"));
        return view;
    }

    @Override public void onBackPressed(){ getActivity().getSupportFragmentManager().popBackStack(); }

}

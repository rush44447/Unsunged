package com.sweetoranges.abc.unsunged.Adapters;


import android.content.Context;
import android.graphics.Bitmap;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.widget.AppCompatImageView;

import com.robert.autoplayvideosample.model.VideoModel;
import com.robert.autoplayvideo.CustomViewHolder;
import com.robert.autoplayvideo.VideosAdapter;
import com.squareup.picasso.Picasso;
import com.sweetoranges.abc.unsunged.Model.QuickSearchModel;
import com.sweetoranges.abc.unsunged.R;

import java.util.List;

public class AutoPlayVideoAdapter extends VideosAdapter {
    private String TAG = "AutoPlayVideoAdapter";

    private final List<QuickSearchModel> list;
    private final Picasso picasso;
    public Context context;
    private QuickAdapter.ViewHolder viewHolder;
    private int[] ids ;

    public class MyViewHolder extends CustomViewHolder {
        private TextView listType;
        VideoView videoView;
        //        private View Quickback;
        boolean isMuted;
        public MyViewHolder(final View v) {
            super(v);
            listType=(TextView)v.findViewById(R.id.quickname);
            videoView =(VideoView)v.findViewById(R.id.videoView);

        }

        //override this method to get callback when video starts to play
        @Override
        public void videoStarted() {
            super.videoStarted();
            img_playback.setImageResource(R.drawable.ic_pause);
            if (isMuted) {
                muteVideo();
                img_vol.setImageResource(R.drawable.ic_mute);
            } else {
                unmuteVideo();
                img_vol.setImageResource(R.drawable.ic_unmute);
            }
        }
        @Override
        public void pauseVideo() {
            super.pauseVideo();
            img_playback.setImageResource(R.drawable.ic_play);
        }
    }
    public AutoPlayVideoAdapter(List<QuickSearchModel> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quicksearch, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.listType.setText("here");
        MediaController mediaController= new MediaController(context);
        mediaController.setAnchorView(holder.videoView);
        String path = "android.resource://" + context.getPackageName() + "/" + R.raw.marvel;
        holder.videoView.setVideoURI(Uri.parse(path));
        holder.videoView.requestFocus();
        holder.videoView.start();

        ((MyViewHolder) holder).tv.setText(String.format("%s・%s", (position + 5) + " minutes from now",list.get(position).getName().toUpperCase()));
        picasso.load(R.mipmap.ic_launcher).config(Bitmap.Config.RGB_565).into(((MyViewHolder) holder).userIcon);
        holder.setImageUrl(list.get(position).getImage_url());
        holder.setVideoUrl(list.get(position).getVideo_url());

        if (list.get(position).getImage_url() != null && !list.get(position).getImage_url().isEmpty()) {
            picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getImageView());

            Log.e(TAG, "--->ImageUrl=" + holder.getImageUrl());
        }

        holder.setLooping(true); //optional - true by default

        //to play pause videos manually (optional)
        ((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isPlaying()) {
                    holder.pauseVideo();
                    holder.setPaused(true);
                } else {
                    holder.playVideo();
                    holder.setPaused(false);
                }
            }
        });

        //to mute/un-mute video (optional)
        ((MyViewHolder) holder).img_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MyViewHolder) holder).isMuted) {
                    holder.unmuteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_unmute);
                } else {
                    holder.muteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_mute);
                }
                ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
            }
        });

        if (list.get(position).getVideo_url() == null) {
            ((MyViewHolder) holder).img_vol.setVisibility(View.GONE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.GONE);
        } else {
            ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


}

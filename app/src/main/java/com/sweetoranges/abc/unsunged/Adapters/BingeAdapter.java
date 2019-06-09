package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sweetoranges.abc.unsunged.Classes.ArcTranslateAnimation;
import com.sweetoranges.abc.unsunged.Model.Binge;
import com.sweetoranges.abc.unsunged.Model.Quick;
import com.sweetoranges.abc.unsunged.Model.Story;
import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

public class BingeAdapter extends RecyclerView.Adapter<BingeAdapter.MyViewHolder>  {
    private Context context;
    private List<Binge> sharedList;
    private List<Quick> contactListFiltered;
    private SearchAdapter.ContactsAdapterListener listener;
    public int[] id=new int[]{1,2,3,4};
    private String[] searchby;

    public BingeAdapter(FragmentActivity activity, List<Binge> sharedList) {
        this.context = activity;
        this.sharedList=sharedList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Username,Userrank;
        private AppCompatImageView ProfilePic;
        private CardView card;
        private RecyclerView mRecyclerView;

        public MyViewHolder(View view) {
            super(view);
            Username=(TextView)view.findViewById(R.id.username);
            Userrank=(TextView)view.findViewById(R.id.userrank);
            card=(CardView)view.findViewById(R.id.card_griditem);
            mRecyclerView=(RecyclerView)view.findViewById(R.id.quickrecycler);
            ProfilePic=(AppCompatImageView)view.findViewById(R.id.circleView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }



    @Override
    public BingeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tiles, parent, false);

        return new BingeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BingeAdapter.MyViewHolder holder, final int position) {
//        final Quick contact = contactListFiltered.get(position);
//        holder.Userrank.setText(contact.getName());
//        holder.Username.setText(contact.getPhone());
//        Glide.with(context)
//                .load(contact.getImage())
//                // .apply(RequestOptions.circleCropTransform())
//                .into(holder.ProfilePic);
//
//
//        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        holder.mRecyclerView.setAdapter(new QuickAdapter(context,id));
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

}

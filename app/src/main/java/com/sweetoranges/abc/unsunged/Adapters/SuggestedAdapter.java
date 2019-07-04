package com.sweetoranges.abc.unsunged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sweetoranges.abc.unsunged.Model.Binge;
import com.sweetoranges.abc.unsunged.Model.Quick;
import com.sweetoranges.abc.unsunged.R;

import java.util.List;


public class SuggestedAdapter extends RecyclerView.Adapter<SuggestedAdapter.MyViewHolder>  {
    private Context context;
    private int songId;
    private SearchAdapter.ContactsAdapterListener listener;
    public int[] id=new int[]{1,2,3,4};

    public SuggestedAdapter(Context context, int songId) {
        this.context = context;
        this.songId=songId;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView AlbumName,SongName;
        private ImageView Cover;
        private RecyclerView mRecyclerView;

        public MyViewHolder(View view) {
            super(view);
            AlbumName=(TextView)view.findViewById(R.id.albumName);
            SongName=(TextView)view.findViewById(R.id.songName);
//            mRecyclerView=(RecyclerView)view.findViewById(R.id.quickrecycler);
            Cover=(ImageView)view.findViewById(R.id.coverimage);

//            view.setOnClickListener(view1 -> listener.onContactSelected(contactListFiltered.get(getAdapterPosition())));
        }
    }



    @Override
    public SuggestedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested, parent, false);

        return new SuggestedAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SuggestedAdapter.MyViewHolder holder, final int position) {
        holder.AlbumName.setText("Louis The Child");
        holder.SongName.setText("Better Not");
        holder.Cover.setImageResource(R.drawable.lady);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}

package com.sweetoranges.abc.unsunged.Adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sweetoranges.abc.unsunged.Model.Song;
import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public RecyclerAdapter(SongClicked clicked) {
        songClicked = clicked;

    }

    private ArrayList songsList = new ArrayList<Song>();
    private SongClicked songClicked;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Song song= (Song) songsList.get(i);
        viewHolder.bind(song);
        viewHolder.itemView.setOnClickListener(v -> songClicked.onSongClicked(song)
        );
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public void addSongs(ArrayList songs) {
        songsList.clear();
        songsList = songs;
        notifyDataSetChanged();
    }

    public interface SongClicked {
        void onSongClicked(Song song);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,artist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewSongTitle);
            artist = itemView.findViewById(R.id.textViewArtistName);

        }
        void bind(Song song){
            title.setText(song.title);
            artist.setText(song.artistName);
        }
    }
}

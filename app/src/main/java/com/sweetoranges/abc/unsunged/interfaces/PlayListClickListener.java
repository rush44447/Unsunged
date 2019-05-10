package com.sweetoranges.abc.unsunged.interfaces;

import com.sweetoranges.abc.unsunged.Classes.PlayListViewHolder;

public interface PlayListClickListener {
    /**
     * Called when a kitten is clicked
     * @param holder The ViewHolder for the clicked kitten
     * @param position The position in the grid of the kitten that was clicked
     */
    void onPlayListClicked(PlayListViewHolder holder, int position);
}
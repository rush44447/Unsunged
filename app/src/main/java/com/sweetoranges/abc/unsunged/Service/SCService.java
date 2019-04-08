package com.sweetoranges.abc.unsunged.Service;
import com.sweetoranges.abc.unsunged.Player.Config;
import com.sweetoranges.abc.unsunged.Player.Track;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SCService {
    @GET("/tracks?client_id=" + Config.CLIENT_ID)
    Call<List<Track>> getRecentTracks(@Query("created_at") String date);
}

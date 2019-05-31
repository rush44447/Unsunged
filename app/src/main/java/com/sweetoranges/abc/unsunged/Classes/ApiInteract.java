package com.sweetoranges.abc.unsunged.Classes;

import com.sweetoranges.abc.unsunged.Model.Quick;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInteract {
    @GET("contacts.json")
    Call<List<Quick>> getStreaming();
}

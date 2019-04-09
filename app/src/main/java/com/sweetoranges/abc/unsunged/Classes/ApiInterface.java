package com.sweetoranges.abc.unsunged.Classes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{stringid}")
    Call<StreamingRequest> getStreaming(@Path("stringid") String stringid);
}
package com.sweetoranges.abc.unsunged.Classes;

import com.sweetoranges.abc.unsunged.Model.Binge;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{stringid}")
    Call<StreamingRequest> getStreaming(@Path("stringid") String stringid); //this is added to baseurl
    @GET("/user/shared")
    Call<StreamingRequest> getStory(); //this is added to baseurl
    @GET("/user/shared")
    Call<List<Binge>> getShared(); // add token here
}
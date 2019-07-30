package com.sweetoranges.abc.unsunged.Classes;

import com.sweetoranges.abc.unsunged.Model.Binge;
import com.sweetoranges.abc.unsunged.Model.SongModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{stringid}")
    Call<StreamingRequest> getStreaming(@Path("stringid") String stringid); //this is added to baseurl

    @GET("/user/shared")
    Call<StreamingRequest> getStory(); //this is added to baseurl

    @GET("/user/shared")
    Call<List<Binge>> getShared(); // add token here

    @GET("/get-all.php")
    Call<List<SongModel>> getSongs(@Header("Authorization") String authkey, @Path("customerId") String customerId);
//    @GET("/user/shared")
//    Call<List<Binge>> getSongs(); // add token here

    //  https://unsungedmusic.000webhostapp.com/n-api/api/sg/get-all.php
}
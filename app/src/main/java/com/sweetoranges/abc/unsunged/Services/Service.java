package com.sweetoranges.abc.unsunged.Services;


import com.sweetoranges.abc.unsunged.Model.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static android.R.id.list;

public interface Service {
    @GET("/api/movies")
    Call<List<Story>> getStories();
    //Call<List<Movie> listRepos(@Path("user") String user);
}
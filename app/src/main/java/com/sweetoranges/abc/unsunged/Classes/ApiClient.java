package com.sweetoranges.abc.unsunged.Classes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {   // enter base url; here

    public static final String BASE_URL = "https://api.clyp.it/";
    public static final String BASE_URL2 = "https://unsungedmusic.000webhostapp.com/n-api/api/sg";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();//connection built
        }
        return retrofit;
    }
    public static Retrofit getClient2() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();//connection built
        }
        return retrofit;
    }
}
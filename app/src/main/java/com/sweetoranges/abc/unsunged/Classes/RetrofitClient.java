package com.sweetoranges.abc.unsunged.Classes;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sweetoranges.abc.unsunged.Classes.ApiCaller.BASE_URL;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context mContext) {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

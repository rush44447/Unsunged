package com.sweetoranges.abc.unsunged.MyProfileFragment;
import com.sweetoranges.abc.unsunged.Player.Config;
import com.sweetoranges.abc.unsunged.Service.SCService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class SoundCloud {
    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(Config.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final SCService SERVICE = RETROFIT.create(SCService.class);

    public static SCService getService() {
        return SERVICE;
    }
}

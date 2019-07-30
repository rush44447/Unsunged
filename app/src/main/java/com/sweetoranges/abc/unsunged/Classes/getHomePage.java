package com.sweetoranges.abc.unsunged.Classes;

public static void getHomePage(Context mContext, Callback<HomepageData> callback) {
        Call<HomepageData> call = RetrofitClient.getClient(mContext).create(ApiInterface.class).getHomePageData(
        Helper.getAuthToken()
        , AppSharedPref.getCustomerId(mContext)
        );
        call.enqueue(callback);
        }
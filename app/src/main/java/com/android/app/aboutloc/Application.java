package com.android.app.aboutloc;


import android.content.Context;

import com.android.app.aboutloc.constant.ApiConstant;
import com.android.app.aboutloc.util.ConnectivityReceiver;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Application extends android.app.Application {

    private static Context appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    // Returning application instance
    public static synchronized Context getInstance() {
        return appInstance;
    }

    // Returning Retrofit with the Base Url
    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    // Returning general string variable for the Resource Id
    public static String getStringRes(int id) {
        return getInstance().getResources().getString(id);
    }


    // Setter to ConnectivityChangeListener
    public static void setConnectivityChangeListener(ConnectivityReceiver.ConnectivityReceiverListener connectionChangeListener) {
        ConnectivityReceiver.setConnectivityReceiverListener(connectionChangeListener);
    }


}

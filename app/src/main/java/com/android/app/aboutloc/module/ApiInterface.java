package com.android.app.aboutloc.module;
import com.android.app.aboutloc.model.Facts;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiInterface {
    /*
    Retrofit interface for calling the getInformation webservice
     */
    @GET("/u/746330/facts.json")
    Call<Facts> getInformation();
}

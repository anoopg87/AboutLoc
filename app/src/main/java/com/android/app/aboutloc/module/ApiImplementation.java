package com.android.app.aboutloc.module;

import com.android.app.aboutloc.Application;
import com.android.app.aboutloc.R;
import com.android.app.aboutloc.model.Facts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 *
 * Retrofit service implementation
 */
public class ApiImplementation {

     public  void invokeGetInformationApi(IApiResponse<Facts> callBack){
         // Creating ApiInterface instance using the Global retrofit object
         ApiInterface apiInterface = Application.getRetrofit().create(ApiInterface.class);
         Call<Facts> call= apiInterface.getInformation();
         // clone is used for calling the same url with same parameters to the same server multiple time
         call.clone().enqueue(new Callback<Facts>() {
             @Override
             public void onResponse(Call<Facts> call, Response<Facts> informationList) {

                 // sending the response to the callback method
                 callBack.onSuccess(informationList.body());
             }
             @Override
             public void onFailure(Call<Facts> call, Throwable t) {

                 // sending the server error to callback method
                 callBack.onError(Application.getStringRes(R.string.error_in_webservice));
             }
         });



    }
}

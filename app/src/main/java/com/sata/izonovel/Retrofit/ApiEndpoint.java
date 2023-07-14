package com.sata.izonovel.Retrofit;

import com.sata.izonovel.Model.RegisterModel;
import com.sata.izonovel.RegisterActivity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIEndPoint {
    String API_KEY = "8ZQDmrtgC0RX5AVLVQV5YjyS1pA1D7Sa7HZtlTSViEA58X8CUl8mueSLqHd3Md3y";

    @Headers("api-key : "+ API_KEY)
    @POST("/action/inserttOne")
    Call<RegisterActivity> registerUser(@Body RegisterModel userModel);
}

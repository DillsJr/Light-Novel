package com.sata.izonovel.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static String BASE_URL = " https://data.mongodb-api.com/app/data-yvczw/endpoint/data/v1";
    private static Retrofit retorfit = null;

    public static APIEndPoint endpoint(){
        retorfit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retorfit.create(APIEndPoint.class);
    }
}

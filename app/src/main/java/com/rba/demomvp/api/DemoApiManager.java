package com.rba.demomvp.api;

import com.rba.demomvp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ricardo Bravo on 6/03/17.
 */

public class DemoApiManager {

    private static DemoApi storeApi;

    public static DemoApi apiManager() {

        if(storeApi == null){

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);

            if(BuildConfig.IS_DEBUG){
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            }

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            storeApi = client.create(DemoApi.class);
        }

        return storeApi;
    }

    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        if(BuildConfig.IS_DEBUG){
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
